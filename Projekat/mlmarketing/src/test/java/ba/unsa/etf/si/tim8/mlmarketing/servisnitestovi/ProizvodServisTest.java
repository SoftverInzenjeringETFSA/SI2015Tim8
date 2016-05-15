package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;
import ba.unsa.etf.si.tim8.mlmarketing.ui.HibernateUtil;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ProizvodServisTest {
	ArrayList<Proizvod> proizvodiZaBrisanje=new ArrayList<Proizvod>(); // lista za brisanje
	 Session s;
	 ProizvodServis proizvodS;
	 	
	 	Proizvod proizvodM1;
		Proizvod proizvodM2;
		Proizvod proizvodM3;
		int id1;
		int id2;
		int id3;

	
	@Before
	public void setUp(){
			 s = HibernateUtil.getSessionFactory().openSession();
			 proizvodS=new ProizvodServis(s);
			 
			 proizvodM1=new Proizvod("karmin22_test1",23.10,56.95,110);
			 proizvodM1.setId(proizvodS.kreirajProizvod(proizvodM1));
			 id1=proizvodM1.getId();
			 
			 proizvodM2=new Proizvod("krema_test12",23.10,56.95,110);
			 proizvodM2.setId(proizvodS.kreirajProizvod(proizvodM2));
			 id2=proizvodM2.getId();
			 
			
			 proizvodM3=new Proizvod("sampon_test2",13.0,31.95,110);
			 proizvodM3.setId(proizvodS.kreirajProizvod(proizvodM3));
			 id3=proizvodM3.getId();
	}
	
	@After
	public void brisiZauzeto(){
		try{
			proizvodM1=proizvodS.dajProizvod(id1);
			proizvodM2=proizvodS.dajProizvod(id2);
			proizvodM3=proizvodS.dajProizvod(id3);
			
			proizvodS.obrisiProizvod(proizvodM1);
			proizvodS.obrisiProizvod(proizvodM2);
			proizvodS.obrisiProizvod(proizvodM3);
			
			
			
		}catch(Exception e){
			fail("@After metoda, brisiZauzeto(): "+e.getMessage());
		}
	}



	@Test
	public void testDajProizvod() {
		Proizvod artikal=new Proizvod();
	    artikal =proizvodS.dajProizvod(id1);
		assertEquals(proizvodM1,artikal);
	}

	@Test
	public void testKreirajProizvod() {
		
		
		
		ArrayList<Proizvod>  listaProizvoda = new ArrayList<Proizvod>();
		try{			
			// kreiran proizvod u before metodi...
			
			int _id =proizvodM1.getId();
			
			
			Proizvod proizvodPoredjenja=new Proizvod();
			proizvodPoredjenja=proizvodS.dajProizvod(_id);
			assertEquals(proizvodM1,proizvodPoredjenja);
			
			
		}catch(Exception e){
			fail("greska-> "+e.getMessage());
		}
		
	}

	@Test
	public void testDajSveProizvode() {
		/*public Proizvod(String naziv, double nabavnacijena, double prodajnacijena, int kolicina)*/

		
		ArrayList<Proizvod>  listaProizvoda = new ArrayList<Proizvod>();
		
		try{
			
			listaProizvoda=proizvodS.dajSveProizvode();
			int brojProizvoda=listaProizvoda.size();
			

			
			// ako je broj proizvoda veci ili jednak 3, onda su nam vraceni svi proizvodi
			assertEquals(true,(brojProizvoda>=3));
		}catch(Exception e){
			fail("greska-> "+e.getMessage());
		}
			
		
	}

	@Test
	public void testObrisiProizvod() {
		
		ArrayList<Proizvod>  listaProizvoda = new ArrayList<Proizvod>();
		Proizvod proizvodLokal=new Proizvod("kremaZaSunca5nje_test1",13.10,36.95,110);
		
		try{
			// dodajemo proizvod u listu, i dobijemo broj elemenata sa ubacenim prozivodom
			int id_lokal=proizvodS.kreirajProizvod(proizvodLokal);
			proizvodLokal=proizvodS.dajProizvod(id_lokal);
			
			listaProizvoda=proizvodS.dajSveProizvode();
			int brPrije=listaProizvoda.size();
			
			//brisemo proizvod
			proizvodS.obrisiProizvod(proizvodLokal);
			
			// lista poslije brisanja , trebala bi biti umanjena za jedan prozivod
			listaProizvoda=proizvodS.dajSveProizvode();
			int brPoslije=listaProizvoda.size();
			
			// uporedimo staru liste prije i poslije brisanja
			assertEquals(brPrije,brPoslije+1);
		}catch(Exception e){
			
			fail("greska-> "+e.getMessage());
		}
	}

	@Test
	public void testIzmijeniProizvod() {
		// ubacujemo proizvod u bazu
		int idArtikla=proizvodS.kreirajProizvod(proizvodM3);
		
		// povlacimo proizvod iz baze
		Proizvod proizvodIzmjena=new Proizvod();
		proizvodIzmjena=proizvodS.dajProizvod(idArtikla);
		
		// mijenjamo podatke nad proizvodom
		proizvodIzmjena.setKolicina(222);
		proizvodIzmjena.setProdajnacijena(32.5);
		
		// vrsimo izmjenu nad proizvodom, i vraca nam true, ukoliko smo uspjesno izvrsili
		boolean izmjena=proizvodS.izmijeniProizvod(proizvodIzmjena);
		assertEquals(true,izmjena);
		
		
	}

	
	
}
