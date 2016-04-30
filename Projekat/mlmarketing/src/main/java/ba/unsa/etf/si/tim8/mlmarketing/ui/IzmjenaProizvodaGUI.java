package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class IzmjenaProizvodaGUI {

	private JFrame frmIzmijeni;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void startIzmjenaProizvoda() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaProizvodaGUI window = new IzmjenaProizvodaGUI();
					window.frmIzmijeni.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzmjenaProizvodaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzmijeni = new JFrame();
		frmIzmijeni.setTitle("Izmijeni");
		frmIzmijeni.setBounds(100, 100, 304, 266);
		frmIzmijeni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmIzmijeni.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Naziv proizvoda:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 36, 109, 14);
		frmIzmijeni.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nabavna cijena:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 61, 109, 14);
		frmIzmijeni.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Prodajna cijena:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 86, 109, 14);
		frmIzmijeni.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Stanje na skladi\u0161tu:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(0, 111, 119, 14);
		frmIzmijeni.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(129, 108, 146, 20);
		frmIzmijeni.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(129, 83, 146, 20);
		frmIzmijeni.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(129, 58, 146, 20);
		frmIzmijeni.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(129, 33, 146, 20);
		frmIzmijeni.getContentPane().add(textField_3);
		
		JButton btnIzmijeniProizvod = new JButton("Izmijeni proizvod");
		btnIzmijeniProizvod.setBounds(159, 154, 116, 23);
		frmIzmijeni.getContentPane().add(btnIzmijeniProizvod);
	}

}
