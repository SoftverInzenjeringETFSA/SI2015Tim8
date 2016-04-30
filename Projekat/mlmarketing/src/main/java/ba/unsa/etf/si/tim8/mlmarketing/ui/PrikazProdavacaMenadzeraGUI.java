package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PrikazProdavacaMenadzeraGUI {

	private JFrame frmProdavai;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void startPrikazProdavaca() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProdavacaMenadzeraGUI window = new PrikazProdavacaMenadzeraGUI();
					window.frmProdavai.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProdavacaMenadzeraGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProdavai = new JFrame();
		frmProdavai.setTitle("Prodava\u010Di");
		frmProdavai.getContentPane().setBackground(Color.WHITE);
		frmProdavai.setBounds(100, 100, 476, 399);
		frmProdavai.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdavai.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 434, 254);
		frmProdavai.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime", "Adresa", "Broj telefona", "Email", "Regija"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(109);
		table.getColumnModel().getColumn(1).setPreferredWidth(98);
		table.getColumnModel().getColumn(2).setPreferredWidth(115);
		table.getColumnModel().getColumn(3).setPreferredWidth(104);
		table.getColumnModel().getColumn(4).setPreferredWidth(99);
		scrollPane.setViewportView(table);
	}

}
