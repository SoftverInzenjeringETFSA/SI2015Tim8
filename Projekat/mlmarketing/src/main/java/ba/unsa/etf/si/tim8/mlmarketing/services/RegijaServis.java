package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;

public class RegijaServis {
	Session s;
	
	public RegijaServis(Session s){
		this.s=s;
	}
	
	public int dodajRegiju(Regija nova){
		Transaction t = s.beginTransaction();
		Regija r = new Regija();
		r.setIme(nova.getIme());
		r.setDrzava(nova.getDrzava());
		int id = (Integer) s.save(r);
		t.commit();
		return id;
	}
	
	public boolean obrisi(int id){
		Regija zaBrisanje = s.get(Regija.class,id);
		
		Criteria c = s.createCriteria(Faktura.class);
		c.add(Restrictions.eq("regija", zaBrisanje));
		List<Faktura> listafakture = c.list();
		if(!listafakture.isEmpty()){
			ArrayList<Faktura> ilistafakture = new ArrayList<Faktura>(listafakture);
			for(int i=0; i<ilistafakture.size();i++){
				ilistafakture.get(i).setRegija(null);
				s.update(ilistafakture.get(i));
			}
		}
		
		Transaction t = s.beginTransaction();		
		
		
		if(zaBrisanje!=null)s.delete(zaBrisanje);
		
		t.commit();
		
		return true;
	}
	
	public ArrayList<Regija> dajRegije(){
		List<Regija> r = s.createCriteria(Regija.class).list();
		return new ArrayList<Regija>(r);
	}
	
	public int dajBrojRegija(){
		return s.createCriteria(Regija.class).list().size();
	}
	
	public int dajBrojAkteraZaRegiju(int id){
		Regija r =(Regija) s.get(Regija.class, id);
		return r.getAkterprodajes().size();
	}
	
	public boolean daLiPostojiRegija(String regija)
	{
		Criteria c = s.createCriteria(Regija.class);
		if(c.add(Restrictions.eq("ime", regija)).uniqueResult() == null)
			return false;
		return true;
	}
}
