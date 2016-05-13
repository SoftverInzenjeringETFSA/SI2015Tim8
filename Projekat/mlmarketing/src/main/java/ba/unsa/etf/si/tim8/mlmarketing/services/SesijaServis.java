package ba.unsa.etf.si.tim8.mlmarketing.services;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;


public class SesijaServis {
	private static SesijaServis instanca = null;
	private static Session s;
	private static Korisnik korisnik;
	
	protected SesijaServis(Session s){
		this.s=s;
		korisnik=null;
	}
	
	public static SesijaServis instanciraj(Session s){
		if(instanca==null){
			instanca = new SesijaServis(s);
		}
		return instanca;
	}
	
	public static boolean instanciran(){
		return instanca!=null;
	}
	
	public static boolean prijava(String Username, String Password){
		Criteria criteria = s.createCriteria(Korisnik.class);
		Korisnik k = (Korisnik) criteria.add(Restrictions.eq("username", Username)).uniqueResult();
		if(k.getPassword().equals(Password)){
			korisnik=k;
			return true;
		}
		else return false;
	}
	
	public static boolean odjava(){
		korisnik=null;
		return true;
	}
	
	public static String dajUsername(){
		return korisnik.getUsername();
	}
	
	public static String dajTipKorisnika() {
		return korisnik.getTip();
	}
		
}
