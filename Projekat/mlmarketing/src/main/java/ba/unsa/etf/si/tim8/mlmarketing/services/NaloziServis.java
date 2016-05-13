package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;

	public class NaloziServis {
	private Session s;
	public NaloziServis(Session s){
		this.s=s;
	}
	
	public int kreirajNalog(Korisnik k) {
		Transaction t = s.beginTransaction();
		int id =(Integer) s.save(k);
		t.commit();
		return id;
	}
	
	public boolean obrisiNalog(int id){
		Transaction t= s.beginTransaction();
		Korisnik zaBrisanje = (Korisnik)s.get(Korisnik.class, id);
		s.delete(zaBrisanje);
		t.commit();
		return true;
	}
	
	public ArrayList<Korisnik> dajSveNaloge(){
		List<Korisnik> korisnici = s.createCriteria(Korisnik.class).list();
		return new ArrayList<Korisnik>(korisnici);
	}
}
