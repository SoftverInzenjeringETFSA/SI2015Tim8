package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Faktura;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodFaktura;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;

public class PrikazFakturaGUI {
	
	final static Logger logger = Logger.getLogger(PrikazFakturaGUI.class);
	private Session s;
	private int id;
	private NarudzbaServis ns;

	private JFrame frmFaktura;
	private JTextField textFieldId;
	private JTextField textFieldDatum;
	private JTextField textFieldNarucilac;
	private JTextField textFieldUkupnaCijena;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void startPrikazFakture() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazFakturaGUI window = new PrikazFakturaGUI(s, id);
					window.frmFaktura.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazFakturaGUI(Session s, int id) 
	{
		this.s = s;
		this.id = id;
		this.ns = new NarudzbaServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFaktura = new JFrame();
		frmFaktura.setTitle("Faktura");
		frmFaktura.getContentPane().setBackground(Color.WHITE);
		frmFaktura.setBounds(100, 100, 450, 469);
		frmFaktura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFaktura.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u0160ifra:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(104, 39, 56, 16);
		frmFaktura.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Datum:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(104, 78, 56, 16);
		frmFaktura.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Naru\u010Dilac:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(60, 122, 100, 16);
		frmFaktura.getContentPane().add(label_2);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(192, 36, 145, 22);
		frmFaktura.getContentPane().add(textFieldId);
		
		textFieldDatum = new JTextField();
		textFieldDatum.setEditable(false);
		textFieldDatum.setColumns(10);
		textFieldDatum.setBounds(192, 75, 145, 22);
		frmFaktura.getContentPane().add(textFieldDatum);
		
		textFieldNarucilac = new JTextField();
		textFieldNarucilac.setEditable(false);
		textFieldNarucilac.setColumns(10);
		textFieldNarucilac.setBounds(192, 119, 145, 22);
		frmFaktura.getContentPane().add(textFieldNarucilac);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 158, 233, 149);
		frmFaktura.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Proizvod", "Koli\u010Dina", "Cijena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Ukupna cijena:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(60, 340, 100, 16);
		frmFaktura.getContentPane().add(lblNewLabel);
		
		textFieldUkupnaCijena = new JTextField();
		textFieldUkupnaCijena.setEditable(false);
		textFieldUkupnaCijena.setColumns(10);
		textFieldUkupnaCijena.setBounds(192, 337, 145, 22);
		frmFaktura.getContentPane().add(textFieldUkupnaCijena);
		prikaziPodatke();
		refreshajTabeluProizvodi();
	}
	
	private void prikaziPodatke()
	{
		Faktura prikaz = ns.dajFakturu(id);
		if(prikaz != null)
		{
			textFieldId.setText(prikaz.getId().toString());
			textFieldDatum.setText(prikaz.getDatum().toString());
			textFieldNarucilac.setText(prikaz.getImeaktera());
			textFieldUkupnaCijena.setText(Double.toString(prikaz.getUkupnacijena()));
			
		}
	}
	
	private void refreshajTabeluProizvodi()
	{
		Faktura prikaz = ns.dajFakturu(id);
		if(prikaz != null)
		{
			ProizvodFaktura[] pf = prikaz.getProizvodFakturas().toArray(new ProizvodFaktura[prikaz.getProizvodFakturas().size()]);
			Object[][] data = new Object[pf.length][];
			for(int i = 0; i<pf.length;i++)
				data[i]= new Object[]
				{
					pf[i].getNazivproizvoda(), pf[i].getKolicina()
				};
			
			table.setModel(new MyTableModel(
					data,
					new String[] {
						"Proizvod", "Količina"
					}
				));
		}
	}

}
