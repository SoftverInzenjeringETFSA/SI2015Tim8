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

public class SumarniIzvjestajKreiranjeGUI {

	private JFrame frame;
	final static Logger logger = Logger.getLogger(PojedinacniIzvjestajKreiranjeGUI.class);
	private Session s;
	private IzvjestajServis is;
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
		frame.setBounds(100, 100, 450, 300);
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
		lblOd.setBounds(41, 116, 28, 14);
		frame.getContentPane().add(lblOd);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(252, 116, 28, 14);
		frame.getContentPane().add(lblDo);
	
		
		
		final JFormattedTextField formattedTextFieldOd = new JFormattedTextField(datum);
		formattedTextFieldOd.setColumns(10);
		formattedTextFieldOd.setText("  .  .    .");
		formattedTextFieldOd.setBounds(79, 113, 105, 20);
		frame.getContentPane().add(formattedTextFieldOd);
		
		final JFormattedTextField formattedTextFieldDo = new JFormattedTextField(datum);
		formattedTextFieldDo.setColumns(10);
		formattedTextFieldDo.setText("  .  .    .");
		formattedTextFieldDo.setBounds(277, 113, 105, 20);
		frame.getContentPane().add(formattedTextFieldDo);
		
		JButton btnNewButton = new JButton("Generisi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tip = comboBoxTip.getSelectedItem().toString();
				IzvjestajPrikazForme izf = new IzvjestajPrikazForme(is.sumarniIzvjesta(new Date(),new Date(),tip));
				izf.main();
			}
		});
		btnNewButton.setBounds(233, 185, 105, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
