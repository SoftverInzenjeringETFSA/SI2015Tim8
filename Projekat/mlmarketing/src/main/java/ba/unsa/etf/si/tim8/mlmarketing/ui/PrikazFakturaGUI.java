package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class PrikazFakturaGUI {

	private JFrame frmFaktura;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void startPrikazFakture() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazFakturaGUI window = new PrikazFakturaGUI();
					window.frmFaktura.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazFakturaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFaktura = new JFrame();
		frmFaktura.setTitle("Faktura");
		frmFaktura.getContentPane().setBackground(Color.WHITE);
		frmFaktura.setBounds(100, 100, 450, 469);
		frmFaktura.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFaktura.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u0160ifra:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(104, 39, 56, 16);
		frmFaktura.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Datum:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(104, 78, 56, 16);
		frmFaktura.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Naru\u010Dilac:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(60, 122, 100, 16);
		frmFaktura.getContentPane().add(label_2);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(192, 36, 145, 22);
		frmFaktura.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(192, 75, 145, 22);
		frmFaktura.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(192, 119, 145, 22);
		frmFaktura.getContentPane().add(textField_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 158, 233, 149);
		frmFaktura.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Proizvod", "Koli\u010Dina", "Cijena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(94);
		table.getColumnModel().getColumn(1).setPreferredWidth(106);
		table.getColumnModel().getColumn(2).setPreferredWidth(106);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Ukupna cijena:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(60, 340, 100, 16);
		frmFaktura.getContentPane().add(lblNewLabel);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(192, 337, 145, 22);
		frmFaktura.getContentPane().add(textField_3);
	}

}
