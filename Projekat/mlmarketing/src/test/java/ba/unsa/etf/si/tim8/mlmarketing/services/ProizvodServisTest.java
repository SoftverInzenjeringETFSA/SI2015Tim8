
package ba.unsa.etf.si.tim8.mlmarketing.services;

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
	 Proizvod proizvodM;
		Proizvod proizvodM1;
		Proizvod proizvodM2;
		Proizvod proizvodM3;
		Proizvod proizvodM4;
	
	@Before
	public void setUp(){
			 s = HibernateUtil.getSessionFactory().openSession();
			 proizvodS=new ProizvodServis(s);
			 proizvodM=new Proizvod("karmin22_test",23.10,56.95,110);
			 
			 proizvodM1=new Proizvod("krema_test1",23.10,56.95,110);
			 proizvodM2=new Proizvod("sampon_test",13.0,31.95,110);
			 proizvodM3=new Proizvod("balzam_test",20.40,36.95,110);
			 proizvodM4=new Proizvod("lak_test",4.10,16.95,110);
	}
	
	@After
	public void brisiZauzeto(){
		try{
			proizvodS.obrisiProizvod(proizvodM1);
			proizvodS.obrisiProizvod(proizvodM2);
			proizvodS.obrisiProizvod(proizvodM3);
			proizvodS.obrisiProizvod(proizvodM4);
			s.close();
			
		}catch(Exception e){
			fail("@After metoda, brisiZauzeto(): "+e.getMessage());
		}
	}



	@Test
	public void testDajProizvod() {
		int id=proizvodS.kreirajProizvod(proizvodM1);
		Proizvod artikal=new Proizvod();
		artikal=proizvodS.dajProizvod(id);
		assertEquals(proizvodM1,artikal);
	}

	@Test
	public void testKreirajProizvod() {
		
		
		
		//ArrayList<Proizvod>  listaProizvoda = new ArrayList<Proizvod>();
		try{			
			// DODAVANJE proizvoda u servis, vraca se _id
			int _id =proizvodS.kreirajProizvod(proizvodM);
			
			//dodavanje proizvoda u listu za brisanje
			proizvodiZaBrisanje.add(proizvodS.dajProizvod(_id));
			
			Proizvod proizvodPoredjenja=new Proizvod();
			proizvodPoredjenja=proizvodS.dajProizvod(_id);
			assertEquals(proizvodM,proizvodPoredjenja);
			
			
			// lista sa svim proizvodima ukljucujuci dodani
			//listaProizvoda=proizvodS.dajSveProizvode();
			
			boolean obrisan=proizvodS.obrisiProizvod(proizvodPoredjenja);
			assertEquals(true,obrisan);
			
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
			int brPrije=listaProizvoda.size();
			
			// DODAVANJE proizvoda u servis
			proizvodS.kreirajProizvod(proizvodM1);
			proizvodS.kreirajProizvod(proizvodM2);
			proizvodS.kreirajProizvod(proizvodM3);
			proizvodS.kreirajProizvod(proizvodM4);
			
			// lista sa svim proizvodima ukljucujuci dodani
			listaProizvoda=proizvodS.dajSveProizvode();
			int brPoslije=listaProizvoda.size();
			// uporedimo staru listu sa novom listom uvecanom za 4 nova proizvoda
			assertEquals((brPrije+4),brPoslije);
		}catch(Exception e){
			fail("greska-> "+e.getMessage());
		}
			
		
	}

	@Test
	public void testObrisiProizvod() {
		
		ArrayList<Proizvod>  listaProizvoda = new ArrayList<Proizvod>();
		Proizvod proizvodM=new Proizvod("kremaZaSuncanje_test1",13.10,36.95,110);
		
		try{
			// dodajemo proizvod u listu, i dobijemo broj elemenata sa ubacenim prozivodom
			proizvodS.kreirajProizvod(proizvodM);
			
			listaProizvoda=proizvodS.dajSveProizvode();
			int brPrije=listaProizvoda.size();
			
			//brisemo proizvod
			proizvodS.obrisiProizvod(proizvodM);
			
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
