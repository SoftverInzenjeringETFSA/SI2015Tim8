package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.services.NaloziServis;
import ba.unsa.etf.si.tim8.mlmarketing.ui.HibernateUtil;

public class NaloziServisTest {
	Session s;
	NaloziServis ns;
	Korisnik k,ko;
	Integer id;
	ArrayList<Korisnik> sviKorisnici;
	
	@Before
	public void setUp() throws Exception{
		s= HibernateUtil.getSessionFactory().openSession();
		try {
			
			ns= new NaloziServis(s);
			k=new Korisnik("proba","proba","proba","proba","proba","proba@proba.proba","123/456-789","sef");
			k.setId(ns.kreirajNalog(k));
			ko=new Korisnik();
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	// testiramo kreiranje novog korisnika 
	@Test
	public void testKreirajNalog(){
		
		
		
		sviKorisnici= ns.dajSveNaloge();
		Integer testId = 0;
		for(int i=0; i<sviKorisnici.size();i++){
			if( sviKorisnici.get(i).getId()==k.getId() ) testId=k.getId();
		}
		ns.obrisiNalog(k.getId());
		Assert.assertEquals(k.getId(), testId);
		
	}
	
	// test da li povlaci sve korisnike iz baze
	@Test
	public void testSvihNaloga(){
		
		sviKorisnici= ns.dajSveNaloge();
		int a= sviKorisnici.size();
		
		Korisnik kor= new Korisnik("proba1","proba1","proba1","proba1","proba1","proba@proba.proba","123/456-789","sef");
		kor.setId(ns.kreirajNalog(kor));
		
		sviKorisnici= ns.dajSveNaloge();
		int b= sviKorisnici.size();
		
		ns.obrisiNalog(k.getId());
		ns.obrisiNalog(kor.getId());
		Assert.assertEquals(a+1,b);
	}
	
	
	// test brisanja korisnika test prolazi iako baca izuzetak kaze da ne moze obrisati ali obrise iz baze
	@Test
	public void testBrisanja(){
		
		ns.obrisiNalog(k.getId());
		
		ko.setAdresa("test");
		ko.setEmail("test@test.test");
		ko.setIme("test");
		ko.setPassword("test");
		ko.setPrezime("test");
		ko.setTelefon("098/765-432");
		ko.setTip("prodavac");
		ko.setUsername("test");
		ko.setId(ns.kreirajNalog(k));

		
		boolean obrisan= ns.obrisiNalog(ko.getId());
		
		Assert.assertEquals(true, obrisan);
	}
	
	
}
