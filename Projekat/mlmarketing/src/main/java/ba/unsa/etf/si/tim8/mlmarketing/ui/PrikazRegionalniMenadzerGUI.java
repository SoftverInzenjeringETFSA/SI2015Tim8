package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PrikazRegionalniMenadzerGUI {

	private JFrame frmPregledMenadera;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JLabel lblNadleanZa;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public static void startPrikazMenadzer() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazRegionalniMenadzerGUI window = new PrikazRegionalniMenadzerGUI();
					window.frmPregledMenadera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazRegionalniMenadzerGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPregledMenadera = new JFrame();
		frmPregledMenadera.setTitle("Pregled menad\u017Eera");
		frmPregledMenadera.setBounds(100, 100, 665, 399);
		frmPregledMenadera.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPregledMenadera.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImeIPrezime.setBounds(28, 40, 99, 14);
		frmPregledMenadera.getContentPane().add(lblImeIPrezime);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojTelefona.setBounds(10, 71, 117, 14);
		frmPregledMenadera.getContentPane().add(lblBrojTelefona);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresa.setBounds(81, 102, 46, 14);
		frmPregledMenadera.getContentPane().add(lblAdresa);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(81, 130, 46, 14);
		frmPregledMenadera.getContentPane().add(lblEmail);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(134, 37, 130, 20);
		frmPregledMenadera.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(134, 68, 130, 20);
		frmPregledMenadera.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(134, 99, 130, 20);
		frmPregledMenadera.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(134, 127, 130, 20);
		frmPregledMenadera.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Regija:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(81, 161, 46, 14);
		frmPregledMenadera.getContentPane().add(lblNewLabel);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(134, 158, 130, 20);
		frmPregledMenadera.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 55, 365, 125);
		frmPregledMenadera.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime",
				"Broj telefona",
				"Adresa",
				"Email",
				"Regija"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNadleanZa = new JLabel("Nadle\u017Ean za:");
		lblNadleanZa.setBounds(274, 40, 99, 14);
		frmPregledMenadera.getContentPane().add(lblNadleanZa);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(550, 326, 89, 23);
		frmPregledMenadera.getContentPane().add(btnOk);
	}

}
