package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class ValidacijeServisTest {
	
	@Test
	public void testValidirajIspravnoIme(){
		
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean ispravnoIme=validacijeS.validirajIme("Tim");
		assertEquals(true,ispravnoIme);
	}
	
	@Test
	public void testNeispravnoIme(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		
		boolean sadrziRazmak=validacijeS.validirajIme("TIMOSAM ostam");
		assertEquals(false,sadrziRazmak);
		
		boolean sadrziBroj=validacijeS.validirajIme("Tim8");
		assertEquals(false,sadrziBroj);
	}
	
	@Test
	public void testValidirajIspravnoPrezime(){
		//String ispravan="Tim";
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean ispravnoPrezime=validacijeS.validirajIme("Timosam");
		assertEquals(true,ispravnoPrezime);
		}
	
	@Test
	public void testNeispravnoPrezime(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean sadrziRazmak=validacijeS.validirajPrezime("TIMOSAM prezime");
		assertEquals(false,sadrziRazmak);
		
		boolean sadrziBroj=validacijeS.validirajIme("Tim8prezime");
		assertEquals(false,sadrziBroj);
		
		boolean sadrziZnakove=validacijeS.validirajIme("Tim$pr=ez#?ime");
		assertEquals(false,sadrziZnakove);
		}
	
	
	@Test
	public void testIspravnBroj(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean cistBroj=validacijeS.daLiJeInt("2314");
		assertEquals(true,cistBroj);
	}
	
	@Test
	public void testNeispravanBroj(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		
		boolean kombinacija=validacijeS.daLiJeInt("23x0y14");
		assertEquals(false,kombinacija);
		
		boolean intSaRazmakom=validacijeS.daLiJeInt(" 1234");
		assertEquals(true,intSaRazmakom);
		
		boolean intSaTackom=validacijeS.daLiJeInt("12.34");
		assertEquals(false,intSaTackom);
	}

	

	
	@Test
	public void testDoubleIspravan(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean ispravanDoubleTackaPoslije=validacijeS.daLiJeDouble("1.");
		boolean ispravanDoubleTackaPrije=validacijeS.daLiJeDouble(".143");
		boolean ispravanDouble=validacijeS.daLiJeDouble("3.14");
		boolean brojInt=validacijeS.daLiJeDouble("34");
		
		assertEquals(true,ispravanDoubleTackaPoslije);
		assertEquals(true,ispravanDoubleTackaPrije);
		assertEquals(true,ispravanDouble);
		assertEquals(true,brojInt);
	}
	
	@Test
	public void testDoubleSaZnakovima(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean doubleSaZnakovima=validacijeS.daLiJeDouble("#214!.s23");
		boolean doubleSaBlank=validacijeS.daLiJeDouble(" 12.4");
		boolean doubleSaDvijeTacke=validacijeS.daLiJeDouble("12.43.14");
		
		assertEquals(false,doubleSaZnakovima);
		assertEquals(true,doubleSaBlank);
		assertEquals(false,doubleSaDvijeTacke);	
	}
	
	@Test
	public void testIspravnEmail(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean ispravanEmail=validacijeS.validirajEmail("tim8@si.etf");
		boolean saViseNivoa=validacijeS.validirajEmail("tim8@si.etf.unsa.ba");
		
		assertEquals(true,ispravanEmail);		
		assertEquals(true,saViseNivoa);
		}
	
	@Test
	public void testNeispravnEmail(){
		ValidacijeServis validacijeS=new ValidacijeServis();
		boolean saZnakovima=validacijeS.validirajEmail("ti#!m8@si.etf");
		boolean saViseUzastopnihTacaka=validacijeS.validirajEmail("tim8@etf.....unsa.aba");
		boolean tackaNaPocetku=validacijeS.validirajEmail(".tim8@etf.ab");
		boolean tackaNaKraju=validacijeS.validirajEmail("tim8@etf.unsa.");
		
		assertEquals(false,saZnakovima);
		assertEquals(false,saViseUzastopnihTacaka);
		assertEquals(false,tackaNaPocetku);
		assertEquals(false,tackaNaKraju);
		
		}
	

}