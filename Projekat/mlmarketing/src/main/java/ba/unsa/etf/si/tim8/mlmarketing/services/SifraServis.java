package ba.unsa.etf.si.tim8.mlmarketing.services;

import org.mindrot.jbcrypt.BCrypt;

public class SifraServis {
	// Define the BCrypt workload to use when generating password hashes. 10-31 is a valid value.
	private static int workload = 12;

	public static String heshirajPassword(String neheshiranPassword) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(neheshiranPassword, salt);

		return(hashed_password);
	}


	public static boolean provjeriPassword(String neheshiranPassword, String heshiraniPassword) {
		boolean password_verified = false;

		if(null == heshiraniPassword || !heshiraniPassword.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(neheshiranPassword, heshiraniPassword);

		return(password_verified);
	}

}
