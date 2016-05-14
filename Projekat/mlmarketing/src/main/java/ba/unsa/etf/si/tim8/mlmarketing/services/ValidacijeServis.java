package ba.unsa.etf.si.tim8.mlmarketing.services;

public class ValidacijeServis {
	
	public static boolean  validirajIme(String ime){
		String pattern = "[a-zA-Z]"; 
		
		return ime.matches(pattern);
		
		
	}
	
	/*boolean validirajPrezime(String prezime){
		
	}*/
}
