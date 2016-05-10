package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;

public class ProizvodServis {
	
	Session s;
	
	public ProizvodServis(Session s){
		this.s=s;
	}
	
	public Proizvod dajProizvod(int id){
		Transaction t = s.beginTransaction();
		Proizvod p = s.get(Proizvod.class, id);
		t.commit();
		return p;
	}
	
	public boolean kreirajProizvod(Proizvod p){
		Transaction t = s.beginTransaction();
		s.save(p);
		t.commit();
		return true;
	}
	
	public ArrayList<Proizvod> dajSveProizvode(){
		List<Proizvod> proizvodi = s.createCriteria(Proizvod.class).list();
		return new ArrayList<Proizvod>(proizvodi);
	}
	
	
}
