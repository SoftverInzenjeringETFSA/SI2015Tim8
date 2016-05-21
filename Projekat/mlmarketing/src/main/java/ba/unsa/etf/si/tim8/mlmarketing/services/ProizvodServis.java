package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodFaktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodNarudzba;

public class ProizvodServis {
	
	Session s;
	
	public ProizvodServis(Session s){
		this.s=s;
	}
	
	public Proizvod dajProizvod(int id){
		Transaction t = s.beginTransaction();
		Proizvod p = s.get(Proizvod.class, id);
		t.commit();
		return p;
	}
	
	public int kreirajProizvod(Proizvod p){
		Transaction t = s.beginTransaction();
		int id = (Integer)s.save(p);
		t.commit();
		s.flush();
		s.clear();
		return id;
	}
	
	public ArrayList<Proizvod> dajSveProizvode(){
		List<Proizvod> proizvodi = s.createCriteria(Proizvod.class).list();
		return new ArrayList<Proizvod>(proizvodi);
	}
	
	public boolean obrisiProizvod(Proizvod p){
		NarudzbaServis ns = new NarudzbaServis(s);
		Criteria cn = s.createCriteria(Narudzba.class);
		cn.createCriteria("proizvodNarudzbas").add(Restrictions.eq("proizvod",p));
		List<Narudzba> listanarudzbi = cn.list();
		ArrayList<Narudzba> ilistanarudzbi = new ArrayList<Narudzba>(listanarudzbi);
		for(int i=0;i<ilistanarudzbi.size();i++) ns.izbrisiNarudzbu(ilistanarudzbi.get(i).getId());
		
		Criteria cf = s.createCriteria(ProizvodFaktura.class).add(Restrictions.eq("proizvod", p));
		List<ProizvodFaktura> listafaktura = cf.list();
		ArrayList<ProizvodFaktura> ilistafaktura = new ArrayList<ProizvodFaktura>(listafaktura);
		for(int i = 0;i<ilistafaktura.size();i++){
			ilistafaktura.get(i).setProizvod(null);
			s.update(ilistafaktura.get(i));
		}
		
		Transaction t = s.beginTransaction();
		s.delete(p);
		t.commit();
		return true;
	}
	
	public boolean izmijeniProizvod(Proizvod p){
		s.flush();
		s.clear();
		Transaction t = s.beginTransaction();
		s.update(p);
		t.commit();
		s.flush();
		return true;
	}
	
	public boolean moguceObrisati(Proizvod p){
		Criteria c = s.createCriteria(ProizvodNarudzba.class).add(Restrictions.eq("proizvod", p));
		c.createCriteria("narudzba").add(Restrictions.eq("status","Potvrđena"));
		List nesto = c.list();
		return (nesto.size()==0);
	}
	
	public boolean daLiPostojiProizvod(String nazivProizvoda)
	{
		Criteria c = s.createCriteria(Proizvod.class);
		if(c.add(Restrictions.eq("naziv", nazivProizvoda)).uniqueResult()== null)
			return false;
		return true;
	}
}
