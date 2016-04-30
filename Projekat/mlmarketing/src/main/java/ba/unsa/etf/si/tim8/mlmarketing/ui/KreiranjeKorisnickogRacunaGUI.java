package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class KreiranjeKorisnickogRacunaGUI {

	private JFrame frmDodajKorisnika;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void startKreiranjeRacuna() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KreiranjeKorisnickogRacunaGUI window = new KreiranjeKorisnickogRacunaGUI();
					window.frmDodajKorisnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KreiranjeKorisnickogRacunaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajKorisnika = new JFrame();
		frmDodajKorisnika.setTitle("Dodaj korisnika");
		frmDodajKorisnika.setBounds(100, 100, 360, 450);
		frmDodajKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajKorisnika.getContentPane().setLayout(null);
		
		JLabel lblTipRauna = new JLabel("Tip ra\u010Duna:");
		lblTipRauna.setBounds(63, 30, 71, 14);
		frmDodajKorisnika.getContentPane().add(lblTipRauna);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 27, 124, 20);
		comboBox.addItem("Šef prodaje");
		comboBox.addItem("Komercijalista");
		frmDodajKorisnika.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(30, 102, 104, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Korisni\u010Dka \u0161ifra:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(40, 130, 94, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(146, 99, 124, 20);
		frmDodajKorisnika.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(146, 199, 124, 20);
		frmDodajKorisnika.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(146, 230, 124, 20);
		frmDodajKorisnika.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(146, 261, 124, 20);
		frmDodajKorisnika.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ime:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(88, 202, 46, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Prezime:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(63, 233, 71, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Adresa:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(88, 264, 46, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Kreiraj");
		btnNewButton.setBounds(181, 292, 89, 23);
		frmDodajKorisnika.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 127, 124, 20);
		frmDodajKorisnika.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(146, 158, 124, 20);
		frmDodajKorisnika.getContentPane().add(passwordField_1);
		
		JLabel lblPotvrdiifru = new JLabel("Potvrdi \u0161ifru:");
		lblPotvrdiifru.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPotvrdiifru.setBounds(30, 160, 104, 14);
		frmDodajKorisnika.getContentPane().add(lblPotvrdiifru);
	}

}
