package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.IzvjestajServis;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PojedinacniIzvjestajKreiranjeGUI {

	private JFrame frmPojedinacniIzvjestaj;
	private JTextField textField;
	private Session s;
	private AkterServis aks;
	private IzvjestajServis is;
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPojedinacniIzvjestaj = new JFrame();
		frmPojedinacniIzvjestaj.setTitle("Pojedinacni izvjestaj");
		frmPojedinacniIzvjestaj.setBounds(100, 100, 450, 300);
		frmPojedinacniIzvjestaj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPojedinacniIzvjestaj.getContentPane().setLayout(null);
		
		JComboBox comboBoxTip = new JComboBox();
		
		comboBoxTip.addItem("Za isplatu");
		comboBoxTip.setBounds(76, 46, 120, 20);
		frmPojedinacniIzvjestaj.getContentPane().add(comboBoxTip);
		
		JComboBox comboBoxEntitet = new JComboBox();
		comboBoxEntitet.setBounds(233, 46, 120, 20);
		frmPojedinacniIzvjestaj.getContentPane().add(comboBoxEntitet);
		
		JComboBox comboBoxMjesec = new JComboBox();
		comboBoxMjesec.setBounds(129, 95, 67, 20);
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
		labelMjesec.setBounds(73, 98, 46, 14);
		frmPojedinacniIzvjestaj.getContentPane().add(labelMjesec);
		
		JLabel lblNewLabel = new JLabel("Godina:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(233, 98, 46, 14);
		frmPojedinacniIzvjestaj.getContentPane().add(lblNewLabel);
		
		JButton btnGenerisi = new JButton("Generiši");
		btnGenerisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//int id = comboBoxEntitet.getSelectedIndex()
				//is.pojedinacniIzvjesta(comboBoxTip.getSelectedItem(), comboBoxMjesec.getSelectedItem(),Integer.parseInt(textField.getText()), id);
			}
		});
		btnGenerisi.setBounds(264, 177, 89, 23);
		frmPojedinacniIzvjestaj.getContentPane().add(btnGenerisi);
		
		textField = new JTextField();
		textField.setBounds(286, 95, 67, 20);
		frmPojedinacniIzvjestaj.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
