package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;

public class RegijaServis {
	Session s;
	
	public RegijaServis(Session s){
		this.s=s;
	}
	
	public int dodajRegiju(Regija nova){
		Transaction t = s.beginTransaction();
		Regija r = new Regija();
		r.setIme(nova.getIme());
		r.setDrzava(nova.getDrzava());
		int id = (Integer) s.save(r);
		t.commit();
		return id;
	}
	
	public boolean obrisi(int id){
		Transaction t = s.beginTransaction();
		
		
		Regija zaBrisanje = s.get(Regija.class,id);
		if(zaBrisanje!=null)s.delete(zaBrisanje);
		
		t.commit();
		
		return true;
	}
	
	public ArrayList<Regija> dajRegije(){
		List<Regija> r = s.createCriteria(Regija.class).list();
		return new ArrayList<Regija>(r);
	}
	
	public int dajBrojRegija(){
		return s.createCriteria(Regija.class).list().size();
	}
	
	public int dajBrojAkteraZaRegiju(int id){
		Regija r =(Regija) s.get(Regija.class, id);
		return r.getAkterprodajes().size();
	}
}
