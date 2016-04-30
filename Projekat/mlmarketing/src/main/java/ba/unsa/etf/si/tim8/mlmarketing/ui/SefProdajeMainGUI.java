package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class SefProdajeMainGUI {

	private JFrame frmSefProdaje;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;

	/**
	 * Launch the application.
	 */
	public static void startSefProdajeMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SefProdajeMainGUI window = new SefProdajeMainGUI();
					window.frmSefProdaje.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SefProdajeMainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSefProdaje = new JFrame();
		frmSefProdaje.setTitle("\u0160ef prodaje ");
		frmSefProdaje.setBounds(100, 100, 652, 393);
		frmSefProdaje.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSefProdaje.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Odjava sa sistema");
		btnNewButton.setBounds(460, 11, 166, 23);
		frmSefProdaje.getContentPane().add(btnNewButton);
		
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
				KreiranjeKorisnickogRacunaGUI kkr = new KreiranjeKorisnickogRacunaGUI();
				kkr.startKreiranjeRacuna();
				
			}
		});
		
		JButton btnNewButton_2 = new JButton("Obri\u0161i korisni\u010Dki ra\u010Dun");
		btnNewButton_2.setBounds(426, 146, 175, 23);
		panel.add(btnNewButton_2);
			
			JScrollPane scrollPane_2 = new JScrollPane();
			scrollPane_2.setBounds(10, 11, 591, 118);
			panel.add(scrollPane_2);
			
			
			
			
			table = new JTable();
			scrollPane_2.setViewportView(table);
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Tip ra\u010Duna", "Korisni\u010Dko ime", "Ime", "Prezime", "Adresa"
				}
			));
			
				
				
				table.setBackground(Color.LIGHT_GRAY);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Regionalni menadžeri", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnDodajMenadzera = new JButton("Dodaj");
		btnDodajMenadzera.setBounds(299, 146, 94, 23);
		panel_1.add(btnDodajMenadzera);
		btnDodajMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RegMenadzerDodajIzmjeniGUI rmdodaj = new RegMenadzerDodajIzmjeniGUI("dodaj");
				rmdodaj.main("dodaj");
				
			}
		});
		
		JButton btnNewButton_4 = new JButton("Obri\u0161i ");
		btnNewButton_4.setBounds(403, 146, 94, 23);
		panel_1.add(btnNewButton_4);
		
		JButton btnIzmijeni = new JButton("Izmijeni");
		btnIzmijeni.setBounds(507, 146, 94, 23);
		panel_1.add(btnIzmijeni);
		btnIzmijeni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				RegMenadzerDodajIzmjeniGUI rmdodaj = new RegMenadzerDodajIzmjeniGUI("izmjeni");
				rmdodaj.main("izmjeni");
				
			}
		});
		
		JButton btnNewButton_7 = new JButton("Unazadi");
		btnNewButton_7.setBounds(404, 180, 197, 23);
		panel_1.add(btnNewButton_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 591, 124);
		panel_1.add(scrollPane);
		

		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(table_1);
		table_1.setBackground(Color.LIGHT_GRAY);
		
		JButton btnPregledMenadzera = new JButton("Prika\u017Ei aktera");
		btnPregledMenadzera.setBounds(10, 146, 113, 23);
		panel_1.add(btnPregledMenadzera);
		btnPregledMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PrikazRegionalniMenadzerGUI rmprikaz = new PrikazRegionalniMenadzerGUI();
				rmprikaz.startPrikazMenadzer();				
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
				ProdavacDodajIzmjeni prodavacdodaj= new ProdavacDodajIzmjeni("dodaj");
				prodavacdodaj.startProdavacDodajIzmjeni("dodaj");
				
			}
		});
		
		JButton btnNewButton_5 = new JButton("Obri\u0161i");
	
		btnNewButton_5.setBounds(403, 146, 94, 23);
		panel_2.add(btnNewButton_5);
		
		JButton btnProdavacIzmjeni = new JButton("Izmijeni");
		btnProdavacIzmjeni.setBounds(507, 146, 94, 23);
		panel_2.add(btnProdavacIzmjeni);
		btnProdavacIzmjeni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ProdavacDodajIzmjeni prodavacdodaj= new ProdavacDodajIzmjeni("");
				prodavacdodaj.startProdavacDodajIzmjeni("");
				
			}
		});
		
		JButton btnPromoviiUMenadera = new JButton("Unaprijedi");
		btnPromoviiUMenadera.setBounds(403, 180, 198, 23);
		panel_2.add(btnPromoviiUMenadera);
		
		JButton btnDodjeliMenadzera = new JButton("Dodijeli nadle\u017Enog menad\u017Eera");
		btnDodjeliMenadzera.setBounds(403, 214, 198, 23);
		panel_2.add(btnDodjeliMenadzera);
		btnDodjeliMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodjelaMenadzeraGUI dodjelamenadzera= new DodjelaMenadzeraGUI();
				dodjelamenadzera.startDodjelaMenadzera();
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 591, 124);
		panel_2.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
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
		scrollPane_1.setViewportView(table_2);
		table_2.setBackground(Color.LIGHT_GRAY);
		
		JButton btnPregledAktera = new JButton("Prika\u017Ei aktera");
		btnPregledAktera.setBounds(10, 146, 127, 23);
		panel_2.add(btnPregledAktera);
		btnPregledAktera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PrikazProdavacGUI prodavacprikaz= new PrikazProdavacGUI();
				prodavacprikaz.startPrikazProdavac();
				
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Proizvodi", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 591, 124);
		panel_3.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Naziv proizvoda",
				"Nabavna cijena",
				"Prodajna cijena",
				"Stanje na skladištu"
			}
		));
		scrollPane_3.setViewportView(table_3);
		
	
		
		JButton btnIzmijeniProizvod = new JButton("Izmijeni");
		
		btnIzmijeniProizvod.setBounds(347, 142, 121, 23);
		panel_3.add(btnIzmijeniProizvod);
		btnIzmijeniProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				IzmjenaProizvodaGUI izmjenaproizvoda = new IzmjenaProizvodaGUI();
				izmjenaproizvoda.startIzmjenaProizvoda();
				
			}
		});
		
		JButton btnNewButton_9 = new JButton("Obri\u0161i");
		btnNewButton_9.setBounds(478, 142, 123, 23);
		panel_3.add(btnNewButton_9);
		
		JButton btnDodajProizvod = new JButton("Dodaj");
		btnDodajProizvod.setBounds(225, 142, 115, 23);
		panel_3.add(btnDodajProizvod);
		btnDodajProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodavanjeProizvodaGUI dodavanjeProizvoda = new DodavanjeProizvodaGUI();
				dodavanjeProizvoda.startDodavanjeProizvoda();
				
			}
		});
		
		JButton btnProizvodPrikaz = new JButton("Prika\u017Ei proizvod");
		btnProizvodPrikaz.setBounds(12, 141, 139, 25);
		panel_3.add(btnProizvodPrikaz);
		btnProizvodPrikaz.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PrikazProizvodGUI pprikaz = new PrikazProizvodGUI();
				pprikaz.startPrikazProizvod();
				
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Regije", null, panel_4, null);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 13, 587, 190);
		panel_4.add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Regija", "Dr\u017Eava"
			}
		));
		table_4.getColumnModel().getColumn(0).setPreferredWidth(117);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(108);
		scrollPane_4.setViewportView(table_4);
		
		JButton btnDodajRegiju = new JButton("Dodaj");
		btnDodajRegiju.setBounds(393, 216, 97, 25);
		panel_4.add(btnDodajRegiju);
		btnDodajRegiju.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodavanjeRegijeGUI dodavanjeRegije = new DodavanjeRegijeGUI();
				dodavanjeRegije.startDodavanjeRegije();
				
			}
		});
		
		JButton button_1 = new JButton("Obri\u0161i");
		button_1.setBounds(502, 216, 97, 25);
		panel_4.add(button_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		tabbedPane.addTab("Narud\u017Ebe", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(12, 13, 587, 186);
		panel_5.add(scrollPane_5);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Status"
			}
		));
		scrollPane_5.setViewportView(table_5);
		
		JButton button_2 = new JButton("Prika\u017Ei narud\u017Ebu");
		button_2.setBounds(441, 212, 158, 25);
		panel_5.add(button_2);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		tabbedPane.addTab("Fakture", null, panel_6, null);
		panel_6.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(12, 13, 587, 193);
		panel_6.add(scrollPane_6);
		
		table_6 = new JTable();
		table_6.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Ukupna cijena"
			}
		));
		table_6.getColumnModel().getColumn(0).setPreferredWidth(96);
		table_6.getColumnModel().getColumn(1).setPreferredWidth(96);
		table_6.getColumnModel().getColumn(2).setPreferredWidth(102);
		table_6.getColumnModel().getColumn(3).setPreferredWidth(113);
		scrollPane_6.setViewportView(table_6);
		
		JButton btnNewButton_11 = new JButton("Prika\u017Ei fakturu");
		btnNewButton_11.setBounds(454, 219, 145, 25);
		panel_6.add(btnNewButton_11);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		tabbedPane.addTab("Izvje\u0161taji", null, panel_7, null);
		panel_7.setLayout(null);
	}

}
