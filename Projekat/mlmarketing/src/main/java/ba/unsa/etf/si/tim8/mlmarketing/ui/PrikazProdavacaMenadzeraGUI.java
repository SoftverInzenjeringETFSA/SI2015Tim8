package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;

public class PrikazProdavacaMenadzeraGUI {
	
	final static Logger logger = Logger.getLogger(PrikazProdavacaMenadzeraGUI.class);
	private Session s;
	private AkterServis aks;
	private int id;
	

	private JFrame frmProdavai;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void startPrikazProdavaca() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProdavacaMenadzeraGUI window = new PrikazProdavacaMenadzeraGUI(s, id);
					window.frmProdavai.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProdavacaMenadzeraGUI(Session s, int id) {
		this.s = s;
		this.id = id;
		this.aks = new AkterServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProdavai = new JFrame();
		frmProdavai.setTitle("Prodava\u010Di");
		frmProdavai.getContentPane().setBackground(Color.WHITE);
		frmProdavai.setBounds(100, 100, 476, 399);
		frmProdavai.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdavai.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 434, 254);
		frmProdavai.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime", "Adresa", "Broj telefona", "Email", "Regija"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(109);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(3).setPreferredWidth(104);
		table.getColumnModel().getColumn(4).setPreferredWidth(99);
		scrollPane.setViewportView(table);
		refreshajTabeluProdavaci();
	}
	
	public void refreshajTabeluProdavaci()
	{
		Akterprodaje ak = aks.dajAktera(id);
		ArrayList<Akterprodaje> sviAkteri = aks.dajSveAkterePoTipu("prodavac");
		ArrayList<Akterprodaje> akteri = new ArrayList<Akterprodaje>();
		
		for(int i = 0; i < sviAkteri.size(); i++)
		{
			if(sviAkteri.get(i).getAkterprodaje() == ak)
			{
				akteri.add(sviAkteri.get(i));
			}
		}
		
		
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
			
			table.setModel(new MyTableModel(
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
			table.getColumnModel().removeColumn(table.getColumnModel().getColumn(6));
		}
	}

}
