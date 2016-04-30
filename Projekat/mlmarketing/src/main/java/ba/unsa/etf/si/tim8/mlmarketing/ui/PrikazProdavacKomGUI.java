package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PrikazProdavacKomGUI {

	private JFrame frmProdava;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void startPrikazProdavaca() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProdavacKomGUI window = new PrikazProdavacKomGUI();
					window.frmProdava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProdavacKomGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProdava = new JFrame();
		frmProdava.setTitle("Prodava\u010D");
		frmProdava.getContentPane().setBackground(Color.WHITE);
		frmProdava.setBounds(100, 100, 406, 425);
		frmProdava.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdava.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ime i prezime:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(83, 27, 93, 16);
		frmProdava.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(188, 24, 116, 22);
		frmProdava.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(188, 60, 116, 22);
		frmProdava.getContentPane().add(textField_1);
		
		JLabel label_1 = new JLabel("Adresa:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(120, 63, 56, 16);
		frmProdava.getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(188, 99, 116, 22);
		frmProdava.getContentPane().add(textField_2);
		
		JLabel label_2 = new JLabel("Broj telefona:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(83, 102, 93, 16);
		frmProdava.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Email:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(120, 142, 56, 16);
		frmProdava.getContentPane().add(label_3);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(188, 139, 116, 22);
		frmProdava.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(188, 175, 116, 22);
		frmProdava.getContentPane().add(textField_4);
		
		JLabel label_4 = new JLabel("Regija:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(120, 178, 56, 16);
		frmProdava.getContentPane().add(label_4);
		
		JButton button = new JButton("Prika\u017Ei narud\u017Ebe");
		button.setBounds(135, 268, 169, 25);
		frmProdava.getContentPane().add(button);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

			}
		});
		
		JButton button_1 = new JButton("Prika\u017Ei fakture");
		button_1.setBounds(135, 306, 169, 25);
		frmProdava.getContentPane().add(button_1);
		
		JLabel lblNadleniMenader = new JLabel("Nadle\u017Eni menad\u017Eer:");
		lblNadleniMenader.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNadleniMenader.setBounds(60, 213, 116, 16);
		frmProdava.getContentPane().add(lblNadleniMenader);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(188, 210, 116, 22);
		frmProdava.getContentPane().add(textField_5);
	}

}
