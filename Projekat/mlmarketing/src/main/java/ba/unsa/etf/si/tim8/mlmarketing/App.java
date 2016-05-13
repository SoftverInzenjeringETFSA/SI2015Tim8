package ba.unsa.etf.si.tim8.mlmarketing;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.ui.HibernateUtil;
import ba.unsa.etf.si.tim8.mlmarketing.ui.LoginGUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
		Session session = HibernateUtil.getSessionFactory().openSession();
		SesijaServis.instanciraj(session);
		/*		
		Transaction t=session.beginTransaction();
		Korisnik k = (Korisnik) session.get(Korisnik.class, 1);
		JOptionPane.showMessageDialog(null, k.getIme());
		t.commit();*/
        LoginGUI lg = new LoginGUI(session);
        lg.startLogin();
    }
}
