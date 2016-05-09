package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;

public class DodavanjeRegijeGUI {
	
	Session s;

	private JFrame frmDodavanjeRegije;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public void startDodavanjeRegije() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeRegijeGUI window = new DodavanjeRegijeGUI(s);
					window.frmDodavanjeRegije.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeRegijeGUI(Session s) {
		this.s=s;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodavanjeRegije = new JFrame();
		frmDodavanjeRegije.getContentPane().setBackground(Color.WHITE);
		frmDodavanjeRegije.setTitle("Dodavanje regije");
		frmDodavanjeRegije.setBounds(100, 100, 362, 275);
		frmDodavanjeRegije.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodavanjeRegije.getContentPane().setLayout(null);
		
		JLabel lblRegija = new JLabel("Regija:");
		lblRegija.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegija.setBounds(58, 67, 56, 16);
		frmDodavanjeRegije.getContentPane().add(lblRegija);
		
		JLabel lblNewLabel = new JLabel("Dr\u017Eava:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(58, 125, 56, 16);
		frmDodavanjeRegije.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(147, 64, 116, 22);
		frmDodavanjeRegije.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 122, 116, 22);
		frmDodavanjeRegije.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBounds(147, 170, 116, 25);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("") && !textField_1.getText().equals("")){
					JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");
					RegijaServis r= new RegijaServis(s);
					Regija nova = new Regija();
					nova.setIme(textField.getText());
					nova.setDrzava(textField_1.getText());
					JOptionPane.showMessageDialog(null, nova.getDrzava());
					r.dodajRegiju(nova);
				}
				
			}
		});
		frmDodavanjeRegije.getContentPane().add(btnNewButton);
	}

}
