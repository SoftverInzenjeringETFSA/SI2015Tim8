package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
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
		Narudzba nova = new Narudzba();
		nova.setAkterprodaje(n.getAkterprodaje());
		nova.setDatum(n.getDatum());
		nova.setStatus(n.getStatus());
		nova.setProizvodNarudzbas(n.getProizvodNarudzbas());
		
		s.save(nova);
		t.commit();
		return true;
	}
	
	public boolean izbrisiNarudzbu(int id)
	{
		Transaction t = s.beginTransaction();
		Narudzba n = (Narudzba) s.get(Narudzba.class, id);
		if(n!=null)s.delete(n);
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

}
