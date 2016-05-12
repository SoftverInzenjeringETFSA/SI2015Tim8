package ba.unsa.etf.si.tim8.mlmarketing.ui;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import ba.unsa.etf.si.tim8.mlmarketing.ui.MyTableModel;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodNarudzba;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PrikazNarudzbaKomGUI {
	
	private Session s;
	private NarudzbaServis ns;
	private int id;

	private JFrame frmNarudba;
	private JTextField textFieldId;
	private JTextField textFieldDatum;
	private JTextField textFieldNarucilac;
	private JComboBox comboBoxStatus;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public void startPrikazNarudzbaKom() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazNarudzbaKomGUI window = new PrikazNarudzbaKomGUI(s, id);
					window.frmNarudba.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazNarudzbaKomGUI(Session s, int id) 
	{
		this.s = s;
		this.ns = new NarudzbaServis(s);
		this.id = id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNarudba = new JFrame();
		frmNarudba.getContentPane().setBackground(Color.WHITE);
		frmNarudba.setTitle("Narud\u017Eba");
		frmNarudba.setBounds(100, 100, 389, 478);
		frmNarudba.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNarudba.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(71, 64, 56, 16);
		frmNarudba.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datum:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(71, 103, 56, 16);
		frmNarudba.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Naru\u010Dilac:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(27, 147, 100, 16);
		frmNarudba.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Status:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(71, 345, 56, 16);
		frmNarudba.getContentPane().add(lblNewLabel_4);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setBounds(159, 61, 145, 22);
		frmNarudba.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldDatum = new JTextField();
		textFieldDatum.setEditable(false);
		textFieldDatum.setColumns(10);
		textFieldDatum.setBounds(159, 100, 145, 22);
		frmNarudba.getContentPane().add(textFieldDatum);
		
		textFieldNarucilac = new JTextField();
		textFieldNarucilac.setEditable(false);
		textFieldNarucilac.setColumns(10);
		textFieldNarucilac.setBounds(159, 144, 145, 22);
		frmNarudba.getContentPane().add(textFieldNarucilac);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 183, 233, 149);
		frmNarudba.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Proizvod", "Koli\u010Dina"
			}
		));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setEditable(true);
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Potvr\u0111ena", "Odbijena", "Na \u010Dekanju"}));
		comboBoxStatus.setBounds(159, 342, 145, 22);
		frmNarudba.getContentPane().add(comboBoxStatus);
		prikaziPodatke();
		refreshajTabeluProizvodi();
	}
	
	private void prikaziPodatke()
	{
		Narudzba prikaz = ns.dajNarudzbu(id);
		if(prikaz != null)
		{
			textFieldId.setText(prikaz.getId().toString());
			textFieldDatum.setText(prikaz.getDatum().toString());
			textFieldNarucilac.setText(prikaz.getAkterprodaje().getIme() + " " + prikaz.getAkterprodaje().getPrezime());
			comboBoxStatus.setSelectedItem(prikaz.getStatus());
			
		}
	}
	
	private void refreshajTabeluProizvodi()
	{
		Narudzba prikaz = ns.dajNarudzbu(id);
		if(prikaz != null)
		{
			ProizvodNarudzba[] pn = prikaz.getProizvodNarudzbas().toArray(new ProizvodNarudzba[prikaz.getProizvodNarudzbas().size()]);
			Object[][] data = new Object[pn.length][];
			for(int i = 0; i<pn.length;i++)
				data[i]= new Object[]
				{
					pn[i].getProizvod().getNaziv(), pn[i].getKolicina()
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
