package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PrikazProdavacGUI {

	private JFrame frmPregledProdavac;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void startPrikazProdavac() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProdavacGUI window = new PrikazProdavacGUI();
					window.frmPregledProdavac.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProdavacGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPregledProdavac = new JFrame();
		frmPregledProdavac.setTitle("Pregled prodava\u010Da");
		frmPregledProdavac.setBounds(100, 100, 450, 300);
		frmPregledProdavac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPregledProdavac.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(134, 31, 130, 20);
		frmPregledProdavac.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(134, 62, 130, 20);
		frmPregledProdavac.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(134, 93, 130, 20);
		frmPregledProdavac.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(134, 121, 130, 20);
		frmPregledProdavac.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(134, 152, 130, 20);
		frmPregledProdavac.getContentPane().add(textField_4);
		
		JLabel label = new JLabel("Ime i prezime:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(28, 34, 99, 14);
		frmPregledProdavac.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Broj telefona:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 65, 117, 14);
		frmPregledProdavac.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Adresa:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(81, 96, 46, 14);
		frmPregledProdavac.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Email:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(81, 124, 46, 14);
		frmPregledProdavac.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Regija:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(81, 155, 46, 14);
		frmPregledProdavac.getContentPane().add(label_4);
		
		JLabel lblNadleniMenader = new JLabel("Nadle\u017Eni menad\u017Eer:");
		lblNadleniMenader.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNadleniMenader.setBounds(28, 186, 99, 14);
		frmPregledProdavac.getContentPane().add(lblNadleniMenader);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(134, 183, 130, 20);
		frmPregledProdavac.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(335, 227, 89, 23);
		frmPregledProdavac.getContentPane().add(btnOk);
	}

}
