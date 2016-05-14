package ba.unsa.etf.si.tim8.mlmarketing.ui;

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

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;



public class LoginGUI {

	final static Logger logger = Logger.getLogger(LoginGUI.class);
	private JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	private Session s;
	public LoginGUI ref;

	/**
	 * Launch the application.
	 */
	public void startLogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI(s);
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}
	
	private void startUpCheck(){
		AkterServis aks = new AkterServis(s);
		Akterprodaje a =aks.pronadjiProdavacaBezMenadzera();
		if(a!= null){
			a.setTip("regmen");
			aks.izmjeniAktera(a);
			JOptionPane.showMessageDialog(null, "Posto prilikom promjene statusa aktera"+a.toString()+" "
					+ "nije odabran nadlezni menadzer, status mu se privremeno vraca u prethodno stanje.");
		}
	}

	/**
	 * Create the application.
	 */
	public LoginGUI(Session s) {
		this.s=s;
		this.ref=this;
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
					if(SesijaServis.prijava(txtUsername.getText(),passwordField.getText())){
						startUpCheck();
						if(SesijaServis.dajTipKorisnika().equals("sef")){
							SefProdajeMainGUI sefmg= new SefProdajeMainGUI(s,ref);
							sefmg.startSefProdajeMain();}
						else if(SesijaServis.dajTipKorisnika().equals("komercijalista")){
							KomercijalistaMainGUI komercijalistamg = new KomercijalistaMainGUI(s, ref);
							komercijalistamg.startKomercijalistaMain();
						}
						frmLogin.hide();
					
				}
			}
		});
		
	}
	
	public void otkrij(){
		frmLogin.show();
	}
	
}
