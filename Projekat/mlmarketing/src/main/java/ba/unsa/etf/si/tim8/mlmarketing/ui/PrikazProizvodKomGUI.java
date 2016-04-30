package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PrikazProizvodKomGUI {

	private JFrame frmProizvod;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProizvodKomGUI window = new PrikazProizvodKomGUI();
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
	public PrikazProizvodKomGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProizvod = new JFrame();
		frmProizvod.setTitle("Proizvod");
		frmProizvod.getContentPane().setBackground(Color.WHITE);
		frmProizvod.setBounds(100, 100, 372, 336);
		frmProizvod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProizvod.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Naziv proizvoda:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(50, 65, 105, 16);
		frmProizvod.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nabavna cijena:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(50, 112, 105, 16);
		frmProizvod.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Prodajna cijena:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(39, 160, 116, 16);
		frmProizvod.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Stanje na skladi\u0161tu:");
		label_3.setBounds(39, 205, 116, 16);
		frmProizvod.getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(180, 62, 116, 22);
		frmProizvod.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(180, 109, 116, 22);
		frmProizvod.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(180, 157, 116, 22);
		frmProizvod.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(180, 202, 116, 22);
		frmProizvod.getContentPane().add(textField_3);
	}

}
