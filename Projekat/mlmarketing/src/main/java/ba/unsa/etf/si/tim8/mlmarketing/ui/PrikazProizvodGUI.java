package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PrikazProizvodGUI {

	private JFrame frmProizvod;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void startPrikazProizvod() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProizvodGUI window = new PrikazProizvodGUI();
					window.frmProizvod.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProizvodGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProizvod = new JFrame();
		frmProizvod.getContentPane().setBackground(Color.WHITE);
		frmProizvod.setTitle("Proizvod");
		frmProizvod.setBounds(100, 100, 450, 417);
		frmProizvod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProizvod.getContentPane().setLayout(null);
		
		JLabel lblNazivProizvoda = new JLabel("Naziv proizvoda:");
		lblNazivProizvoda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazivProizvoda.setBounds(79, 76, 105, 16);
		frmProizvod.getContentPane().add(lblNazivProizvoda);
		
		JLabel lblNabavnaCijena = new JLabel("Nabavna cijena:");
		lblNabavnaCijena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNabavnaCijena.setBounds(79, 123, 105, 16);
		frmProizvod.getContentPane().add(lblNabavnaCijena);
		
		JLabel lblProdajnaCijena = new JLabel("Prodajna cijena:");
		lblProdajnaCijena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProdajnaCijena.setBounds(68, 171, 116, 16);
		frmProizvod.getContentPane().add(lblProdajnaCijena);
		
		JLabel lblStanjeNaSkladitu = new JLabel("Stanje na skladi\u0161tu:");
		lblStanjeNaSkladitu.setBounds(68, 216, 116, 16);
		frmProizvod.getContentPane().add(lblStanjeNaSkladitu);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(209, 73, 116, 22);
		frmProizvod.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(209, 120, 116, 22);
		frmProizvod.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(209, 168, 116, 22);
		frmProizvod.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(209, 213, 116, 22);
		frmProizvod.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Potvrdi promjenu stanja");
		btnNewButton.setBounds(94, 269, 231, 25);
		frmProizvod.getContentPane().add(btnNewButton);
	}

}
