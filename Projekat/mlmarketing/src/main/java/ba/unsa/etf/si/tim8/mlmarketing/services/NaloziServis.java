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
		//Prosljedjuje se nalog sa obicnim passwordom, koji se onda reheshira i postavlja!
		Transaction t = s.beginTransaction();
		k.setPassword(SifraServis.heshirajPassword(k.getPassword()));
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
	
	public boolean zauzetKorisnik(String username){
		Criteria c = s.createCriteria(Korisnik.class);
		if(c.add(Restrictions.eq("username", username)).uniqueResult()==null) return false;
		else return true;
	}
}
