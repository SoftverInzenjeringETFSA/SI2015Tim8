package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodFaktura;
import ba.unsa.etf.si.tim8.mlmarketing.ui.MyTableModel;
import ba.unsa.etf.si.tim8.mlmarketing.ui.SefProdajeMainGUI;

public class IzvjestajServis {
	private Session s;
	final static Logger logger = Logger.getLogger(SefProdajeMainGUI.class);
	public IzvjestajServis(Session s){
		this.s=s;
	}
	public void pojedinacniIzvjesta(String tip, String mjesec, int godina, int id){
		if(tip.equals("Za isplatu")){
			
			AkterServis aks = new AkterServis(s);
			Akterprodaje a = aks.dajAktera(id);
			
			Date datePocetak = pocetniDatum(mjesec, godina);
			Date dateKraj = krajnjiDatum(mjesec, godina);
			
			Criteria c = s.createCriteria(Faktura.class);
			c.add(Restrictions.eq("akterprodaje", a));
			c.add(Restrictions.between("datum", datePocetak, dateKraj));
			double ukupnaCijena=0;
			ArrayList<Faktura> listafaktura = new ArrayList<Faktura>(c.list());
			for(int i=0;i<listafaktura.size();i++)
			{
				ukupnaCijena+=listafaktura.get(i).getUkupnacijena();
				ProizvodFaktura[] nizpf = listafaktura.get(i).getProizvodFakturas().toArray(new ProizvodFaktura[listafaktura.get(i).getProizvodFakturas().size()] );
				for(int j=0;j< nizpf.length;j++){
					ukupnaCijena-=nizpf[j].getNabavnacijena()*nizpf[j].getKolicina();
				}
			}
			System.out.println(ukupnaCijena);
		}
	}
	
	private Date pocetniDatum(String mjesec,int godina){
		String pocetak="-01 00:00:01";
		String datumPocetni = Integer.toString(godina)+"-"+mjesec+pocetak;
		Date d=new Date();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
			d = dateFormat.parse(datumPocetni);
    		System.out.println(d);
    	}
    	catch(Exception e){
    		logger.error(e);
    	}
		return d;
	}
	
	private Date krajnjiDatum(String mjesec,int godina){
		String pocetak="-28 00:00:01";
		String datumPocetni = Integer.toString(godina)+"-"+mjesec+pocetak;
		Date d=new Date();
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
			d = dateFormat.parse(datumPocetni);
    		System.out.println(d);
    	}
    	catch(Exception e){
    		logger.error(e);
    	}
		return d;
	}

}
