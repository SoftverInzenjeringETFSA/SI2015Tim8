package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.IzvjestajServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class PojedinacniIzvjestajKreiranjeGUI {

	private JFrame frmPojedinacniIzvjestaj;
	private JTextField textField;
	private Session s;
	private AkterServis aks;
	private IzvjestajServis is;
	private ProizvodServis ps;
	final static Logger logger = Logger.getLogger(PojedinacniIzvjestajKreiranjeGUI.class);
	/**
	 * Launch the application.
	 */
	public void startPojedinacniIzvjestaj() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PojedinacniIzvjestajKreiranjeGUI window = new PojedinacniIzvjestajKreiranjeGUI(s);
					window.frmPojedinacniIzvjestaj.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PojedinacniIzvjestajKreiranjeGUI(Session s) {
		this.s=s;
		this.aks= new AkterServis(s);
		this.is = new IzvjestajServis(s);
		this.ps = new ProizvodServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPojedinacniIzvjestaj = new JFrame();
		frmPojedinacniIzvjestaj.setTitle("Pojedinacni izvjestaj");
		frmPojedinacniIzvjestaj.setBounds(100, 100, 450, 300);
		frmPojedinacniIzvjestaj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPojedinacniIzvjestaj.getContentPane().setLayout(null);
		
		final JComboBox comboBoxTip = new JComboBox();
		//comboBoxTip.setModel(new DefaultComboBoxModel(new String[] {, "Naruceni proizvodi", "Po proizvodu"}));
		comboBoxTip.addItem("Za isplatu");
		comboBoxTip.addItem("Naruceni proizvodi");
		comboBoxTip.addItem("Po proizvodu");
		
		comboBoxTip.setBounds(76, 46, 120, 20);
		frmPojedinacniIzvjestaj.getContentPane().add(comboBoxTip);
		
		final JComboBox comboBoxMenadzeri = new JComboBox();
		comboBoxMenadzeri.setBounds(233, 46, 120, 20);
		frmPojedinacniIzvjestaj.getContentPane().add(comboBoxMenadzeri);
		
		final JComboBox comboBoxMjesec = new JComboBox();
		comboBoxMjesec.setBounds(129, 77, 67, 20);
		comboBoxMjesec.addItem("01");
		comboBoxMjesec.addItem("02");
		comboBoxMjesec.addItem("03");
		comboBoxMjesec.addItem("04");
		comboBoxMjesec.addItem("05");
		comboBoxMjesec.addItem("06");
		comboBoxMjesec.addItem("07");
		comboBoxMjesec.addItem("08");
		comboBoxMjesec.addItem("09");
		comboBoxMjesec.addItem("10");
		comboBoxMjesec.addItem("11");
		comboBoxMjesec.addItem("12");
		
		frmPojedinacniIzvjestaj.getContentPane().add(comboBoxMjesec);
		
		JLabel labelMjesec = new JLabel("Mjesec:");
		labelMjesec.setHorizontalAlignment(SwingConstants.RIGHT);
		labelMjesec.setBounds(24, 80, 46, 14);
		frmPojedinacniIzvjestaj.getContentPane().add(labelMjesec);
		
		JLabel lblNewLabel = new JLabel("Godina:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(24, 111, 46, 14);
		frmPojedinacniIzvjestaj.getContentPane().add(lblNewLabel);
		
		
		
		
		textField = new JTextField();
		textField.setBounds(129, 108, 67, 20);
		frmPojedinacniIzvjestaj.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JComboBox comboBoxProizvodi = new JComboBox();
		comboBoxProizvodi.setBounds(233, 77, 120, 20);
		frmPojedinacniIzvjestaj.getContentPane().add(comboBoxProizvodi);
		
		JLabel lblTip = new JLabel("Tip:");
		lblTip.setHorizontalAlignment(SwingConstants.CENTER);
		lblTip.setBounds(24, 49, 46, 14);
		frmPojedinacniIzvjestaj.getContentPane().add(lblTip);
		comboBoxMenadzeri.setEnabled(true);
		comboBoxProizvodi.setEnabled(false);
		
		ArrayList<Akterprodaje> al = aks.dajSveAktere();
		for(int i = 0; i< al.size();i++){
			comboBoxMenadzeri.addItem(al.get(i));
		}
		
		ArrayList<Proizvod> pl = ps.dajSveProizvode();
		for(int i = 0; i< pl.size();i++){
			comboBoxProizvodi.addItem(pl.get(i));
		}
		
		comboBoxTip.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
		         if(comboBoxTip.getSelectedItem().equals("Po proizvodu")){
		        	 comboBoxMenadzeri.setEnabled(false);
		        	 comboBoxProizvodi.setEnabled(true);
		         }
		         else{
		        	 comboBoxMenadzeri.setEnabled(true);
		        	 comboBoxProizvodi.setEnabled(false);
		         }
			}
		});
		
		JButton btnGenerisi = new JButton("Generiši");
		btnGenerisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ValidacijeServis.daLiJeInt(textField.getText())){
					int id;
					if(comboBoxMenadzeri.isEnabled()){
						if(comboBoxMenadzeri.getItemCount()!=0){
							id = ((Akterprodaje)comboBoxMenadzeri.getSelectedItem()).getId();
						}
						else{
							JOptionPane.showMessageDialog(null, "Lista prodavaca je prazna");
							return;
						}
					}
					else{
						if(comboBoxProizvodi.getItemCount()!=0){
							id = ((Proizvod)comboBoxProizvodi.getSelectedItem()).getId();
						}
						else{
							JOptionPane.showMessageDialog(null, "Lista proizvoda je prazna");
							return;
						}
					}
					String tip = comboBoxTip.getSelectedItem().toString();
					String mjesec = comboBoxMjesec.getSelectedItem().toString();
					
					IzvjestajPrikazForme izf = new IzvjestajPrikazForme(is.pojedinacniIzvjesta(tip, mjesec,Integer.parseInt(textField.getText()), id));
					izf.main();
					
					
					
				}
				else JOptionPane.showMessageDialog(null, "Unesite validnu godinu!");
				
			}
		});
		btnGenerisi.setBounds(264, 166, 89, 23);
		frmPojedinacniIzvjestaj.getContentPane().add(btnGenerisi);
	}
}
