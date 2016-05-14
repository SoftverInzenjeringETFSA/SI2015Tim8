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
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;

public class PrikazNarudzbiAkterGUI {
	
	final static Logger logger = Logger.getLogger(PrikazNarudzbiAkterGUI.class);
	private Session s;
	private int id;
	private AkterServis aks;
	private NarudzbaServis ns;

	private JFrame frmPregledNarudbi;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void startPrikazNarudzbiAkter() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazNarudzbiAkterGUI window = new PrikazNarudzbiAkterGUI(s, id);
					window.frmPregledNarudbi.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazNarudzbiAkterGUI(Session s, int id) 
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
		frmPregledNarudbi = new JFrame();
		frmPregledNarudbi.getContentPane().setBackground(Color.WHITE);
		frmPregledNarudbi.setTitle("Pregled narud\u017Ebi");
		frmPregledNarudbi.setBounds(100, 100, 476, 399);
		frmPregledNarudbi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPregledNarudbi.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 434, 248);
		frmPregledNarudbi.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Naru\u010Dilac", "Datum", "Status"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		table.getColumnModel().getColumn(1).setPreferredWidth(107);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		table.getColumnModel().getColumn(3).setPreferredWidth(99);
		scrollPane.setViewportView(table);
		refreshajTabeluNarudzbe();
	}
	
	private void refreshajTabeluNarudzbe()
	{
		ArrayList<Narudzba> sveNarudzbe = ns.dajNarudzbe();
		ArrayList<Narudzba> narudzbe = new ArrayList<Narudzba>();
		Akterprodaje ak = aks.dajAktera(id);
		
		for(int i = 0; i < sveNarudzbe.size(); i++)
		{
			if(sveNarudzbe.get(i).getAkterprodaje() == ak)
			{
				narudzbe.add(sveNarudzbe.get(i));
			}
		}
		Object[][] data = new Object[narudzbe.size()][];
		for(int i = 0; i<narudzbe.size();i++)
			data[i]= new Object[]
			{
				narudzbe.get(i).getId(),narudzbe.get(i).getAkterprodaje().getIme() + " " + narudzbe.get(i).getAkterprodaje().getPrezime(),
				narudzbe.get(i).getDatum(),narudzbe.get(i).getStatus()
					
			};
		
		table.setModel(new MyTableModel(
				data,
				new String[] {
					"ID", "Naručilac", "Datum", "Status"
				}
			));
	}

}