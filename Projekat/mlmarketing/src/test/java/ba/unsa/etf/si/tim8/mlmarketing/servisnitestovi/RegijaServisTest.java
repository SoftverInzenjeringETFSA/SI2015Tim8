package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.ui.HibernateUtil;

public class RegijaServisTest {
	Date d = new Date();
	Session s=null;
	Regija regijaM;
	Regija regijaBrisanja;
	public static int _idGlobal; // da mozemo obrisati regiju nakon sto smo je dodali
	
	@Before
	public void setUp(){
		regijaM=new Regija();
		regijaM.setDrzava("Bih");
		regijaM.setIme(d.toString());
		regijaBrisanja=new Regija();
		regijaBrisanja.setIme("atlantska1245");
		regijaBrisanja.setDrzava("Engleska");
		
		try{
			s=HibernateUtil.getSessionFactory().openSession();
			
			
		}catch(Exception e){
			fail("setUP()GRESKA: "+e.getMessage());
		}
		
	}
	
	
	@After
	public void brisiZauzeto(){
		try{
			
		RegijaServis regijaS=new RegijaServis(s);
		
		regijaS.obrisi(_idGlobal);
		s.close();
		}catch(Exception e){
			fail("@after-brisiZauzeto(): "+e.getMessage());
		}
	}
	

	@Test
	public void testDodajRegiju() {
		ArrayList<Regija>  listaRegija = new ArrayList<Regija>();
		// broj regija prije dodavanje nove regije
		//int listaRegijaPrije=listaRegija.size();
		
		try{
		RegijaServis regijaS=new RegijaServis(s);
		listaRegija=regijaS.dajRegije();
		int prije=listaRegija.size();
		
		//dodavanje nove regije------
		 _idGlobal=regijaS.dodajRegiju(regijaM);
		
		// broj regija POSLIJE dodavanje nove regije
		listaRegija=regijaS.dajRegije();
		int poslije=listaRegija.size();
		
		
		// broj regija prije dodavanja nove regije je za jedan manji od broja regija poslije dodavanja!		
		assertEquals((prije+1),poslije);
		
		}catch(Exception e){
			fail("GRESKA"+e.getMessage());
		}
	}

	@Test
	public void testObrisi() {
		RegijaServis regijaS=new RegijaServis(s);
		// dodamo regiju u bazu, a vrati nam se ID regije
		int _idBrisi=regijaS.dodajRegiju(regijaBrisanja);
		
		//brisemo preko _id regije, vraca nam se bool
		boolean obrisanaRegija=regijaS.obrisi(_idBrisi);
		
		assertEquals(true,obrisanaRegija);
	}
		
	

	@Test
	public void testDajRegije() {
		RegijaServis regijaS=new RegijaServis(s);
		ArrayList<Regija> listaRegija=new ArrayList<Regija>();
		// uporedit cemo broj elemenata dajRegije niz sa metodom dajBrojRegija
		try{
			//broj regija iz liste:
			listaRegija=regijaS.dajRegije();
			int brojListaRegija=listaRegija.size();
			
			//broj regija direktno iz metode dajBrojRegija
			int brojRegijaMetoda=regijaS.dajBrojRegija();
			
			assertEquals(brojListaRegija,brojRegijaMetoda);
			
		}catch(Exception e){
			fail("testDajRegije():fail ---"+e.getMessage());
		}
	}

}
