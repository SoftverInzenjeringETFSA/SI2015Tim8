package ba.unsa.etf.si.tim8.mlmarketing.ui;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.FileInputStream;
import java.util.Properties;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	  private static final SessionFactory sessionFactory = buildSessionFactory();

	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	        	java.util.Properties properties = new Properties();
	        	properties.load(new FileInputStream("db.properties"));
	        	return new Configuration().addProperties(properties).configure("hibernate.cfg.xml").buildSessionFactory();
	        }
	        catch (Exception ex) { //Exception ili Throwable ?
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
}