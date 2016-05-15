package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.swing.JButton;

public class IzvjestajPrikazForme {

	private JFrame frmPrikazIzvjetaja;
	private JTable table;
	private MyTableModel m;
	private Session s;
	final static Logger logger = Logger.getLogger(IzvjestajPrikazForme.class);

	/**
	 * Launch the application.
	 */
	public  void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzvjestajPrikazForme window = new IzvjestajPrikazForme(m);
					window.frmPrikazIzvjetaja.setVisible(true);
				} catch (Exception e) {
					logger.error(e);;
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzvjestajPrikazForme(MyTableModel m) {
		initialize();
		this.m = m;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazIzvjetaja = new JFrame();
		frmPrikazIzvjetaja.setTitle("Prikaz izvještaja");
		frmPrikazIzvjetaja.setBounds(100, 100, 450, 300);
		frmPrikazIzvjetaja.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrikazIzvjetaja.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 188);
		frmPrikazIzvjetaja.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSacuvaj = new JButton("Sačuvaj");
		btnSacuvaj.setBounds(335, 227, 89, 23);
		frmPrikazIzvjetaja.getContentPane().add(btnSacuvaj);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(236, 227, 89, 23);
		frmPrikazIzvjetaja.getContentPane().add(btnOk);
	}
}
