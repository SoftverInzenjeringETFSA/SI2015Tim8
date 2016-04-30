package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class DodavanjeProizvodaGUI {

	private JFrame frmDodajProizvod;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void startDodavanjeProizvoda() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeProizvodaGUI window = new DodavanjeProizvodaGUI();
					window.frmDodajProizvod.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeProizvodaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajProizvod = new JFrame();
		frmDodajProizvod.setTitle("Dodaj proizvod");
		frmDodajProizvod.setBounds(100, 100, 348, 300);
		frmDodajProizvod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajProizvod.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naziv proizvoda:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(41, 46, 109, 14);
		frmDodajProizvod.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nabavna cijena:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(51, 71, 99, 14);
		frmDodajProizvod.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prodajna cijena:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(41, 96, 109, 14);
		frmDodajProizvod.getContentPane().add(lblNewLabel_2);
		
		JLabel lblStanjeNaSkladitu = new JLabel("Stanje na skladi\u0161tu:");
		lblStanjeNaSkladitu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStanjeNaSkladitu.setBounds(12, 121, 138, 14);
		frmDodajProizvod.getContentPane().add(lblStanjeNaSkladitu);
		
		textField = new JTextField();
		textField.setBounds(160, 43, 146, 20);
		frmDodajProizvod.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnDodajProizvod = new JButton("Dodaj proizvod");
		btnDodajProizvod.setBounds(190, 164, 116, 23);
		frmDodajProizvod.getContentPane().add(btnDodajProizvod);
		
		textField_1 = new JTextField();
		textField_1.setBounds(160, 68, 146, 20);
		frmDodajProizvod.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(160, 93, 146, 20);
		frmDodajProizvod.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(160, 118, 146, 20);
		frmDodajProizvod.getContentPane().add(textField_3);
		textField_3.setColumns(10);
	}

}
