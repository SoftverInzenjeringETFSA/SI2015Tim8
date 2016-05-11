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

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;

/*import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NaloziServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;*/

import org.hibernate.Session;

public class KomercijalistaMainGUI {
	
	private Session s;
	private NarudzbaServis ns;
	private AkterServis aks;

	private JFrame frmKomercijalista;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;

	/**
	 * Launch the application.
	 */
	public void startKomercijalistaMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KomercijalistaMainGUI window = new KomercijalistaMainGUI(s);
					window.frmKomercijalista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KomercijalistaMainGUI(Session s) {
		this.s=s;
		this.ns = new NarudzbaServis(s);
		this.aks = new AkterServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKomercijalista = new JFrame();
		frmKomercijalista.setTitle("Komercijalista");
		frmKomercijalista.getContentPane().setBackground(Color.WHITE);
		frmKomercijalista.setBounds(100, 100, 685, 473);
		frmKomercijalista.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKomercijalista.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 42, 643, 371);
		frmKomercijalista.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Narud\u017Ebe", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 614, 243);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Status"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(114);
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("Obri\u0161i");
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(table.getSelectedRow()!=-1)
				{
					int id =(Integer)table.getModel().getValueAt(table.getSelectedRow(),0);
					ns.izbrisiNarudzbu(id);
					refreshajTabeluNarudzbe();
				}
				else JOptionPane.showMessageDialog(null, "Niste odabrali narudzbu!");
			}
		});
		button.setBounds(353, 280, 97, 25);
		panel.add(button);
		
		JButton btnKreirajNarudzu = new JButton("Dodaj");
		btnKreirajNarudzu.setBounds(232, 280, 97, 25);
		panel.add(btnKreirajNarudzu);
		btnKreirajNarudzu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				DodavanjeNarudzbeGUI dodavanjenarudzbe = new DodavanjeNarudzbeGUI(s, table);
				dodavanjenarudzbe.startDodavanjeNarudzbe();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Prika\u017Ei narud\u017Ebu");
		btnNewButton_1.setBounds(468, 280, 158, 25);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Kreiraj fakturu");
		btnNewButton_3.setBounds(12, 280, 134, 25);
		panel.add(btnNewButton_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		tabbedPane.addTab("Fakture", null, panel_4, null);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(12, 13, 614, 228);
		panel_4.add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Ukupna cijena"
			}
		));
		table_4.getColumnModel().getColumn(0).setPreferredWidth(98);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(113);
		scrollPane_4.setViewportView(table_4);
		
		JButton btnPrikazFakture = new JButton("Prika\u017Ei fakturu");
		btnPrikazFakture.setBounds(481, 260, 145, 25);
		panel_4.add(btnPrikazFakture);
		btnPrikazFakture.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PrikazFakturaGUI prikazfaktura = new PrikazFakturaGUI();
				prikazfaktura.startPrikazFakture();
				
			}
		});
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Regionalni menad\u017Eeri", null, panel_3, null);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_2.setBounds(12, 13, 614, 232);
		panel_3.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime", "Adresa", "Broj telefona", "Email", "Regija"
			}
		));
		table_2.getColumnModel().getColumn(0).setPreferredWidth(99);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(95);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(105);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(90);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(100);
		scrollPane_2.setViewportView(table_2);
		
		JButton btnPrikazMenadzera = new JButton("Prika\u017Ei aktera");
		btnPrikazMenadzera.setBounds(513, 271, 113, 23);
		panel_3.add(btnPrikazMenadzera);
		btnPrikazMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) 
			{
				int id = odaberiIdKolonu(table_2, 5);
				PrikazMenadzerKomGUI prikazmenadzer = new PrikazMenadzerKomGUI(s, id);
				prikazmenadzer.startPrikazMenadzer();
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Prodava\u010Di", null, panel_2, null);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBounds(12, 13, 614, 232);
		panel_2.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime", "Adresa", "Broj telefona", "Email", "Regija", "Nadle\u017Eni menad\u017Eer"
			}
		));
		table_3.getColumnModel().getColumn(0).setPreferredWidth(90);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(96);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(99);
		table_3.getColumnModel().getColumn(3).setPreferredWidth(102);
		table_3.getColumnModel().getColumn(4).setPreferredWidth(106);
		table_3.getColumnModel().getColumn(5).setPreferredWidth(126);
		scrollPane_3.setViewportView(table_3);
		
		JButton btnPrikaziProdavaca = new JButton("Prika\u017Ei aktera");
		btnPrikaziProdavaca.setBounds(513, 277, 113, 23);
		panel_2.add(btnPrikaziProdavaca);
		btnPrikaziProdavaca.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int id = odaberiIdKolonu(table_3, 6);
				PrikazProdavacKomGUI prikazprodavac= new PrikazProdavacKomGUI(s, id);
				prikazprodavac.startPrikazProdavaca();
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Proizvodi", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnPrikaziProizvod = new JButton("Prika\u017Ei proizvod");
		btnPrikaziProizvod.setBounds(487, 276, 139, 25);
		panel_1.add(btnPrikaziProizvod);
		btnPrikaziProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//PrikazProdavacKomGUI prikazproizvoda = new PrikazProdavacKomGUI();
				//prikazproizvoda.startPrikazProdavaca();
				
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 13, 614, 231);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Naziv proizvoda", "Nabavna cijena", "Prodajna cijena", "Stanje na skladi\u0161tu"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(108);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(103);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(102);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(112);
		scrollPane_1.setViewportView(table_1);
		
		JButton btnNewButton_2 = new JButton("Odjava sa sistema");
		btnNewButton_2.setBounds(499, 13, 156, 25);
		frmKomercijalista.getContentPane().add(btnNewButton_2);
		refreshajTabeluNarudzbe();
		refreshajTabeluMenadzeri();
		refreshajTabeluProdavaci();
	}
	
	private void refreshajTabeluNarudzbe()
	{
		ArrayList<Narudzba> narudzbe = ns.dajNarudzbe();
		Object[][] data = new Object[narudzbe.size()][];
		for(int i = 0; i<narudzbe.size();i++)
			data[i]= new Object[]
			{
				narudzbe.get(i).getId(),narudzbe.get(i).getAkterprodaje().getIme(),
				narudzbe.get(i).getDatum(),narudzbe.get(i).getStatus()
					
			};
		
		table.setModel(new DefaultTableModel(
				data,
				new String[] {
					"ID", "Naručilac", "Datum", "Status"
				}
			));
	}
	
	public void refreshajTabeluMenadzeri(){
		ArrayList<Akterprodaje> akteri = aks.dajSveAkterePoTipu("regmen");
		Object[][] data = new Object[akteri.size()][];
		for(int i = 0; i<akteri.size();i++) data[i]= new Object[]{akteri.get(i).getIme()+" "+akteri.get(i).getPrezime(),
				akteri.get(i).getBrojtelefona(), akteri.get(i).getAdresa(), akteri.get(i).getEmail(),
				akteri.get(i).getRegija().getIme(), akteri.get(i).getId()
				};
		
		table_2.setModel(new DefaultTableModel(
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
		
		table_2.getColumnModel().removeColumn(table_2.getColumnModel().getColumn(5));
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
			
			table_3.setModel(new DefaultTableModel(
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
			table_3.getColumnModel().removeColumn(table_3.getColumnModel().getColumn(6));
		}
	}
	
	public int odaberiIdKolonu(JTable tabela,int brojKolone){
		int id = (Integer)tabela.getModel().getValueAt(tabela.getSelectedRow(),brojKolone);
		return id;
	}

}
