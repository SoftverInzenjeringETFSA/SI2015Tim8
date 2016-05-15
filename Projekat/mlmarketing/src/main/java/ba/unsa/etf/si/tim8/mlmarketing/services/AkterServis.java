package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;

public class AkterServis {
	
	Session s;
	public AkterServis(Session s){
		this.s =s;
	}
	
	public int kreirajAktera(Akterprodaje a)
	{
		Transaction t = s.beginTransaction();
		int id = (Integer)s.save(a);
		if(a.getAkterprodaje() != null){
			s.update(a.getAkterprodaje());
		}
		t.commit();
		s.flush();
		s.clear();
		return id;
	}
	
	public boolean izmjeniAktera(Akterprodaje a)
	{
		s.flush();
		s.clear();
		Transaction t = s.beginTransaction();
		s.update(a);
		t.commit();
		s.flush();
		return true;
	}
	
	
	public boolean izbrisiAktera(int id)
	{
		NarudzbaServis ns = new NarudzbaServis(s);
		Akterprodaje a = (Akterprodaje) s.get(Akterprodaje.class, id);
		
		Criteria cn = s.createCriteria(Narudzba.class);
		cn.add(Restrictions.eq("akterprodaje", a));
		List<Narudzba> listanarudzbi = cn.list();
		if(!listanarudzbi.isEmpty()){
			ArrayList<Narudzba> ilistanarudzbi= new ArrayList<Narudzba>(listanarudzbi);
			for(int i=0;i<ilistanarudzbi.size();i++)  ns.izbrisiNarudzbu(ilistanarudzbi.get(i).getId());
		}
		
		
		Criteria cf = s.createCriteria(Faktura.class);
		cf.add(Restrictions.eq("akterprodaje", a));
		List<Faktura> listafaktura = cf.list();
		if(!listafaktura.isEmpty()){
			ArrayList<Faktura> ilistafaktura = new ArrayList<Faktura>(listafaktura);
			for(int i = 0; i<ilistafaktura.size();i++){
				ilistafaktura.get(i).setAkterprodaje(null);
				s.update(ilistafaktura.get(i));
			}
		}
		
		
		Transaction t = s.beginTransaction();
		if(a!=null)s.delete(a);
		t.commit();
		s.flush();
		s.clear();
		return true;
	}
	
	public Akterprodaje dajAktera(int id)
	{
		Transaction t = s.beginTransaction();
		Akterprodaje a = (Akterprodaje) s.get(Akterprodaje.class, id);
		t.commit();
		return a;		
	}
	
	public ArrayList<Akterprodaje> dajSveAkterePoTipu(String tip){
		Criteria criteria = s.createCriteria(Akterprodaje.class);	
	
		List<Akterprodaje> akteri = (List<Akterprodaje>)criteria.add(Restrictions.eq("tip", tip)).list();
		return new ArrayList<Akterprodaje>(akteri);
	}
	
	public int dajBrojAktera(){
		return s.createCriteria(Akterprodaje.class).list().size();
	}
	
	public Akterprodaje pronadjiProdavacaBezMenadzera(){
		Criteria c = s.createCriteria(Akterprodaje.class);
		c.add(Restrictions.eq("tip", "prodavac"));
		Akterprodaje a = (Akterprodaje) c.add(Restrictions.isNull("akterprodaje")).uniqueResult();
		return a;
	}
	
	public boolean moguceBrisanje(Akterprodaje a){
		Criteria c = s.createCriteria(Narudzba.class);
		c.add(Restrictions.eq("akterprodaje", a));
		c.add(Restrictions.eq("status", "Potvrđena"));
		List<Narudzba> lista = c.list();
		if(lista == null) return true;
		if(lista.size()==0) return true;
		return false;
	}
	
	public boolean daLiJeNadlezanZaMaxBroj(Akterprodaje a)
	{
		Transaction t = s.beginTransaction();
		Akterprodaje ak = (Akterprodaje) s.get(Akterprodaje.class, a.getId());
		t.commit();
		if(ak.getAkterprodajes().size() == 10)
			return true;
		return false;
	}
}
