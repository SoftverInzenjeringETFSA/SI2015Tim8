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
	public MyTableModel pojedinacniIzvjesta(String tip, String mjesec, int godina, int id){
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
			double bonus = 0;
			Akterprodaje[] niza = a.getAkterprodajes().toArray(new Akterprodaje[a.getAkterprodajes().size()]);
			for(int i=0;i<niza.length;i++){
				Faktura[] nizf = niza[i].getFakturas().toArray(new Faktura[niza[i].getFakturas().size()]);
				for(int j=0;j< nizf.length;j++){
					bonus+=nizf[i].getUkupnacijena();
				}
			}
			bonus/=20;
			Object[][]data = new Object[5][];
			data[0]= new Object[]{"Akter prodaje:",a.getIme()+" "+a.getPrezime()};
			data[1]= new Object[]{"Mjesec:",mjesec.replaceFirst("^0+(?!$)", "")+", "+Integer.toString(godina)};
			data[2]= new Object[]{"Iznos isplate:",Double.toString(ukupnaCijena)};
			data[3]=new Object[]{"Bonus:",Double.toString(bonus)};
			data[4]= new Object[]{"Ukupno",Double.toString(bonus+ukupnaCijena)};
			MyTableModel m = new MyTableModel(data, new String[]{"Izvjestaj za isplatu",""});
			System.out.println(ukupnaCijena);
			return m;
		}
		Object[][]data = new Object[6][];
		return new MyTableModel(data, new String[]{"",""});
		
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

	public MyTableModel sumarniIzvjesta(String tip, String mjesec, int godina, int id){
		Object[][] data=new Object[1][];
		data[0]=new Object[]{"",""};
		return new MyTableModel(data, new String[]{"",""});
	}
}
