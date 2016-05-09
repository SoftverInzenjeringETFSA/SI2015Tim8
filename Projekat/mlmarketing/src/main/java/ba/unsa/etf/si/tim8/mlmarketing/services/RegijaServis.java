package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;

public class RegijaServis {
	Session s;
	
	public RegijaServis(Session s){
		this.s=s;
	}
	
	public boolean dodajRegiju(Regija nova){
		Transaction t = s.beginTransaction();
		/*
		String hql = "Select new ba.unsa.etf.si.tim12.bll.viewmodel.MaterijalVM(p.id, p.naziv, p.mjernaJedinica,p.cijena) "
				+ "FROM Materijal p WHERE p.naziv= :NoviMaterijal";		
        Query q = s.createQuery(hql);
        q.setString("NoviMaterijal",NoviMaterijal.getNaziv());
		
        List<MaterijalVM> nesto =  (List<MaterijalVM>) q.list();
        //Vidi da li ovdje treba bacati Exception ili returnati FALSE 
			if(!nesto.isEmpty() )
				return false;
		//throw new RuntimeException ("Materijal sa imenom već postoji!");
		*/
		
		Regija r = new Regija();
		r.setIme(nova.getIme());
		r.setDrzava(nova.getDrzava());
		
		s.save(r);
		t.commit();
		return true;
	}
	
	public boolean obrisi(int id){
		Transaction t = s.beginTransaction();
		
		
		Regija zaBrisanje = s.get(Regija.class,id);
		if(zaBrisanje!=null)s.delete(zaBrisanje);
		
		t.commit();
		
		return true;
	}
	
	public ArrayList<Regija> dajRegije(){
		List<Regija> r = s.createCriteria(Regija.class).list();
		return new ArrayList<Regija>(r);
	}
	
}
