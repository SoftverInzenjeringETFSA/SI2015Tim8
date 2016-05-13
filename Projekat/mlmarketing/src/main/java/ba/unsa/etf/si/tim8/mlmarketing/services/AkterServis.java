package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;

public class AkterServis {
	
	Session s;
	public AkterServis(Session s){
		this.s =s;
	}
	
	public int kreirajAktera(Akterprodaje a)
	{
		Transaction t = s.beginTransaction();
		int id = (Integer)s.save(a);
		t.commit();
		return id;
	}
	
	public boolean izmjeniAktera(Akterprodaje a)
	{
		s.flush();
		s.clear();
		Transaction t = s.beginTransaction();
		s.update(a);
		t.commit();
		s.flush();
		return true;
	}
	
	
	public boolean izbrisiAktera(int id)
	{
		Transaction t = s.beginTransaction();
		Akterprodaje a = (Akterprodaje) s.get(Akterprodaje.class, id);
		if(a!=null)s.delete(a);
		t.commit();
		return true;
	}
	
	public Akterprodaje dajAktera(int id)
	{
		Transaction t = s.beginTransaction();
		Akterprodaje a = (Akterprodaje) s.get(Akterprodaje.class, id);
		t.commit();
		return a;		
	}
	
	public ArrayList<Akterprodaje> dajSveAkterePoTipu(String tip){
		Criteria criteria = s.createCriteria(Akterprodaje.class);	
	
		List<Akterprodaje> akteri = (List<Akterprodaje>)criteria.add(Restrictions.eq("tip", tip)).list();
		return new ArrayList<Akterprodaje>(akteri);
	}
	
	public int dajBrojAktera(){
		return s.createCriteria(Akterprodaje.class).list().size();
	}
	
	public Akterprodaje pronadjiProdavacaBezMenadzera(){
		Criteria c = s.createCriteria(Akterprodaje.class);
		c.add(Restrictions.eq("tip", "prodavac"));
		Akterprodaje a = (Akterprodaje) c.add(Restrictions.isNull("akterprodaje")).uniqueResult();
		return a;
	}
}
