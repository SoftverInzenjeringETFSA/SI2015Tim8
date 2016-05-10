package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodNarudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodavanjeNarudzbeGUI {

	private Session s;
	private NarudzbaServis ns;
	private AkterServis aks;
	private Narudzba n;
	
	private JFrame frmKreiranjeNarudbe;
	private JTextField textField;
	private JTable table1;

	/**
	 * Launch the application.
	 */
	public void startDodavanjeNarudzbe() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeNarudzbeGUI window = new DodavanjeNarudzbeGUI(s, table1);
					window.frmKreiranjeNarudbe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeNarudzbeGUI(Session s, JTable table) 
	{
		this.s = s;
		this.ns = new NarudzbaServis(s);
		this.aks = new AkterServis(s);
		this.n = new Narudzba();
		table1 = table;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeNarudbe = new JFrame();
		frmKreiranjeNarudbe.getContentPane().setBackground(Color.WHITE);
		frmKreiranjeNarudbe.setTitle("Kreiranje narud\u017Ebe");
		frmKreiranjeNarudbe.setBounds(100, 100, 376, 390);
		frmKreiranjeNarudbe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeNarudbe.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naru\u010Dilac:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(58, 56, 78, 16);
		frmKreiranjeNarudbe.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Proizvod:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(58, 112, 78, 16);
		frmKreiranjeNarudbe.getContentPane().add(lblNewLabel_1);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(148, 53, 128, 22);
		frmKreiranjeNarudbe.getContentPane().add(comboBox);
		
		ArrayList<Akterprodaje> regionalni = aks.dajSveAkterePoTipu("regmen");
		for(int i = 0; i < regionalni.size(); i++) comboBox.addItem(regionalni.get(i));
		
		ArrayList<Akterprodaje> prodavaci = aks.dajSveAkterePoTipu("prodavac");
		for(int i = 0; i < prodavaci.size(); i++) comboBox.addItem(prodavaci.get(i));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(80, 85, 196, 156);
		frmKreiranjeNarudbe.getContentPane().add(panel);
		panel.setLayout(null);
		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(72, 25, 111, 22);
		panel.add(comboBox_1);
		
		// za ovo ce se kasnije koristiti service ProizvodServis 
		List<Proizvod> proizvodi = s.createCriteria(Proizvod.class).list();
		for(int i = 0; i < proizvodi.size(); i++) comboBox_1.addItem(proizvodi.get(i));
		
		
		textField = new JTextField();
		textField.setBounds(72, 60, 111, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Koli\u010Dina:");
		lblNewLabel_2.setBounds(-23, 63, 78, 16);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(n == null) n = new Narudzba();
				if(Integer.parseInt(textField.getText())  <= ((Proizvod)comboBox_1.getSelectedItem()).getKolicina())
				{
					ProizvodNarudzba pn = new ProizvodNarudzba();
					pn.setKolicina(Integer.parseInt(textField.getText()));
					pn.setNarudzba(n);
					pn.setProizvod((Proizvod)comboBox_1.getSelectedItem());
					pn.getProizvod().setKolicina(pn.getProizvod().getKolicina() - Integer.parseInt(textField.getText()));
					n.getProizvodNarudzbas().add(pn);
				}
				else JOptionPane.showMessageDialog(null, "Unijeli ste nedozvoljenu količinu!");
			}
		});
		btnNewButton.setBounds(72, 101, 111, 25);
		panel.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("Kreiraj");
		btnNewButton_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(n == null) n = new Narudzba();
				n.setAkterprodaje((Akterprodaje)comboBox.getSelectedItem());
				n.setDatum(new Date());
				n.setStatus("Na čekanju");
				ns.kreirajNarudzbu(n);
				refreshajTabeluNarudzbe();
				n = null;
			}
		});
		btnNewButton_1.setBounds(129, 278, 97, 25);
		frmKreiranjeNarudbe.getContentPane().add(btnNewButton_1);
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
		
		table1.setModel(new DefaultTableModel(
				data,
				new String[] {
					"ID", "Naručilac", "Datum", "Status"
				}
			));
	}

}
