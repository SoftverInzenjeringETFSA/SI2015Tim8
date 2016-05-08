package ba.unsa.etf.si.tim8.mlmarketing.ui;

import ba.unsa.etf.si.tim8.mlmarketing.models.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;
import org.hibernate.Transaction;



public class LoginGUI {

	private JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void startLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Prijava na sistem");
		frmLogin.setBounds(100, 100, 343, 249);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Korisni\u010Dko ime:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(37, 60, 105, 14);
		frmLogin.getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(164, 57, 133, 20);
		frmLogin.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Korisni\u010Dka \u0161ifra:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(37, 91, 105, 14);
		frmLogin.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(164, 88, 133, 20);
		frmLogin.getContentPane().add(passwordField);
		
		JButton btnPrijavi = new JButton("Prijava");
		btnPrijavi.setBounds(192, 149, 105, 23);
		frmLogin.getContentPane().add(btnPrijavi);
		btnPrijavi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(txtUsername.getText().equals("komercijalista")){
					KomercijalistaMainGUI komercijalistamg = new KomercijalistaMainGUI();
					komercijalistamg.startKomercijalistaMain();
				}
				else{
					SefProdajeMainGUI sefmg= new SefProdajeMainGUI();
					sefmg.startSefProdajeMain();
				}
			}
		});
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t=session.beginTransaction();
		Korisnik k = (Korisnik) session.get(Korisnik.class, 1);
		JOptionPane.showMessageDialog(null, k.getIme());
		t.commit();
	}

}
