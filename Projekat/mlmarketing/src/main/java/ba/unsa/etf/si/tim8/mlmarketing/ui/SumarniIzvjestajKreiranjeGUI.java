package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.IzvjestajServis;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class SumarniIzvjestajKreiranjeGUI {

	private JFrame frame;
	final static Logger logger = Logger.getLogger(PojedinacniIzvjestajKreiranjeGUI.class);
	private Session s;
	private IzvjestajServis is;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	/**
	 * Launch the application.
	 */
	public void startSumarni() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SumarniIzvjestajKreiranjeGUI window = new SumarniIzvjestajKreiranjeGUI(s);
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SumarniIzvjestajKreiranjeGUI(Session s) {
		this.s=s;
		this.is= new IzvjestajServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 372, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MaskFormatter datum = new MaskFormatter();
		try {
			datum = new MaskFormatter("##.##.####.");
			datum.setPlaceholderCharacter(' ');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info(e);// e.printStackTrace();
		}
		
		JLabel lblSumarniIzvjestajZa = new JLabel("Sumarni izvjestaj za:");
		lblSumarniIzvjestajZa.setBounds(105, 50, 105, 14);
		frame.getContentPane().add(lblSumarniIzvjestajZa);
		
		final JComboBox comboBoxTip = new JComboBox();
		comboBoxTip.setBounds(233, 47, 105, 20);
		comboBoxTip.addItem("Proizvodi");
		comboBoxTip.addItem("Prodavaci");
		frame.getContentPane().add(comboBoxTip);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(28, 90, 28, 14);
		frame.getContentPane().add(lblOd);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(218, 90, 28, 14);
		frame.getContentPane().add(lblDo);
		
		JButton btnNewButton = new JButton("Generisi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tip = comboBoxTip.getSelectedItem().toString();
				Date datumPocetni = is.pocetniDatumSaDanom(1, "01", 2012);
				Date datumKrajnji = is.krajnjiDatumSaDanom(4, "06", 2016);
				IzvjestajPrikazForme izf = new IzvjestajPrikazForme(is.sumarniIzvjesta(datumPocetni,datumKrajnji,tip));
				izf.main();
			}
		});
		btnNewButton.setBounds(233, 210, 105, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblDan = new JLabel("Dan:");
		lblDan.setBounds(20, 115, 28, 14);
		frame.getContentPane().add(lblDan);
		
		JLabel lblMjesec = new JLabel("Mjesec:");
		lblMjesec.setBounds(10, 140, 38, 14);
		frame.getContentPane().add(lblMjesec);
		
		JLabel lblGodina = new JLabel("Godina:");
		lblGodina.setBounds(10, 164, 38, 14);
		frame.getContentPane().add(lblGodina);
		
		textField = new JTextField();
		textField.setBounds(58, 112, 76, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(58, 161, 76, 20);
		frame.getContentPane().add(textField_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(58, 137, 76, 20);
		frame.getContentPane().add(comboBox);
		
		JLabel label = new JLabel("Dan:");
		label.setBounds(218, 115, 28, 14);
		frame.getContentPane().add(label);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(262, 112, 76, 20);
		frame.getContentPane().add(textField_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(262, 137, 76, 20);
		frame.getContentPane().add(comboBox_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(262, 161, 76, 20);
		frame.getContentPane().add(textField_3);
		
		JLabel label_1 = new JLabel("Godina:");
		label_1.setBounds(208, 164, 38, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Mjesec:");
		label_2.setBounds(208, 140, 38, 14);
		frame.getContentPane().add(label_2);
	}
}
