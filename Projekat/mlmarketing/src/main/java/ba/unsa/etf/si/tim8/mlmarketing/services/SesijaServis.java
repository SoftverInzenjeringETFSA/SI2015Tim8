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
		if(k!=null){
			if(SifraServis.provjeriPassword(Password, k.getPassword())){
				System.out.println("to je to");
				korisnik=k;
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public static boolean odjava(){
		korisnik=null;
		return true;
	}
	
	public static String dajUsername(){
		if(korisnik!=null) return korisnik.getUsername();
		else return "";
	}
	
	public static String dajTipKorisnika() {
		if(korisnik!=null) return korisnik.getTip();
		else return "";
	}
		
}
