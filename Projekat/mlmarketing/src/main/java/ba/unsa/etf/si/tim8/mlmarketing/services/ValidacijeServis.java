package ba.unsa.etf.si.tim8.mlmarketing.services;

import org.apache.log4j.Logger;

public class ValidacijeServis {
	final static Logger logger = Logger.getLogger(ValidacijeServis.class);
	public static boolean  validirajIme(String ime){
		if(ime.equals("") || ime.length()< 3)
			return false;
		String pattern = "[a-zA-ZšŠćĆĐđŽžČč]+"; 		
		return ime.matches(pattern);		
	}
	
	public static boolean validirajPrezime(String prezime){
		if(prezime.equals("") || prezime.length() < 3)
			return false;
		String pattern = "[a-zA-ZšŠćĆĐđŽžČč]+"; 		
		return prezime.matches(pattern);
	}
	
	public static boolean daLiJeInt(String broj)
	{
		broj = broj.trim();
		int duzina = broj.length();		
		if(broj == null || duzina == 0)
			return false;
		for(int i = 0; i < duzina; i++)
		{
			char c = broj.charAt(i);
			if(c < '0' || c > '9')
			{
				return false;
			}
		}
		return true;
	}
	
	public static boolean daLiJeDouble(String broj)
	{
		broj = broj.trim();
		if(daLiJeInt(broj)) return true;
		String pattern = "([0-9]*)\\.([0-9]*)";
		return broj.matches(pattern);
	}
	
	public static boolean nabavnaManjaOdProdajne(String nabavna, String prodajna)
	{
		double nabavnaCijena = 0;
		double prodajnaCijena = 0;
		if(daLiJeDouble(nabavna) && daLiJeDouble(prodajna))
		{
			nabavnaCijena = Double.parseDouble(nabavna);
			prodajnaCijena = Double.parseDouble(prodajna);
			return nabavnaCijena < prodajnaCijena;
		}
		else return false;			
		
	}
	
	public static boolean validirajNazivProizvoda(String naziv)
	{
		String noviNaziv = naziv.trim();
		if(noviNaziv.equals(""))
			return false;
		String pattern = "[a-zA-Z0-9šŠćĆĐđŽžČč ]*";
		return noviNaziv.matches(pattern);
		
	}
	
	public static boolean validirajEmail(String email)
	{
		if(email.equals(""))
			return false;
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return email.matches(pattern);
	}
	
	public static boolean validirajBrojTelefona(String broj)
	{
		if(broj.equals("") || broj.length() < 6)
			return false;
		String pattern = "[0-9]+";
		return broj.matches(pattern);
	}
	
	public static boolean validirajUsername(String username)
	{
		if(username.equals(""))
			return false;
		String pattern = "[a-zA-Z0-9_.-]{3,}";
		return username.matches(pattern);
	}
	
	public static boolean validirajPassword(String pass, String ponoviPass)
	{
		if(pass.equals("")|| pass.contains(" "))
			return false;
		return pass.equals(ponoviPass);
	}
	
	public static boolean validirajAdresu(String adresa)
	{
		String novaAdresa = adresa.trim();
		if(novaAdresa.equals(""))
			return false;
		return true;
	}
	
	public static boolean validirajRegiju(String regija)
	{
		if(regija.equals(""))
			return false;
		String pattern = "[a-zA-ZšŠćĆĐđŽžČč ]*";
		return regija.matches(pattern);
	}
	
	public static int dajBrojDana(String mjesec)
	{
		int[] dani = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int broj = 0;
		try{
			broj = Integer.parseInt(mjesec);			
			
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		
		return dani[broj];
	}
	
	
}
