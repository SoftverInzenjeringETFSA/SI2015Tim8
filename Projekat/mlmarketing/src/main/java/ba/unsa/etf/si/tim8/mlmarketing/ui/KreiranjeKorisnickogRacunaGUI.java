package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;
import org.jboss.logging.Logger;

import ba.unsa.etf.si.tim8.mlmarketing.models.Korisnik;
import ba.unsa.etf.si.tim8.mlmarketing.services.NaloziServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class KreiranjeKorisnickogRacunaGUI {
	
	final static Logger logger = Logger.getLogger(KreiranjeKorisnickogRacunaGUI.class);
	private Session s;
	private NaloziServis ns;
	private SefProdajeMainGUI refreshableRoditelj;

	private JFrame frmDodajKorisnika;
	private JTextField textFieldKorisnickoIme;
	private JTextField textFieldIme;
	private JTextField textFieldPrezime;
	private JTextField textFieldAdresa;
	private JPasswordField passwordFieldSifra;
	private JPasswordField passwordFieldSifraPonovo;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefon;

	/**
	 * Launch the application.
	 */
	public void startKreiranjeRacuna() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KreiranjeKorisnickogRacunaGUI window = new KreiranjeKorisnickogRacunaGUI(s,refreshableRoditelj);
					window.frmDodajKorisnika.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KreiranjeKorisnickogRacunaGUI(Session s,SefProdajeMainGUI roditelj) {
		this.s=s;
		this.refreshableRoditelj=roditelj;
		ns= new NaloziServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajKorisnika = new JFrame();
		frmDodajKorisnika.setTitle("Dodaj korisnika");
		frmDodajKorisnika.setBounds(100, 100, 360, 450);
		frmDodajKorisnika.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajKorisnika.getContentPane().setLayout(null);
		
		JLabel lblTipRauna = new JLabel("Tip ra\u010Duna:");
		lblTipRauna.setBounds(63, 30, 71, 14);
		frmDodajKorisnika.getContentPane().add(lblTipRauna);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(146, 27, 124, 20);
		comboBox.addItem("Šef prodaje");
		comboBox.addItem("Komercijalista");
		frmDodajKorisnika.getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(30, 102, 104, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Korisni\u010Dka \u0161ifra:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(40, 130, 94, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_1);
		
		textFieldKorisnickoIme = new JTextField();
		textFieldKorisnickoIme.setBounds(146, 99, 124, 20);
		frmDodajKorisnika.getContentPane().add(textFieldKorisnickoIme);
		textFieldKorisnickoIme.setColumns(10);
		
		textFieldIme = new JTextField();
		textFieldIme.setBounds(146, 199, 124, 20);
		frmDodajKorisnika.getContentPane().add(textFieldIme);
		textFieldIme.setColumns(10);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setBounds(146, 230, 124, 20);
		frmDodajKorisnika.getContentPane().add(textFieldPrezime);
		textFieldPrezime.setColumns(10);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setBounds(146, 261, 124, 20);
		frmDodajKorisnika.getContentPane().add(textFieldAdresa);
		textFieldAdresa.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ime:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(88, 202, 46, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Prezime:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(63, 233, 71, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Adresa:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(88, 264, 46, 14);
		frmDodajKorisnika.getContentPane().add(lblNewLabel_4);
		
		JButton btnKreirajNalog = new JButton("Kreiraj");
		btnKreirajNalog.setBounds(181, 354, 89, 23);
		btnKreirajNalog.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(SesijaServis.dajTipKorisnika().equals("sef")){
					
					boolean[] validacije = {false, false, false, false, false, false, false};
										
						Korisnik k = new Korisnik();
						String errorMessage = validirajPolja(validacije);
						if(errorMessage.equals(""))
						{
							if(!ns.zauzetKorisnik(textFieldKorisnickoIme.getText()))
							{
								k.setIme(textFieldIme.getText());					
								k.setPrezime(textFieldPrezime.getText());
								k.setUsername(textFieldKorisnickoIme.getText());
								k.setAdresa(textFieldAdresa.getText());
								if(comboBox.getSelectedItem()=="Komercijalista") k.setTip("komercijalista");
								else k.setTip("sef");
								k.setTelefon(textFieldTelefon.getText());
								k.setEmail(textFieldEmail.getText());
								k.setPassword(passwordFieldSifra.getText());							
								ns.kreirajNalog(k);
								refreshableRoditelj.refreshajTabeluKorisnici();
								frmDodajKorisnika.dispose();
								JOptionPane.showMessageDialog(null, "Novi korisnik uspješno dodan");
							}
							else JOptionPane.showMessageDialog(null, "Korisnik sa usernameom " + textFieldKorisnickoIme.getText() + " već postoji." );
							
						}
						else JOptionPane.showMessageDialog(null, errorMessage);
						
				
					}
					
				
				else {
					JOptionPane.showMessageDialog(null, "Niste logovani sa odgovarajućim privilegijama za ovu akciju.");
					frmDodajKorisnika.dispose();
				}
				
			}
		});
		frmDodajKorisnika.getContentPane().add(btnKreirajNalog);
		
		passwordFieldSifra = new JPasswordField();
		passwordFieldSifra.setBounds(146, 127, 124, 20);
		frmDodajKorisnika.getContentPane().add(passwordFieldSifra);
		
		passwordFieldSifraPonovo = new JPasswordField();
		passwordFieldSifraPonovo.setBounds(146, 158, 124, 20);
		frmDodajKorisnika.getContentPane().add(passwordFieldSifraPonovo);
		
		JLabel lblPotvrdiifru = new JLabel("Potvrdi \u0161ifru:");
		lblPotvrdiifru.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPotvrdiifru.setBounds(30, 160, 104, 14);
		frmDodajKorisnika.getContentPane().add(lblPotvrdiifru);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(146, 292, 124, 20);
		frmDodajKorisnika.getContentPane().add(textFieldEmail);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(63, 295, 71, 14);
		frmDodajKorisnika.getContentPane().add(lblEmail);
		
		JLabel lblTelefon = new JLabel("Telefon:");
		lblTelefon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefon.setBounds(88, 326, 46, 14);
		frmDodajKorisnika.getContentPane().add(lblTelefon);
		
		textFieldTelefon = new JTextField();
		textFieldTelefon.setColumns(10);
		textFieldTelefon.setBounds(146, 323, 124, 20);
		frmDodajKorisnika.getContentPane().add(textFieldTelefon);
	}
	
	private String validirajPolja(boolean[] validacije)
	{
		String[] greske = new String[]{
				"Morate unijeti username od minimalno 3 karaktera (bez razmaka). Pred slova i brojeva može sadržavati . , - i _\n",
				"Passwordi se moraju podudarati, ne mogu biti prazni niti sadržavati razmak!\n",
				"Ime može sadržavati samo slova (bez razmaka).\n",
				"Prezime može sadržavati samo slova (bez razmaka).\n",
				"Adresa ne može biti prazna, niti može sadržavati samo razmak\n",
				"Email mora biti u ispravnom formatu (example@nesto.nesto).\n",
				"Telefon može sadržavati samo brojeve (bez razmaka).\n"
		};
		String returnMessage = "";
		validacije[0] = ValidacijeServis.validirajUsername(textFieldKorisnickoIme.getText());
		validacije[1] = ValidacijeServis.validirajPassword(passwordFieldSifra.getText(), passwordFieldSifraPonovo.getText());
		validacije[2] = ValidacijeServis.validirajIme(textFieldIme.getText());
		validacije[3] = ValidacijeServis.validirajPrezime(textFieldPrezime.getText());
		validacije[4] = ValidacijeServis.validirajAdresu(textFieldAdresa.getText());
		validacije[5] = ValidacijeServis.validirajEmail(textFieldEmail.getText());
		validacije[6] = ValidacijeServis.validirajBrojTelefona(textFieldTelefon.getText());
		for (int i = 0; i < validacije.length; i++)
		{
			if(!validacije[i])
				returnMessage += greske[i];
		}
		return returnMessage;
				
	}
}
