package ba.unsa.etf.si.tim8.mlmarketing.services;

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
}
