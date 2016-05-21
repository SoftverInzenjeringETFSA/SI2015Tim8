package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.mysql.fabric.xmlrpc.base.Array;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodFaktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
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
			if(a.getAkterprodaje()==null){
				Akterprodaje[] niza = a.getAkterprodajes().toArray(new Akterprodaje[a.getAkterprodajes().size()]);
				for(int i=0;i<niza.length;i++){
					Faktura[] nizf = niza[i].getFakturas().toArray(new Faktura[niza[i].getFakturas().size()]);
					for(int j=0;j< nizf.length;j++){
						bonus+=nizf[j].getUkupnacijena();
						ProizvodFaktura[] nizpf = nizf[j].getProizvodFakturas().toArray(new ProizvodFaktura[nizf[j].getProizvodFakturas().size()]);
						for(int k=0;k<nizpf.length;k++) bonus-=nizpf[k].getNabavnacijena()*nizpf[k].getKolicina();
						
					}
				}
				bonus*=0.2;
			}
			else{
				bonus= ukupnaCijena*(-0.2);
			}
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
		else if(tip.equals("Naruceni proizvodi")){
			
			AkterServis aks = new AkterServis(s);
			Akterprodaje a = aks.dajAktera(id);
			
			Date datePocetak = pocetniDatum(mjesec, godina);
			Date dateKraj = krajnjiDatum(mjesec, godina);
			
			/*
			Criteria c = s.createCriteria(Faktura.class);
			c.add(Restrictions.eq("akterprodaje", a));
			c.add(Restrictions.between("datum", datePocetak, dateKraj));
			double ukupnaCijena=0;
			ArrayList<Faktura> listafaktura = new ArrayList<Faktura>(c.list());*/
			
			Criteria c = s.createCriteria(ProizvodFaktura.class);
			c.createAlias("faktura", "f",Criteria.LEFT_JOIN,Restrictions.eq("akterprodaje", a));
			//c.add(Restrictions.eq("akterprodaje", a));
			c.add(Restrictions.between("f.datum", datePocetak, dateKraj));
			//c.createCriteria("proizvodFakturas");
			ArrayList<ProizvodFaktura> listastavkifakture= new ArrayList<ProizvodFaktura>(c.list());
			
			double ukupno=0;
			Object[][]data = new Object[listastavkifakture.size()+4][];
			data[0]= new Object[]{"Radnik:",a.getIme()+" "+a.getPrezime(),"","",""};
			data[1]= new Object[]{"Mjesec:",mjesec.replaceFirst("^0+(?!$)", "")+", "+Integer.toString(godina)};
			data[2]= new Object[]{"Sifra artikla","Naziv artikla","Broj fakture","Kolicina","Isplata bez bonusa"};
			for(int i=0;i<listastavkifakture.size();i++){
				data[3+i]= new Object[]{
						listastavkifakture.get(i).getProizvod().getId(),
						listastavkifakture.get(i).getNazivproizvoda(),
						listastavkifakture.get(i).getFaktura().getId(),
						listastavkifakture.get(i).getKolicina(),
						(listastavkifakture.get(i).getProdajnacijena()-listastavkifakture.get(i).getNabavnacijena())*listastavkifakture.get(i).getKolicina()
				};
				ukupno+=(listastavkifakture.get(i).getProdajnacijena()-listastavkifakture.get(i).getNabavnacijena())*listastavkifakture.get(i).getKolicina();
			}
			data[listastavkifakture.size()+3]=new Object[]{"Ukupno","","","",ukupno};
			return new MyTableModel(data, new String[]{"Izvjestaj","za","narucene","proizvode",""});
		}
		else if(tip.equals("Po proizvodu")){
			
			ProizvodServis ps = new ProizvodServis(s);
			RegijaServis rs = new RegijaServis(s);
			Proizvod p = ps.dajProizvod(id);
			
			Date datePocetak = pocetniDatum(mjesec, godina);
			Date dateKraj = krajnjiDatum(mjesec, godina);
			
			ArrayList<Regija> regije = rs.dajRegije();
			Object data[][] = new Object[rs.dajBrojRegija()+4][];
			data[0]= new Object[]{"Ime proizvoda:",p.getNaziv()};
			data[1]= new Object[]{"Mjesec:",mjesec.replaceFirst("^0+(?!$)", "")+", "+Integer.toString(godina)};
			data[2]= new Object[]{"Regija","Broj narucenih"};
			int ukupno=0;
			System.out.println("jedan");
			for(int i=0;i<regije.size();i++){
				System.out.println("svaki sljedeci");
				Criteria c = s.createCriteria(ProizvodFaktura.class);
				c.add(Restrictions.eq("proizvod", p));
				c.createAlias("faktura", "f");
				c.add(Restrictions.eq("f.regija", regije.get(i)));
				c.add(Restrictions.between("f.datum", datePocetak,dateKraj));
				ArrayList<ProizvodFaktura> pflista = new ArrayList<ProizvodFaktura>(c.list());
				
				
				int kolicina = 0;
				for(int j=0;j<pflista.size();j++){ kolicina+=pflista.get(j).getKolicina();System.out.println("svaki sljedeci");}
				data[3+i]=new Object[]{regije.get(i).getIme(),kolicina};
				ukupno+=kolicina;
			}
			data[rs.dajBrojRegija()+3]= new Object[]{"Ukupno:",ukupno};
			return new MyTableModel(data, new String[]{"Izvjestaj za proizvod",""});
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
	
	public Date pocetniDatumSaDanom(int dan, String mjesec,int godina){
		String stringDan = Integer.toString(dan);
		if(stringDan.length()==1) stringDan="0"+stringDan;
		String pocetak="-"+stringDan+" 00:00:01";
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
	
	public Date krajnjiDatumSaDanom(int dan, String mjesec,int godina){
		
		String stringDan = Integer.toString(dan);
		if(stringDan.length()==1) stringDan="0"+stringDan;
		String pocetak="-"+dan+" 23:59:59";
		
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

	public MyTableModel sumarniIzvjesta(Date datumPocetni, Date datumKrajnji, String tip){
		if(tip.equals("Proizvodi")){
			
			Criteria cproizvodi = s.createCriteria(Proizvod.class);
			ProizvodServis ps = new ProizvodServis(s);
			
			ArrayList<Proizvod> listaProizvoda = new ArrayList<Proizvod>(cproizvodi.list());
			Object data[][] = new Object[listaProizvoda.size()+3][];
			data[0]= new Object[]{"Regija: Sve","","",""};
			data[1]= new Object[]{"Od:","","Do:", ""};
			data[2]= new Object[]{"Sifra proizvoda","Ime proizvoda","Broj prodanih","Zarada"};
			for(int i = 0;i<listaProizvoda.size();i++){
				int brojBrodanih=0;
				Criteria cpf = s.createCriteria(ProizvodFaktura.class);
				cpf.add(Restrictions.eq("proizvod", listaProizvoda.get(i)));
				cpf.createAlias("faktura", "f");
				cpf.add(Restrictions.between("f.datum", datumPocetni,datumKrajnji));
				ArrayList<ProizvodFaktura> listaPFaktura= new ArrayList<ProizvodFaktura>(cpf.list());
				for(int j= 0; j<listaPFaktura.size();j++) brojBrodanih+=listaPFaktura.get(i).getKolicina();
				data[3+i]= new Object[]{
						listaProizvoda.get(i).getId(),
						listaProizvoda.get(i).getNaziv(),
						brojBrodanih,
						brojBrodanih*listaProizvoda.get(i).getNabavnacijena()
				};
			}
			return new MyTableModel(data, new String[]{"Izvjestaj za prozivode","","",""});
		}
		Object[][] data=new Object[1][];
		data[0]=new Object[]{"",""};
		return new MyTableModel(data, new String[]{"",""});
	}
}
