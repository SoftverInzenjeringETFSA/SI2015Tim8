package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim8.mlmarketing.services.SifraServis;

public class SifraServisTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHeshiranja() {
		assertNotEquals("password", SifraServis.heshirajPassword("password"));
	}
	
	@Test
	public void testHeshiranja2(){
		assertNotNull(SifraServis.heshirajPassword("password"));
	}
	
	@Test
	public void testProvjereDigesta(){
		String hash = SifraServis.heshirajPassword("password");
		assertTrue(SifraServis.provjeriPassword("password", hash));
	}

	@Test(expected=Exception.class)
	public void testProvjereDigesta2() throws Exception{
		assertTrue(SifraServis.provjeriPassword("password", "password"));
	}
}
