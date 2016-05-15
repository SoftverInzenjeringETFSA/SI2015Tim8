package ba.unsa.etf.si.tim8.mlmarketing.servisnitestovi;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.ui.HibernateUtil;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;

public class AkterServisTest {
 Session session;
 
 RegijaServis RegijaS;
 Regija regija;
 //Korisnik k;
 Akterprodaje akterP;
 ArrayList<Regija> listaRegija;
 AkterServis as;
 public static int _id;  // id cemo koristiti u svim metodama! kao i u metodi @After za brisanje aktera!



 @Before
 public void setup(){
 try {
   session=HibernateUtil.getSessionFactory().openSession();
   as =new AkterServis(session);

   // importujemo regiju iz tabele regija, jer direktno kreiranje regije na licu mjesta nije radilo, tacnije bacalo je izuzetak!
   //NAPOMENA TABELA SE REGIJAMA NE SMIJE BITI PRAZNA!
  
   RegijaS=new RegijaServis(session);
   
   regija = new Regija("Kantnon1 Sarajevo", "Bosna i Hercegovina");
   regija.setId(RegijaS.dodajRegiju(regija));
   /* konstruktor : public Akterprodaje(Regija regija, String tip, String ime, String prezime, String adresa, String email,
   String brojtelefona)*/
   akterP=new Akterprodaje(regija,"sef","testIme","testPrezime","testAdresa","test@email.com","05233344221");
   akterP.setId(as.kreirajAktera(akterP));
  
   }catch (Exception e){
     fail ("greska u setupu "+ e.getMessage());
   }
  
 }
  
 @After
   public void tearDown() throws Exception {
    try {
      as.izbrisiAktera(akterP.getId());
      RegijaS.obrisi(regija.getId());
    } catch (Exception e) {
     // throw e;
    }
   }

 @Test
 public void testKreirajAktera() {
   /*testira kreiranje aktera, i provjerava ga , da li se nalazi u bazi*/
   try{
    
     // ovo je super, kreiramo aktera prodaje i vraca nam id, sad imamo prakticno pristup
     // svim podacimo o korisniku , u 
     Akterprodaje testniAkter= new Akterprodaje(regija, "sef", "test", "test", "test", "test@test.test", "213/213-213");
     testniAkter.setId(as.kreirajAktera(testniAkter));

     assertEquals("test",testniAkter.getIme());
     as.izbrisiAktera(testniAkter.getId());
   }catch(Exception e){
     fail("greska u testKreirajAktera()"+e.getMessage());
   }
  
 }

 @Test
 public void testDajAktera()
 {
   // imamo vec postojeci id; a ovo smo testirali u ranijem testu, ali mozemo ponoviti :D
   try{
   Akterprodaje akterIzBaze=new Akterprodaje();
   
   akterIzBaze=as.dajAktera(akterP.getId());
   assertEquals(akterP.toString(),akterIzBaze.toString());
   }catch(Exception e){
     fail("testDajAktera()");
   }
  
 }

 @Test
 public void testDajBrojAktera(){
   int brojAktera=as.dajBrojAktera();
   try{
   //ArrayList<Akterprodaje> aplista=new ArrayList<Akterprodaje>();
   //int brojAkteraListe=
   Akterprodaje testni= new Akterprodaje(regija, "sef", "test", "test", "test", "test@test.test", "213/213-213");
   testni.setId(as.kreirajAktera(testni));
   int brojAkteraUp1=as.dajBrojAktera();
   as.izbrisiAktera(testni.getId());
   assertEquals(brojAktera+1,brojAkteraUp1);
   }catch(Exception e){
     fail("dajBojAktera(): "+e.getMessage());
   }
 }

 @Test
 public void testIzmjeniAktera(){
   //int _idAktera; // bitno da znamo da li smo izmijenili podatke o akteru!, a da je pri tome Id_ ostao isti
  
   try{
     // kreiramo aktera, dodajemo ga u bazu i dobijamo njegov ID
       int _idAktera=akterP.getId();
    
       // akterIzmjeni je zapravo kreirani akter, koji je povucen nazad iz baze!
     Akterprodaje akterIzmjeni=as.dajAktera(_idAktera);
    
     // Vrsimo promjene nad akterom iz baze!, mijenjamo ime i prezime
     akterIzmjeni.setIme("ImePromijeno");
     akterIzmjeni.setPrezime("PrezimePromijenjeno");
    
     // ako izmjena podataka bude uspjesna vraca nam se true
     boolean izmjenjenTrue= as.izmjeniAktera(akterIzmjeni);
     assertEquals(true,izmjenjenTrue);
    
     // povlacimo opet aktera iz Baze, sada sa promijenjenim podacima, kako bismo uporedili da li su promjene
     //zaista izvrsene
     Akterprodaje akterIzBaze=as.dajAktera(_idAktera);
  
     assertEquals("ImePromijeno",(String)akterIzBaze.getIme());
     assertEquals("PrezimePromijenjeno",(String)akterIzBaze.getPrezime());
    
     //obrisimo na kraju aktera! izvrsit ce se u @After samo dodijelimo _id-u vrijednosta _idAktera
     

   }catch(Exception e){
     fail("testIzmjeniAktera() "+e.getMessage());
   }
 }

 @Test
 public void testIzbrisiAktera(){
   boolean izbrisanAkter;
   try{
     Akterprodaje testni= new Akterprodaje(regija, "sef", "test", "test", "test", "test@test.test", "213/213-213");
     testni.setId(as.kreirajAktera(testni));
     izbrisanAkter=as.izbrisiAktera(testni.getId());
     assertEquals(true,izbrisanAkter);
   }catch(Exception e){
     fail("testIzbrisiAktera(): "+e.getMessage());
   }
 }


}