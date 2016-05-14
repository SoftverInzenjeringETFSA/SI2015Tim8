package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodFaktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodNarudzba;



public class NarudzbaServis 
{
	Session s;
	public NarudzbaServis(Session s){
		this.s =s;
	}
	
	public boolean kreirajNarudzbu(Narudzba n)
	{
		Transaction t = s.beginTransaction();
		s.save(n);
		t.commit();
		return true;
	}
	
	public boolean dodajProizvod(ProizvodNarudzba pn)
	{
		Transaction t = s.beginTransaction();
		s.save(pn);
		t.commit();
		return true;
	}
	
	public boolean izbrisiNarudzbu(int id)
	{
		Transaction t = s.beginTransaction();
		Narudzba n = (Narudzba) s.get(Narudzba.class, id);
		if(n!=null) 
		{
			
			ProizvodNarudzba[] pn = n.getProizvodNarudzbas().toArray(new ProizvodNarudzba[n.getProizvodNarudzbas().size()]);
			for(int i = 0; i < pn.length; i++)
			{
				//pn[i].getProizvod().setKolicina(pn[i].getProizvod().getKolicina() + pn[i].getKolicina());
				s.delete(pn[i]);
			}
			s.delete(n);
		}
		t.commit();
		return true;
	}
	
	public Narudzba dajNarudzbu(int id)
	{
		Transaction t = s.beginTransaction();
		Narudzba n = (Narudzba) s.get(Narudzba.class, id);
		t.commit();
		return n;		
	}
	
	public ArrayList<Narudzba> dajNarudzbe(){
		List<Narudzba> n = s.createCriteria(Narudzba.class).list();
		return new ArrayList<Narudzba>(n);
	}
	
	public boolean kreirajFakturu(Narudzba n)
	{
		Transaction t = s.beginTransaction();
		//Kasnije ce ici if(n.getStatus() == "Potvrđena"
		if(n.getStatus().equals("Na čekanju"))
		{
			//Transaction t = s.beginTransaction();
			Faktura f = new Faktura();
			double ukupnacijena=0;
			f.setAkterprodaje(n.getAkterprodaje());
			f.setImeaktera(n.getAkterprodaje().getIme() + " " + n.getAkterprodaje().getPrezime());
			f.setRegija(n.getAkterprodaje().getRegija());
			f.setImeregije(n.getAkterprodaje().getRegija().getIme());
			f.setDatum(new Date());
			f.setUkupnacijena(0);
			int id = (Integer)s.save(f);
			//f=s.get(Faktura.class, id);
			f = s.get(Faktura.class, id);
			ProizvodNarudzba[] pn = n.getProizvodNarudzbas().toArray(new ProizvodNarudzba[n.getProizvodNarudzbas().size()]);
			for(int i=0; i<pn.length;i++)
			{
				ProizvodFaktura pf = new ProizvodFaktura();
				pf.setFaktura(f);
				
				pf.setProizvod(pn[i].getProizvod());
				pf.setNazivproizvoda(pn[i].getProizvod().getNaziv());
				pf.setNabavnacijena(pn[i].getProizvod().getNabavnacijena());
				pf.setProdajnacijena(pn[i].getProizvod().getProdajnacijena());
				
				pf.setKolicina(pn[i].getKolicina());
				pf.getProizvod().setKolicina(pf.getProizvod().getKolicina() - pf.getKolicina());
				f.getProizvodFakturas().add(pf);
				ukupnacijena = pf.getNabavnacijena() * pf.getKolicina();
				s.save(pf);
			}
			f.setUkupnacijena(ukupnacijena);
			n.setStatus("Potvrđena");
			s.update(f);
			s.update(n);
			t.commit();
			return true;
		}
		else return false;
	}
	
	public Faktura dajFakturu(int id)
	{
		Transaction t = s.beginTransaction();
		Faktura f = (Faktura) s.get(Faktura.class, id);
		t.commit();
		return f;
	}
	
	public ArrayList<Faktura> dajFakture(){
		List<Faktura> f = s.createCriteria(Faktura.class).list();
		return new ArrayList<Faktura>(f);
	}
	
	
	

}
