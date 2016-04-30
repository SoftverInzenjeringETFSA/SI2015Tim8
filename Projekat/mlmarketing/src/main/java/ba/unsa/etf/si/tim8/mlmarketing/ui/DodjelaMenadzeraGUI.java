package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DodjelaMenadzeraGUI {

	private JFrame frmDodijeliMenadera;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void startDodjelaMenadzera() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodjelaMenadzeraGUI window = new DodjelaMenadzeraGUI();
					window.frmDodijeliMenadera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodjelaMenadzeraGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodijeliMenadera = new JFrame();
		frmDodijeliMenadera.setTitle("Dodijeli menad\u017Eera");
		frmDodijeliMenadera.setBounds(100, 100, 313, 204);
		frmDodijeliMenadera.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodijeliMenadera.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime prodava\u010Da:");
		lblImeIPrezime.setBounds(12, 57, 154, 14);
		frmDodijeliMenadera.getContentPane().add(lblImeIPrezime);
		
		JLabel lblOdaberiMenadera = new JLabel("Odaberi menad\u017Eera:");
		lblOdaberiMenadera.setBounds(35, 82, 120, 14);
		frmDodijeliMenadera.getContentPane().add(lblOdaberiMenadera);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(166, 54, 120, 20);
		frmDodijeliMenadera.getContentPane().add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(165, 82, 121, 20);
		frmDodijeliMenadera.getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Dodijeli");
		btnNewButton.setBounds(197, 113, 89, 23);
		frmDodijeliMenadera.getContentPane().add(btnNewButton);
	}

}
