package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import java.util.ArrayList;
import static org.junit.Assert.*;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.mysql.fabric.xmlrpc.base.Array;

import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NaloziServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.ui.HibernateUtil;


public class SesijaServisTest {
	Session s;
	Korisnik k;
	SesijaServis ss;
	NaloziServis ns;
	@Before
	public void setUp() throws Exception{
		s= HibernateUtil.getSessionFactory().openSession();
		try {
			ss.instanciraj(s);
			

			// kreiranje korisnika
			ns= new NaloziServis(s);
			k= new Korisnik("test","test","test","test","test","test@test.test","123/456-789","prodavac");
			k.setId(ns.kreirajNalog(k));
			
			System.out.println(k.getId());
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
	// Testiranje prijave korisnika
	@Test
	public void testPrijave() throws Exception{
		try {
			
			boolean prijava = ss.prijava(k.getUsername(),k.getUsername());
			ns.obrisiNalog(k.getId());
			Assert.assertEquals(true, prijava);
		} catch (Exception e) {
			fail("testPrijave: "+e.getMessage());
		}
		
		
	}
	
	
	// pokusaj prijave za nepostojeceg korisnika
	@Test
	public void testpogresnaPrijava(){
		boolean prijava= ss.prijava("proba", "proba");
		ns.obrisiNalog(k.getId());
		Assert.assertEquals(false, prijava);
	}
	
	
	
	@Test
	public void testKorisnika() throws Exception{
		try {
			ss.prijava(k.getUsername(), k.getPassword());
			String tun= ss.dajUsername();
			String un= k.getUsername();
			ns.obrisiNalog(k.getId());
			Assert.assertEquals(k.getUsername(), un);
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
	@Test
	public void testOdjave(){
		ss.prijava(k.getUsername(), k.getPassword());
		boolean odjava= ss.odjava();
		ns.obrisiNalog(k.getId());
		Assert.assertEquals(true, odjava);
	}
	
	
	
}
