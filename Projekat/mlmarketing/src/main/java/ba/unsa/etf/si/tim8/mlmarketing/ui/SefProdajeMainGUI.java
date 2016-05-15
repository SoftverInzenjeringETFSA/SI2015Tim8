package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NaloziServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;

public class SefProdajeMainGUI {
	
	final static Logger logger = Logger.getLogger(SefProdajeMainGUI.class);
	private Session s;
	private RegijaServis rs;
	private AkterServis aks;
	private NaloziServis ns;
	private ProizvodServis ps;
	private NarudzbaServis nfs;
	private LoginGUI roditelj;
	
	private JFrame frmSefProdaje;
	private JTable tableKorisnici;
	private JTable tableMenadzeri;
	private JTable tableProdavaci;
	private JTable tableProizvodi;
	private JTable tableRegije;
	private JTable tableNarudzbe;
	private JTable tableFaktura;

	/**
	 * Launch the application.
	 */
	public void startSefProdajeMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SefProdajeMainGUI window = new SefProdajeMainGUI(s,roditelj);
					window.frmSefProdaje.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SefProdajeMainGUI(Session s,LoginGUI roditelj) {
		this.s=s;
		this.rs = new RegijaServis(s);
		this.aks = new AkterServis(s);
		this.ns = new NaloziServis(s);
		this.ps = new ProizvodServis(s);
		this.nfs = new NarudzbaServis(s);
		this.roditelj=roditelj;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		final SefProdajeMainGUI ref = this;
		frmSefProdaje = new JFrame();
		frmSefProdaje.setTitle("\u0160ef prodaje ");
		frmSefProdaje.setBounds(100, 100, 652, 393);
		frmSefProdaje.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSefProdaje.getContentPane().setLayout(null);
		
		JButton btnOdjava = new JButton("Odjava sa sistema");
		btnOdjava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(SesijaServis.odjava()){
					roditelj.otkrij();
					frmSefProdaje.dispose();
				}
			}
		});
		btnOdjava.setBounds(460, 11, 166, 23);
		frmSefProdaje.getContentPane().add(btnOdjava);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 41, 616, 302);
		frmSefProdaje.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Korisnici", null, panel, null);
		panel.setLayout(null);
		
		JButton btnKreirajRacun = new JButton("Kreiraj korisni\u010Dki ra\u010Dun");
		btnKreirajRacun.setBounds(244, 146, 170, 23);
		panel.add(btnKreirajRacun);
		btnKreirajRacun.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				KreiranjeKorisnickogRacunaGUI kkr = new KreiranjeKorisnickogRacunaGUI(s,ref);
				kkr.startKreiranjeRacuna();
			}
		});
		
		JButton btnObrisiRacun = new JButton("Obri\u0161i korisni\u010Dki ra\u010Dun");
		btnObrisiRacun.setBounds(426, 146, 175, 23);
		panel.add(btnObrisiRacun);
		btnObrisiRacun.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableKorisnici.getSelectedRow()!=-1){
					int id = odaberiIdKolonu(tableKorisnici, 5);
					Korisnik k = s.get(Korisnik.class, id);
					if(k!=null){
						if(k.getUsername().equals("master")){
							JOptionPane.showMessageDialog(null, "Nije moguce obrisati master nalog!");
						}
						else if(k.getId()==SesijaServis.dajIdKorisnika()){
							JOptionPane.showMessageDialog(null, "Ne mozete obrisati vlastiti nalog!");
						}
						else{
							int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
							if(rez == JOptionPane.YES_OPTION){
								
								ns.obrisiNalog(id);
								refreshajTabeluKorisnici();
							}						}
						
					}
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nalog");
				
			}
		});
			
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 591, 118);
		panel.add(scrollPane_2);
			
			
			
		tableKorisnici = new JTable();
		scrollPane_2.setViewportView(tableKorisnici);
		tableKorisnici.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Tip ra\u010Duna", "Korisni\u010Dko ime", "Ime", "Prezime", "Adresa"
			}
		));
			

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Regionalni menadžeri", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnDodajMenadzera = new JButton("Dodaj");
		btnDodajMenadzera.setBounds(299, 146, 94, 23);
		panel_1.add(btnDodajMenadzera);
		btnDodajMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(rs.dajBrojRegija()==0){
					JOptionPane.showMessageDialog(null, "Potrebno je da postoji barem jedna regija, kako bi bilo moguce dodati menadzera");
				}
				else
				{
					RegMenadzerDodajIzmjeniGUI rmdodaj = new RegMenadzerDodajIzmjeniGUI("dodaj",s,ref);
					rmdodaj.pokreniRegMenDodajIzmjeni("dodaj");
				}
			}
		});
		
		JButton btnObrisiMenadzera = new JButton("Obri\u0161i ");
		btnObrisiMenadzera.setBounds(403, 146, 94, 23);
		btnObrisiMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(tableMenadzeri.getSelectedRow() != -1)
				{
					int id = odaberiIdKolonu(tableMenadzeri, 5);
					Akterprodaje a = aks.dajAktera(id);
					if(aks.moguceBrisanje(a)){
						if(a.getAkterprodajes().size()==0){
							int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
							if(rez == JOptionPane.YES_OPTION){
								aks.izbrisiAktera(id);
								refreshajTabeluMenadzeri();
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Nije moguce obrisati regionalnog menadžera"
									+ " dok je isti nadležan za neke prodavače.");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nije moguce brisanje aktera dok se ne fakturisu sve njegove "
								+ "narudzbe koje su potvrdene!");
					}
					
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog menadžera iz tabele.");
				
				
			}
		});
		panel_1.add(btnObrisiMenadzera);
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.setBounds(507, 146, 94, 23);
		panel_1.add(btnIzmijeni);
		btnIzmijeni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableMenadzeri.getSelectedRow() != -1){
					
						int id= odaberiIdKolonu(tableMenadzeri, 5);
						RegMenadzerDodajIzmjeniGUI rmdodaj = new RegMenadzerDodajIzmjeniGUI("izmjeni",s,ref,id);
						rmdodaj.pokreniRegMenDodajIzmjeni("izmjeni");
					
					
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog menadžera iz tabele.");
				
				
			}
		});
		
		JButton btnUnazadi = new JButton("Unazadi");
		btnUnazadi.setBounds(404, 180, 197, 23);
		btnUnazadi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableMenadzeri.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableMenadzeri, 5);
					Akterprodaje a = aks.dajAktera(id);
					if(a.getAkterprodajes().size()==0){
						int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
						if(rez == JOptionPane.YES_OPTION){
							
								a.setTip("prodavac");
								aks.izmjeniAktera(a);
								DodjelaMenadzeraGUI dodjelamenadzera= new DodjelaMenadzeraGUI(s,ref,a.getId());
								dodjelamenadzera.startDodjelaMenadzera();
						}
					}
					else{
							JOptionPane.showMessageDialog(null, "Nije moguce unazaditi regionalnog menadžera"
									+ " dok je isti nadležan za neke prodavače.");
					}
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog menadžera iz tabele.");
			}
		});
		panel_1.add(btnUnazadi);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 591, 124);
		panel_1.add(scrollPane);
		

		
		tableMenadzeri = new JTable();
		tableMenadzeri.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime", 
				"Broj telefona",
				"Adresa",
				"Email",
				"Regija"
				}
		));
		scrollPane.setViewportView(tableMenadzeri);
		
		JButton btnPregledMenadzera = new JButton("Prika\u017Ei aktera");
		btnPregledMenadzera.setBounds(10, 146, 113, 23);
		panel_1.add(btnPregledMenadzera);
		btnPregledMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableMenadzeri.getSelectedRow() != -1)
				{
					int id = (Integer)tableMenadzeri.getModel().getValueAt(tableMenadzeri.getSelectedRow(), 5);
					PrikazRegionalniMenadzerGUI rmprikaz = new PrikazRegionalniMenadzerGUI(s,id);
					rmprikaz.startPrikazMenadzer();
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste odabrali nijednog menadžera iz tabele");
				}
								
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Prodava\u010Di", null, panel_2, null);
		panel_2.setLayout(null);
		
		JButton btnDodajProdavaca = new JButton("Dodaj ");
		btnDodajProdavaca.setBounds(299, 146, 94, 23);
		panel_2.add(btnDodajProdavaca);
		btnDodajProdavaca.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(rs.dajBrojRegija()!=0){
					if(aks.dajSveAkterePoTipu("regmen")!=null){
						ProdavacDodajIzmjeni prodavacdodaj= new ProdavacDodajIzmjeni("dodaj", s, ref);
						prodavacdodaj.startProdavacDodajIzmjeni("dodaj");
					}
					else{
						JOptionPane.showMessageDialog(null, "Potrebno je da postoji barem jedan regionalni menadzer \n kako bi mogli dodavati prodavace");
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Potrebno je da postoji barem jedna regija i jedan regionalni menadzer, kako bi bilo moguce dodati prodavaca");
				}
				
			}
		});
		
		JButton btnObrisiProdavaca = new JButton("Obri\u0161i");
	
		btnObrisiProdavaca.setBounds(403, 146, 94, 23);
		panel_2.add(btnObrisiProdavaca);
		btnObrisiProdavaca.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(tableProdavaci.getSelectedRow() != -1){
					int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
					if(rez == JOptionPane.YES_OPTION){
						int id = odaberiIdKolonu(tableProdavaci, 6);
						aks.izbrisiAktera(id);
						refreshajTabeluProdavaci();
					}

				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog prodavača iz tabele.");
					
			}
		});
		
		JButton btnProdavacIzmjeni = new JButton("Izmijeni");
		btnProdavacIzmjeni.setBounds(507, 146, 94, 23);
		panel_2.add(btnProdavacIzmjeni);
		btnProdavacIzmjeni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableProdavaci.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableProdavaci, 6);
					ProdavacDodajIzmjeni prodavacdodaj= new ProdavacDodajIzmjeni("", s, ref, id);
					prodavacdodaj.startProdavacDodajIzmjeni("");
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog prodavača iz tabele.");				
				
			}
		});
		
		JButton btnPromoviiUMenadera = new JButton("Unaprijedi");
		btnPromoviiUMenadera.setBounds(403, 180, 198, 23);
		btnPromoviiUMenadera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableProdavaci.getSelectedRow() != -1){
					int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
					if(rez == JOptionPane.YES_OPTION){
						int id = odaberiIdKolonu(tableProdavaci, 6);
						Akterprodaje a = aks.dajAktera(id);
						a.setAkterprodaje(null);
						a.setTip("regmen");
						aks.izmjeniAktera(a);
						refreshajTabeluProdavaci();
						refreshajTabeluMenadzeri();
					}
					
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog prodavača iz tabele.");
				
				
			}
		});
		panel_2.add(btnPromoviiUMenadera);
		
		JButton btnDodjeliMenadzera = new JButton("Dodijeli nadle\u017Enog menad\u017Eera");
		btnDodjeliMenadzera.setBounds(403, 214, 198, 23);
		panel_2.add(btnDodjeliMenadzera);
		btnDodjeliMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableProdavaci.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableProdavaci, 6);
					DodjelaMenadzeraGUI dodjelamenadzera= new DodjelaMenadzeraGUI(s,ref,id);
					dodjelamenadzera.startDodjelaMenadzera();
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog prodavača iz tabele.");
				
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 591, 124);
		panel_2.add(scrollPane_1);
		
		tableProdavaci = new JTable();
		tableProdavaci.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime",
				"Broj telefona",
				"Adresa",
				"Email",
				"Regija",
				"Nadležni manager"
			}
		));
		scrollPane_1.setViewportView(tableProdavaci);
		
		JButton btnPregledAktera = new JButton("Prika\u017Ei aktera");
		btnPregledAktera.setBounds(10, 146, 127, 23);
		panel_2.add(btnPregledAktera);
		btnPregledAktera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableProdavaci.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableProdavaci, 6);
					PrikazProdavacGUI prodavacprikaz= new PrikazProdavacGUI(s, id);
					prodavacprikaz.startPrikazProdavac();
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednog prodavača iz tabele.");
				
				
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Proizvodi", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 591, 124);
		panel_3.add(scrollPane_3);
		
		tableProizvodi = new JTable();
		tableProizvodi.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Naziv proizvoda",
				"Nabavna cijena",
				"Prodajna cijena",
				"Stanje na skladištu"
			}
		));
		scrollPane_3.setViewportView(tableProizvodi);
		
	
		
		JButton btnIzmijeniProizvod = new JButton("Izmijeni");
		
		btnIzmijeniProizvod.setBounds(347, 142, 121, 23);
		panel_3.add(btnIzmijeniProizvod);
		btnIzmijeniProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(tableProizvodi.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableProizvodi, 4);
					IzmjenaProizvodaGUI izmjenaproizvoda = new IzmjenaProizvodaGUI(s, ref, id);
					izmjenaproizvoda.startIzmjenaProizvoda();
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijedan proizvod iz tabele.");
				
				
			}
		});
		
		JButton btnObrisiProizvod = new JButton("Obri\u0161i");
		btnObrisiProizvod.setBounds(478, 142, 123, 23);
		btnObrisiProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableProizvodi.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableProizvodi, 4);
					Proizvod p = ps.dajProizvod(id);
					if(ps.moguceObrisati(p)){
						int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
						if(rez == JOptionPane.YES_OPTION){
							ps.obrisiProizvod(p);
							refreshajTabeluProizvodi();
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Nije moguce obrisati proizvod dok se ne fakturisu narudzbe kojim pripada, a koje su potvrđene");
					}
					
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijedan proizvod iz tabele.");
				
			}
		});
		panel_3.add(btnObrisiProizvod);
		
		JButton btnDodajProizvod = new JButton("Dodaj");
		btnDodajProizvod.setBounds(225, 142, 115, 23);
		panel_3.add(btnDodajProizvod);
		btnDodajProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodavanjeProizvodaGUI dodavanjeProizvoda = new DodavanjeProizvodaGUI(s,ref);
				dodavanjeProizvoda.startDodavanjeProizvoda();
				
			}
		});
		
		JButton btnProizvodPrikaz = new JButton("Prika\u017Ei proizvod");
		btnProizvodPrikaz.setBounds(12, 141, 139, 25);
		panel_3.add(btnProizvodPrikaz);
		btnProizvodPrikaz.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(tableProizvodi.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableProizvodi, 4);
					PrikazProizvodGUI pprikaz = new PrikazProizvodGUI(s, ref,id);
					pprikaz.startPrikazProizvod();
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijedan proizvod iz tabele.");
				
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Regije", null, panel_4, null);
		panel_4.setLayout(null);
		
		
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 13, 587, 190);
		panel_4.add(scrollPane_4);
		
		tableRegije = new JTable();
		tableRegije.setModel(new MyTableModel(
			new Object[][]{},
			new String[] {
				"Regija", "Dr\u017Eava",""
			}
		));
		tableRegije.getColumnModel().getColumn(0).setPreferredWidth(117);
		tableRegije.getColumnModel().getColumn(1).setPreferredWidth(108);
		
		scrollPane_4.setViewportView(tableRegije);
		
		JButton btnDodajRegiju = new JButton("Dodaj");
		btnDodajRegiju.setBounds(393, 216, 97, 25);
		panel_4.add(btnDodajRegiju);
		btnDodajRegiju.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodavanjeRegijeGUI dodavanjeRegije = new DodavanjeRegijeGUI(s,ref);
				dodavanjeRegije.startDodavanjeRegije();
				
			}
		});
		
		JButton btnObrisiRegiju = new JButton("Obri\u0161i");
		btnObrisiRegiju.setBounds(502, 216, 97, 25);
		panel_4.add(btnObrisiRegiju);
		btnObrisiRegiju.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(tableRegije.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableRegije, 2);
					if(rs.dajBrojAkteraZaRegiju(id) > 0)
						JOptionPane.showMessageDialog(null, "Ne možete obrisati regiju dok postoje akteri u istoj.");
					else
					{
						int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
						if(rez == JOptionPane.YES_OPTION){
							rs.obrisi(id);
							refreshajTabeluRegije();
						}
					}
					
					
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednu regiju iz tabele.");
				
			}
		});
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Narud\u017Ebe", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(12, 13, 587, 186);
		panel_5.add(scrollPane_5);
		
		tableNarudzbe = new JTable();
		tableNarudzbe.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Status"
			}
		));
		scrollPane_5.setViewportView(tableNarudzbe);
		
		JButton btnPrikaziNarudzbu = new JButton("Prika\u017Ei narud\u017Ebu");
		btnPrikaziNarudzbu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableNarudzbe.getSelectedRow() != -1)
				{
					int id = odaberiIdKolonu(tableNarudzbe, 0);
					PrikazNarudzbaKomGUI pnk = new PrikazNarudzbaKomGUI(s, id);
					pnk.startPrikazNarudzbaKom();
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednu narudžbu iz tabele.");
			}
		});
		btnPrikaziNarudzbu.setBounds(441, 212, 158, 25);
		panel_5.add(btnPrikaziNarudzbu);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		tabbedPane.addTab("Fakture", null, panel_6, null);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(12, 13, 587, 193);
		panel_6.add(scrollPane_6);
		
		tableFaktura = new JTable();
		tableFaktura.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Ukupna cijena"
			}
		));
		tableFaktura.getColumnModel().getColumn(0).setPreferredWidth(96);
		tableFaktura.getColumnModel().getColumn(1).setPreferredWidth(96);
		tableFaktura.getColumnModel().getColumn(2).setPreferredWidth(102);
		tableFaktura.getColumnModel().getColumn(3).setPreferredWidth(113);
		scrollPane_6.setViewportView(tableFaktura);
		
		JButton btnPrikaziFakturu = new JButton("Prika\u017Ei fakturu");
		btnPrikaziFakturu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tableFaktura.getSelectedRow() != -1){
					int id = odaberiIdKolonu(tableFaktura, 0);
					PrikazFakturaGUI frmFak = new PrikazFakturaGUI(s, id);
					frmFak.startPrikazFakture();
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali nijednu fakturu iz tabele.");
			}
		});
		btnPrikaziFakturu.setBounds(454, 219, 145, 25);
		panel_6.add(btnPrikaziFakturu);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		tabbedPane.addTab("Izvje\u0161taji", null, panel_7, null);
		panel_7.setLayout(null);
		
		refreshajTabeluRegije();
		refreshajTabeluMenadzeri();
		refreshajTabeluKorisnici();
		refreshajTabeluProdavaci();
		refreshajTabeluProizvodi();
		refreshajTabeluNarudzba();
		refreshajTabeluFakture();
	}
	
	public void refreshajTabeluRegije(){
		ArrayList<Regija> regije = rs.dajRegije();
		Object[][] data= new Object[regije.size()][];
		for(int i = 0; i<regije.size();i++) data[i]= new Object[]{regije.get(i).getIme(),regije.get(i).getDrzava(),regije.get(i).getId()};
		
		tableRegije.setModel(new MyTableModel(
				data,
				new String[] {
					"Regija", "Dr\u017Eava",""
				}
			));
		tableRegije.getColumnModel().removeColumn(tableRegije.getColumnModel().getColumn(2));
	}
	
	public void refreshajTabeluMenadzeri(){
		ArrayList<Akterprodaje> akteri = aks.dajSveAkterePoTipu("regmen");
		Object[][] data = new Object[akteri.size()][];
		for(int i = 0; i<akteri.size();i++) data[i]= new Object[]{akteri.get(i).getIme()+" "+akteri.get(i).getPrezime(),
				akteri.get(i).getBrojtelefona(), akteri.get(i).getAdresa(), akteri.get(i).getEmail(),
				akteri.get(i).getRegija().getIme(), akteri.get(i).getId()
				};
		
		tableMenadzeri.setModel(new MyTableModel(
				data ,
				new String[] {
					"Ime i prezime", 
					"Broj telefona",
					"Adresa",
					"Email",
					"Regija",
					"ID"
					}
			));
		
		tableMenadzeri.getColumnModel().removeColumn(tableMenadzeri.getColumnModel().getColumn(5));
	}
	
	public void refreshajTabeluProdavaci()
	{
		ArrayList<Akterprodaje> akteri = aks.dajSveAkterePoTipu("prodavac");
		Object[][] data = new Object[akteri.size()][];
		for(int i = 0; i < akteri.size(); i++)
		{
			data[i] = new Object[]{ 
				akteri.get(i).getIme() + " " + akteri.get(i).getPrezime(),
				akteri.get(i).getBrojtelefona(),
				akteri.get(i).getAdresa(),
				akteri.get(i).getEmail(),
				akteri.get(i).getRegija().getIme(),
				akteri.get(i).getAkterprodaje().getIme()+" "+ akteri.get(i).getAkterprodaje().getPrezime(),
				akteri.get(i).getId()
			};
		}
		tableProdavaci.setModel(new MyTableModel(
				data,					
				new String[] {
					"Ime i prezime",
					"Broj telefona",
					"Adresa",
					"Email",
					"Regija",
					"Nadležni manager",
					"ID"
				}
			));
		tableProdavaci.getColumnModel().removeColumn(tableProdavaci.getColumnModel().getColumn(6));
	}
	
	public void refreshajTabeluKorisnici(){
		ArrayList<Korisnik> korisnici = ns.dajSveNaloge();
		Object[][] data = new Object[korisnici.size()][];
		for(int i = 0; i<korisnici.size();i++) data[i]= new Object[]{korisnici.get(i).getTip(),korisnici.get(i).getUsername(),
				korisnici.get(i).getIme(),korisnici.get(i).getPrezime(),korisnici.get(i).getAdresa(),korisnici.get(i).getId()
				};
		
		tableKorisnici.setModel(new MyTableModel(
				data,
				new String[] {
					"Tip ra\u010Duna", "Korisni\u010Dko ime", "Ime", "Prezime", "Adresa","ID"
				}
			));
		
		tableKorisnici.getColumnModel().removeColumn(tableKorisnici.getColumnModel().getColumn(5));
	}
	
	public void refreshajTabeluProizvodi(){
		ArrayList<Proizvod> proizvodi = ps.dajSveProizvode();
		Object[][] data = new Object[proizvodi.size()][];
		for(int i = 0; i<proizvodi.size();i++){
			data[i]= new Object[]{proizvodi.get(i).getNaziv(),proizvodi.get(i).getNabavnacijena(),
					proizvodi.get(i).getProdajnacijena(),proizvodi.get(i).getKolicina(),proizvodi.get(i).getId()
			};
		}
		tableProizvodi.setModel(new MyTableModel(
				data,
				new String[] {
					"Naziv proizvoda",
					"Nabavna cijena",
					"Prodajna cijena",
					"Stanje na skladištu",
					"ID"
				}));
		tableProizvodi.getColumnModel().removeColumn(tableProizvodi.getColumnModel().getColumn(4));
	}
	public void refreshajTabeluNarudzba(){
		ArrayList<Narudzba> narudzbe = nfs.dajNarudzbe();
		Object[][] data = new Object[narudzbe.size()][];
		for (int i = 0; i < narudzbe.size(); i++) {
			data[i] = new Object[] {
					narudzbe.get(i).getId(), 
					narudzbe.get(i).getAkterprodaje().toString(),
					narudzbe.get(i).getDatum().toString(),
					narudzbe.get(i).getStatus()
			};
		}
		
		tableNarudzbe.setModel(new MyTableModel(data, new String[]{
				"ID", "Naru\u010Dilac", "Datum", "Status"
		}));
	}

	public void refreshajTabeluFakture(){
		ArrayList<Faktura> fakture = nfs.dajFakture();
		Object[][] data = new Object[fakture.size()][];
		for (int i = 0; i < fakture.size(); i++){
			data[i] = new Object[] {
					fakture.get(i).getId(), 
					fakture.get(i).getImeaktera(),
					fakture.get(i).getDatum(),
					fakture.get(i).getUkupnacijena()
			};
		}
		
		tableFaktura.setModel(new MyTableModel(
				data,
				new String[] {
						"ID",
						"Naručilac",
						"Datum",
						"Ukupna cijena"					
						
				}));
	}
	
	public int odaberiIdKolonu(JTable tabela,int brojKolone){
		int id = (Integer)tabela.getModel().getValueAt(tabela.getSelectedRow(),brojKolone);
		return id;
	}

}
