package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.ui.SefProdajeMainGUI;

public class FileThreadServis implements Runnable{
	 private Thread t;
	   private String threadName;
	   private Session s;
	   final static Logger logger = Logger.getLogger(FileThreadServis.class);
	   
	   public FileThreadServis( String name, Session s){
		   this.s=s;
	       this.threadName = name;
	   }
	   public void run() {
	      System.out.println("Running " +  threadName );
	      AkterServis aks = new AkterServis(s);
    	  IzvjestajServis is = new IzvjestajServis(s);
    	  ArrayList<Akterprodaje> akteri = aks.dajSveAktere();
	      try {
	    	  for(int i=0;i<akteri.size();i++){
	    		  TableModel tm = is.pojedinacniIzvjesta("Za isplatu", "05", 2016, akteri.get(i).getId());
		    	  Date sad = new Date();
					String stamp = sad.toString();
					stamp=stamp.replaceAll(" ", "");
					stamp=stamp.replaceAll(":", "");
					File f = new File("izvjestajGenerisani"+stamp+".xls");
					  try{
					    FajlUpisServis.exportTable(tm, f);
					  }
					  catch(Exception e)
					  {
					    logger.info(e);
					  }
					  Thread.sleep(1000);
	    	  }				
	     } catch (Exception e) {
	    	 logger.info(e);
	     }
	   }
	   
	   public void start ()
	   {
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}
