package ba.unsa.etf.si.tim8.mlmarketing.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;

public class SesijaServis {
	Session s;
	Korisnik k;
	
	public SesijaServis(Session s){
		this.s=s;
		k=null;
	}
	
	public boolean prijava(String Username, String Password){
		Criteria criteria = s.createCriteria(Korisnik.class);
		Korisnik k = (Korisnik) criteria.add(Restrictions.eq("username", Username)).uniqueResult();
		if(k.getPassword().equals(Password)){
			this.k=k;
			return true;
		}
		else return false;
	}
	
	public boolean odjava(){
		this.k=null;
		return true;
	}
	
	public Korisnik dajKorisnika(){
		return k;
	}
		
}
