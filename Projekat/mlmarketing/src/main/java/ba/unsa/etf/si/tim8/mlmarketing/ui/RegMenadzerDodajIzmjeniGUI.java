package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;

public class RegMenadzerDodajIzmjeniGUI {
	
	private Session s;
	private RegijaServis rs;
	private JFrame frmDodajizmijeni;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public void main(final String what) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegMenadzerDodajIzmjeniGUI window = new RegMenadzerDodajIzmjeniGUI(what,s);
					window.frmDodajizmijeni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegMenadzerDodajIzmjeniGUI(String what,Session s) {
		this.s=s;
		this.rs= new RegijaServis(s);
		initialize(what);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String what) {
		frmDodajizmijeni = new JFrame();
		if(what=="dodaj")frmDodajizmijeni.setTitle("Dodaj");
		else frmDodajizmijeni.setTitle("Izmijeni");
		frmDodajizmijeni.setBounds(100, 100, 321, 274);
		frmDodajizmijeni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajizmijeni.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ime i prezime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(46, 60, 84, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Broj telefona:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(35, 85, 95, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adresa:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(56, 110, 74, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(56, 135, 74, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Regija:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(46, 160, 84, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(140, 57, 129, 20);
		frmDodajizmijeni.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 82, 129, 20);
		frmDodajizmijeni.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(140, 107, 129, 20);
		frmDodajizmijeni.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(140, 132, 129, 20);
		frmDodajizmijeni.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(140, 157, 129, 20);
		frmDodajizmijeni.getContentPane().add(comboBox);
		ArrayList<Regija> regije = rs.dajRegije();
		for(int i = 0; i<regije.size();i++) comboBox.addItem(regije.get(i));
		
		JButton btnDodajIzmjeni;
		if(what=="dodaj") btnDodajIzmjeni = new JButton("Dodaj");
		else btnDodajIzmjeni = new JButton("Izmijeni");
		btnDodajIzmjeni.setBounds(180, 188, 89, 23);
		frmDodajizmijeni.getContentPane().add(btnDodajIzmjeni);
		btnDodajIzmjeni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Regija r = (Regija)comboBox.getSelectedItem();
				JOptionPane.showMessageDialog(null, r.getDrzava());
				
				
			}
		});
	}

}
