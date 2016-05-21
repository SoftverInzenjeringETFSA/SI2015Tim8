package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.services.FajlUpisServis;

public class IzvjestajPrikazForme {

	private JFrame frmPrikazIzvjetaja;
	private JTable table;
	private MyTableModel m;
	private Session s;
	final static Logger logger = Logger.getLogger(IzvjestajPrikazForme.class);

	/**
	 * Launch the application.
	 */
	public void main() {
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
		table.setModel(m);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrikazIzvjetaja = new JFrame();
		frmPrikazIzvjetaja.setTitle("Prikaz izvještaja");
		frmPrikazIzvjetaja.setBounds(100, 100, 450, 300);
		frmPrikazIzvjetaja.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrikazIzvjetaja.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 188);
		frmPrikazIzvjetaja.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSacuvaj = new JButton("Sačuvaj");
		btnSacuvaj.setBounds(335, 227, 89, 23);
		btnSacuvaj.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Date sad = new Date();
				String stamp = sad.toString();
				stamp=stamp.replaceAll(" ", "");
				stamp=stamp.replaceAll(":", "");
				File f = new File("izvjestaj"+stamp+".xls");
				  try{
				    FajlUpisServis.exportTable(table.getModel(), f);
				    JOptionPane.showMessageDialog(null,"Izvjestaj je uspjesno sacuvan.");
				  }
				  catch(Exception e)
				  {
				    JOptionPane.showMessageDialog(null,"Doslo je do greske, izvjestaj nije sacuvan.");
				    logger.info(e);
				  }
				}
			
		});
		frmPrikazIzvjetaja.getContentPane().add(btnSacuvaj);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPrikazIzvjetaja.dispose();
			}
		});
		btnOk.setBounds(236, 227, 89, 23);
		frmPrikazIzvjetaja.getContentPane().add(btnOk);
	}
}
