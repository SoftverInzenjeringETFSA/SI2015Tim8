package ba.unsa.etf.si.tim8.mlmarketing.services;

public class ValidacijeServis {
	
	public static boolean  validirajIme(String ime){
		String pattern = "[a-zA-ZšŠćĆĐđŽžČč]+"; 		
		return ime.matches(pattern);		
	}
	
	public static boolean validirajPrezime(String prezime){
		String pattern = "[a-zA-ZšŠćĆĐđŽžČč]+"; 		
		return prezime.matches(pattern);
	}
	
	public static boolean daLiJeInt(String broj)
	{
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
		if(daLiJeInt(broj)) return true;
		String pattern = "([0-9]*)\\.([0-9]*)";
		return broj.matches(pattern);
	}
	
	public static boolean validirajEmail(String email)
	{
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		return email.matches(pattern);
	}
}
