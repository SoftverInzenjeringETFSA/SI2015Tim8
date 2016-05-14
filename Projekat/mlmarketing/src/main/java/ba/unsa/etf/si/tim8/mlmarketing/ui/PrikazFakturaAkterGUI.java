package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;

public class PrikazFakturaAkterGUI {
	
	final static Logger logger = Logger.getLogger(PrikazFakturaAkterGUI.class);
	private Session s;
	private int id;
	private AkterServis aks;
	private NarudzbaServis ns;

	private JFrame frmPregledFaktura;
	private JTable tableFakture;

	/**
	 * Launch the application.
	 */
	public void startPrikazFakturaAkter() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazFakturaAkterGUI window = new PrikazFakturaAkterGUI(s, id);
					window.frmPregledFaktura.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazFakturaAkterGUI(Session s, int id) 
	{
		this.s = s;
		this.id = id;
		this.aks = new AkterServis(s);
		this.ns = new NarudzbaServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPregledFaktura = new JFrame();
		frmPregledFaktura.setTitle("Pregled faktura");
		frmPregledFaktura.getContentPane().setBackground(Color.WHITE);
		frmPregledFaktura.setBounds(100, 100, 476, 399);
		frmPregledFaktura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPregledFaktura.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 434, 246);
		frmPregledFaktura.getContentPane().add(scrollPane);
		
		tableFakture = new JTable();
		tableFakture.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Ukupna cijena"
			}
		));
		tableFakture.getColumnModel().getColumn(0).setPreferredWidth(98);
		tableFakture.getColumnModel().getColumn(1).setPreferredWidth(92);
		tableFakture.getColumnModel().getColumn(2).setPreferredWidth(97);
		tableFakture.getColumnModel().getColumn(3).setPreferredWidth(95);
		scrollPane.setViewportView(tableFakture);
		refreshajTabeluFakture();
	}
	
	private void refreshajTabeluFakture()
	{
		ArrayList<Faktura> sveFakture = ns.dajFakture();
		ArrayList<Faktura> fakture = new ArrayList<Faktura>();
		Akterprodaje ak = aks.dajAktera(id);
		
		for(int i = 0; i < sveFakture.size(); i++)
		{
			if(sveFakture.get(i).getAkterprodaje() == ak)
			{
				fakture.add(sveFakture.get(i));
			}
		}
		Object[][] data = new Object[fakture.size()][];
		for(int i = 0; i<fakture.size();i++)
			data[i]= new Object[]
			{
				fakture.get(i).getId(),fakture.get(i).getAkterprodaje().getIme() + " " + fakture.get(i).getAkterprodaje().getPrezime(),
				fakture.get(i).getDatum(),fakture.get(i).getUkupnacijena()
					
			};
		
		tableFakture.setModel(new MyTableModel(
				data,
				new String[] {
					"ID", "Naručilac", "Datum", "Ukupna cijena"
				}
			));
	}

}

