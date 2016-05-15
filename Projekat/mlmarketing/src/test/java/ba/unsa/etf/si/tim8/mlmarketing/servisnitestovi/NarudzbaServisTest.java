package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodNarudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.ui.HibernateUtil;

public class NarudzbaServisTest {
	
	Session s;
	
	NarudzbaServis ns;
	Narudzba n;
	ArrayList<Narudzba> sveNarudzbe;
	ProizvodNarudzba pn;
	
	Faktura f;
	ArrayList<Faktura> listaFaktura;
	
	Proizvod p;
	ProizvodServis ps;
	
	Regija r;
	RegijaServis rs;
	
	Akterprodaje ap;
	AkterServis as;
	
	Date datum;
	
	@Before
	public void setUp(){
		s= HibernateUtil.getSessionFactory().openSession();
		try {
			ns= new NarudzbaServis(s);
			f= new Faktura();
			
			// prvo dodamo regiju
			rs = new RegijaServis(s);
			r= new Regija("Kanton Sarajevo", "Bosna i Hercegovina");
			r.setId(rs.dodajRegiju(r));
			
			// dodamo akteraProdaje
			as= new AkterServis(s);
			ap= new Akterprodaje(r, "test", "test", "test", "test", "test@test.test", "212/213-231");
			ap.setId(as.kreirajAktera(ap));
			
			datum= new Date(2016,5,15);
			
			n= new Narudzba(ap,datum,"Fakturisana");
			
			ps= new ProizvodServis(s);
			p= new Proizvod("krema", 2.3, 2.6, 10);
			p.setId(ps.kreirajProizvod(p));
			
		} catch (Exception e) {
			fail("Greska u setUp() metodi: "+e.getMessage());
		}
	}
	
	@Test
	public void testKreirajNarudzbu(){
		boolean kreirana=ns.kreirajNarudzbu(n);
		Assert.assertEquals(true, kreirana);
	}
	
	@Test
	public void testDodajProizvod(){
		ns.kreirajNarudzbu(n);
		pn= new ProizvodNarudzba(n,p,3);
		boolean dodana= ns.dodajProizvod(pn);
		Assert.assertEquals(dodana, true);
	}
	
	@Test
	public void testDajNarudzbu(){
		ns.kreirajNarudzbu(n);
		sveNarudzbe= ns.dajNarudzbe();
		int id= sveNarudzbe.get(0).getId();
		Assert.assertEquals(n.getAkterprodaje(), ap);
	}
	
	@Test 
	public void testDajNarudzbe(){
		ns.kreirajNarudzbu(n);
		sveNarudzbe= ns.dajNarudzbe();
		int a= sveNarudzbe.size();
		Narudzba na= new Narudzba(ap,datum,"Fakturisana");
		ns.kreirajNarudzbu(na);
		sveNarudzbe= ns.dajNarudzbe();
		int b= sveNarudzbe.size();
		Assert.assertEquals(a+1, b);
	}
	
	@Test
	public void testBrisanjaNarudzbe(){
		ns.kreirajNarudzbu(n);
		sveNarudzbe= ns.dajNarudzbe();
		int id= sveNarudzbe.get(0).getId();
		boolean obrisana = ns.izbrisiNarudzbu(id);
		Assert.assertEquals(true, obrisana);
	}
	
	@Test
	public void testPromjeneStanjaNarudzbe()
	{
		ns.kreirajNarudzbu(n);
		boolean promjenjena = ns.izmijeniStatusNarudzbe(n, "Odbijena");
		Assert.assertEquals(true, promjenjena);
	}
	
	@Test
	public void testKreiranjaFakture(){
		n.setStatus("Potvrđena");
		ns.kreirajNarudzbu(n);
		pn= new ProizvodNarudzba(n,p,3);
		ns.dodajProizvod(pn);
		boolean krirana = ns.kreirajFakturu(n);
		ns.izmijeniStatusNarudzbe(n, "Odbijena");
		Assert.assertEquals(true, krirana);
	}
	
	@Test
	public void testDajFakturu(){
		n.setStatus("Potvrđena");
		ns.kreirajNarudzbu(n);
		pn= new ProizvodNarudzba(n,p,3);
		ns.dodajProizvod(pn);
		ns.kreirajFakturu(n);
		ns.izmijeniStatusNarudzbe(n, "Odbijena");
		listaFaktura= ns.dajFakture();
		int vel	= listaFaktura.size()-1;
		int id = listaFaktura.get(vel).getId();
		f= ns.dajFakturu(id);
		Assert.assertEquals(f.getAkterprodaje().getIme(),n.getAkterprodaje().getIme());
	}
	
	@Test
	public void testDajSveFakture(){
		n.setStatus("Potvrđena");
		ns.kreirajNarudzbu(n);
		pn= new ProizvodNarudzba(n,p,3);
		ns.dodajProizvod(pn);
		ns.kreirajFakturu(n);
		ns.izmijeniStatusNarudzbe(n, "Odbijena");
		listaFaktura= ns.dajFakture();
		int a= listaFaktura.size();
		Narudzba na= new Narudzba(ap, datum, "Potvrđena");
		ns.kreirajNarudzbu(na);
		pn= new ProizvodNarudzba(na,p,3);
		ns.dodajProizvod(pn);
		ns.kreirajFakturu(na);
		ns.izmijeniStatusNarudzbe(na, "Odbijena");
		listaFaktura= ns.dajFakture();
		int b= listaFaktura.size();
		Assert.assertEquals(a+1, b);
	}
	
	@After
	public void tearDown(){
		try {
			
			sveNarudzbe= ns.dajNarudzbe();
			for(int i=0; i<sveNarudzbe.size(); i++){
				ns.izbrisiNarudzbu(sveNarudzbe.get(i).getId());
			}
			ps.obrisiProizvod(p);
			as.izbrisiAktera(ap.getId());
			rs.obrisi(r.getId());
			
		} catch (Exception e) {
			fail("Greska u tearDown() metodi: "+e.getMessage());
		}
	}
	
}
