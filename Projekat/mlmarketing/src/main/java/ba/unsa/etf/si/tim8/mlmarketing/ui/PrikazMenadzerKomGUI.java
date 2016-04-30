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

public class PrikazMenadzerKomGUI {

	private JFrame frmRegionalniMenader;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void startPrikazMenadzer() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazMenadzerKomGUI window = new PrikazMenadzerKomGUI();
					window.frmRegionalniMenader.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazMenadzerKomGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegionalniMenader = new JFrame();
		frmRegionalniMenader.setTitle("Regionalni menad\u017Eer");
		frmRegionalniMenader.getContentPane().setBackground(Color.WHITE);
		frmRegionalniMenader.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImeIPrezime.setBounds(74, 45, 93, 16);
		frmRegionalniMenader.getContentPane().add(lblImeIPrezime);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresa.setBounds(111, 81, 56, 16);
		frmRegionalniMenader.getContentPane().add(lblAdresa);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojTelefona.setBounds(74, 120, 93, 16);
		frmRegionalniMenader.getContentPane().add(lblBrojTelefona);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(111, 160, 56, 16);
		frmRegionalniMenader.getContentPane().add(lblEmail);
		
		JLabel lblRegija = new JLabel("Regija:");
		lblRegija.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegija.setBounds(111, 196, 56, 16);
		frmRegionalniMenader.getContentPane().add(lblRegija);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(179, 42, 116, 22);
		frmRegionalniMenader.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(179, 78, 116, 22);
		frmRegionalniMenader.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(179, 117, 116, 22);
		frmRegionalniMenader.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(179, 157, 116, 22);
		frmRegionalniMenader.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(179, 193, 116, 22);
		frmRegionalniMenader.getContentPane().add(textField_4);
		
		JButton btnNewButton = new JButton("Prika\u017Ei narud\u017Ebe");
		btnNewButton.setBounds(126, 243, 169, 25);
		frmRegionalniMenader.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Prika\u017Ei fakture");
		btnNewButton_1.setBounds(126, 281, 169, 25);
		frmRegionalniMenader.getContentPane().add(btnNewButton_1);
		
		JButton btnPrikaziProdavace = new JButton("Prika\u017Ei prodava\u010De");
		btnPrikaziProdavace.setBounds(126, 319, 169, 25);
		frmRegionalniMenader.getContentPane().add(btnPrikaziProdavace);
		btnPrikaziProdavace.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PrikazProdavacaMenadzeraGUI prikazprodavaca=new PrikazProdavacaMenadzeraGUI();
				prikazprodavaca.startPrikazProdavaca();
				
			}
		});
		frmRegionalniMenader.setBounds(100, 100, 406, 425);
		frmRegionalniMenader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
