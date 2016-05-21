package ba.unsa.etf.si.tim8.mlmarketing;

import java.util.Date;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.services.FileThreadServis;
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
		Session session = HibernateUtil.getSessionFactory().openSession();
		SesijaServis.instanciraj(session);
        LoginGUI lg = new LoginGUI(session);
        lg.startLogin();
        Date datum = new Date();
        if(datum.getDate()==1){
        FileThreadServis fs = new FileThreadServis("ime",session);
        fs.start();}
    }
}
