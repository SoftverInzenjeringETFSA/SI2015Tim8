package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;

public class AkterServis {
	
	Session s;
	public AkterServis(Session s){
		this.s =s;
	}
	
	public boolean kreirajAktera(Akterprodaje a)
	{
		Transaction t = s.beginTransaction();
		Akterprodaje novi = new Akterprodaje();
		novi.setIme(a.getIme());
		novi.setPrezime(a.getPrezime());
		novi.setAdresa(a.getAdresa());
		novi.setEmail(a.getEmail());
		novi.setBrojtelefona(a.getBrojtelefona());
		novi.setTip(a.getTip());
		novi.setRegija(a.getRegija());
		
		s.save(novi);
		t.commit();
		return true;
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
		Transaction t = s.beginTransaction();
		Akterprodaje a = (Akterprodaje) s.get(Akterprodaje.class, id);
		if(a!=null)s.delete(a);
		t.commit();
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
	
	

}
