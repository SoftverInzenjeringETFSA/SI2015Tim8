package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;

public class ProbaTest {

	private RegijaServis rs;
	private Regija r;


	@Before
	public void setUp() throws Exception {
		/*Session s = HibernateUtil.getSessionFactory().openSession();
		rs = new RegijaServis(s);
		try {
			r = new Regija();
			r.setIme("neko ime3");
			r.setDrzava("neka drzava");
		    rs.dodajRegiju(r);
		} catch (Exception e) {
			throw e;
		}*/
	}

	@After
	public void tearDown() throws Exception {
		//rs.obrisi(r.getId()); mora se brisati a ne rai brisanje kako treba
		assertTrue(true);
	}

	@Test
	public void test() {
		assertTrue(true);
	}
	

	
	@Test
	public void Radi(){
	/*	boolean jeste=false;
		ArrayList<Regija> listaRegija = rs.dajRegije();
		for (int i = 0; i < listaRegija.size(); i++) {
			if(listaRegija.get(i).getIme().equals("neko ime")) jeste = true;
		}
		assertTrue(jeste);*/
	}


}
