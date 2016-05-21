package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class ProdavacDodajIzmjeni {

	final static Logger logger = Logger.getLogger(ProdavacDodajIzmjeni.class);
	private Session s;
	private RegijaServis rs;
	private AkterServis aks;
	private int id=-1;
	private SefProdajeMainGUI refreshableRoditelj;
	
	
	private JFrame frmDodajizmijeni;
	private JTextField textFieldIme;
	private JTextField textFieldBrojTelefona;
	private JTextField textFieldAdresa;
	private JTextField textFieldEmail;
	private JTextField textFieldPrezime;

	/**
	 * Launch the application.
	 */
	public void startProdavacDodajIzmjeni(final String what) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProdavacDodajIzmjeni window;
					if(id == -1)
						window = new ProdavacDodajIzmjeni(what, s,refreshableRoditelj);
					else 
						window = new ProdavacDodajIzmjeni(what, s, refreshableRoditelj, id);
					window.frmDodajizmijeni.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProdavacDodajIzmjeni(String what, Session s, SefProdajeMainGUI roditelj) {
		this.s = s;
		this.rs = new RegijaServis(s);
		this.aks = new AkterServis(s);
		this.refreshableRoditelj = roditelj;
		initialize(what);
	}
	
	public ProdavacDodajIzmjeni(String what, Session s, SefProdajeMainGUI roditelj, int id)
	{
		this.s = s;
		this.rs = new RegijaServis(s);
		this.aks = new AkterServis(s);
		this.refreshableRoditelj = roditelj;
		this.id = id;
		initialize(what);
		Akterprodaje ap = aks.dajAktera(id);
		textFieldIme.setText(ap.getIme());
		textFieldPrezime.setText(ap.getPrezime());
		textFieldEmail.setText(ap.getEmail());
		textFieldBrojTelefona.setText(ap.getBrojtelefona());
		textFieldAdresa.setText(ap.getAdresa());
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String what) {
		
		frmDodajizmijeni = new JFrame();
		if(what=="dodaj") frmDodajizmijeni.setTitle("Dodaj");
		else frmDodajizmijeni.setTitle("Izmijeni");
		frmDodajizmijeni.setBounds(100, 100, 305, 323);
		frmDodajizmijeni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajizmijeni.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Prezime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(37, 49, 102, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Broj telefona:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(37, 74, 102, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adresa:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(93, 99, 46, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(93, 124, 46, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Regija:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(93, 157, 46, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Nadle\u017Eni menad\u017Eer:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_5.setBounds(12, 182, 127, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_5);
		
		final JComboBox comboBoxRegije = new JComboBox(new DefaultComboBoxModel(rs.dajRegije().toArray()));
		comboBoxRegije.setBounds(149, 154, 94, 20);
		frmDodajizmijeni.getContentPane().add(comboBoxRegije);
		
		final JComboBox comboBoxNM = new JComboBox(new DefaultComboBoxModel(aks.dajSveAkterePoTipu("regmen").toArray()));
		comboBoxNM.setBounds(149, 179, 94, 20);
		frmDodajizmijeni.getContentPane().add(comboBoxNM);
		
		if(id!=-1){
			
			int regijaid=aks.dajAktera(id).getRegija().getId();
			for(int i = 0; i < comboBoxRegije.getItemCount();i++){
				if(((Regija)comboBoxRegije.getModel().getElementAt(i)).getId()==regijaid){
					comboBoxRegije.setSelectedIndex(i);
					break;
				}
			}
			int menadzerid=aks.dajAktera(id).getAkterprodaje().getId();
			for(int i = 0; i < comboBoxNM.getItemCount();i++){
				if(((Akterprodaje)comboBoxNM.getModel().getElementAt(i)).getId()==menadzerid){
					comboBoxNM.setSelectedIndex(i);
					break;
				}
			}
		}
		
		textFieldIme = new JTextField();
		textFieldIme.setBounds(149, 22, 94, 20);
		frmDodajizmijeni.getContentPane().add(textFieldIme);
		textFieldIme.setColumns(10);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setBounds(149, 71, 94, 20);
		frmDodajizmijeni.getContentPane().add(textFieldBrojTelefona);
		textFieldBrojTelefona.setColumns(10);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setBounds(150, 96, 93, 20);
		frmDodajizmijeni.getContentPane().add(textFieldAdresa);
		textFieldAdresa.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(150, 121, 94, 20);
		frmDodajizmijeni.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnDodajIzmjeni;
		if(what=="dodaj") btnDodajIzmjeni = new JButton("Dodaj");
		else btnDodajIzmjeni = new JButton("Izmijeni");
		
		
		
		btnDodajIzmjeni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(SesijaServis.dajTipKorisnika().equals("sef")){
					
						
						boolean[] validacije = {false, false, false, false, false};
						String errorMessage = validirajPolja(validacije);
						if(errorMessage.equals(""))
						{
							
							if(id == -1)
							{
								Akterprodaje a = new Akterprodaje();
								Akterprodaje regmen = (Akterprodaje)comboBoxNM.getSelectedItem();
								if(!aks.daLiJeNadlezanZaMaxBroj(regmen))
								{
									a.setIme(textFieldIme.getText());
									a.setPrezime(textFieldPrezime.getText());
									a.setAdresa(textFieldAdresa.getText());
									a.setBrojtelefona(textFieldBrojTelefona.getText());
									a.setEmail(textFieldEmail.getText());
									a.setRegija((Regija) comboBoxRegije.getSelectedItem());
									a.setAkterprodaje(regmen);									
									a.setTip("prodavac");
									aks.kreirajAktera(a);
									JOptionPane.showMessageDialog(null, "Prodavač uspješno kreiran!");
									frmDodajizmijeni.dispose();
								}
								else JOptionPane.showMessageDialog(null, "Regionalni menadžer " + regmen.toString() + " je već nadležan za maksimalni broj prodavača.");
								
							}
							else
							{
								Akterprodaje ap = aks.dajAktera(id); 	
								if(ap != null)
								{
									Akterprodaje regmen = (Akterprodaje)comboBoxNM.getSelectedItem();
									if(!aks.daLiJeNadlezanZaMaxBroj(regmen))
									{
										int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
										if(rez == JOptionPane.YES_OPTION){
																				
										ap.setIme(textFieldIme.getText());
										ap.setPrezime(textFieldPrezime.getText());
										ap.setAdresa(textFieldAdresa.getText());
										ap.setBrojtelefona(textFieldBrojTelefona.getText());
										ap.setEmail(textFieldEmail.getText());
										ap.setRegija((Regija) comboBoxRegije.getSelectedItem());
										ap.setAkterprodaje(regmen);
										aks.izmjeniAktera(ap);
										JOptionPane.showMessageDialog(null, "Prodavač uspješno izmijenjen!");
										frmDodajizmijeni.dispose();
										}
									}
									else JOptionPane.showMessageDialog(null, "Regionalni menadžer " + regmen.toString() + " je već nadležan za maksimalni broj prodavača.");
								}
								else JOptionPane.showMessageDialog(null, "Odabrani akter u međuvremenu obrisan.");
								
									
															
								
							}							
							
							refreshableRoditelj.refreshajTabeluProdavaci();
							refreshableRoditelj.refreshajTabeluMenadzeri();
						}
						else JOptionPane.showMessageDialog(null, errorMessage);
						
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste logovani sa odgovarajućim privilegijama za ovu akciju.");
					frmDodajizmijeni.dispose();
				}
							
				
			}
		});
		btnDodajIzmjeni.setBounds(149, 224, 94, 23);
		frmDodajizmijeni.getContentPane().add(btnDodajIzmjeni);
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setBounds(149, 46, 94, 20);
		frmDodajizmijeni.getContentPane().add(textFieldPrezime);
		textFieldPrezime.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Ime:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setBounds(93, 25, 46, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_6);
	}
	
	private String validirajPolja(boolean[] validacija)
	{
		String errorMessage = "";
		String[] greske = new String[]{
				"Ime može sadržavati samo slova i minimalna dužina je 3 (razmaci nisu dozvoljeni).\n",
				"Prezime može sadržavati samo slova i minimalna dužina je 3 (razmaci nisu dozvoljeni).\n",
				"Telefon može sadržavati samo brojeve, minimalna dužina je 6 (razmaci nisu dozvoljeni).\n",
				"Adresa ne može biti prazna, niti može sadržavati samo razmak\n",
				"Email mora biti u ispravnom formatu (example@nesto.nesto).\n"
				
		};
		validacija[0] = ValidacijeServis.validirajIme(textFieldIme.getText());
		validacija[1] = ValidacijeServis.validirajPrezime(textFieldPrezime.getText());
		validacija[2] = ValidacijeServis.validirajBrojTelefona(textFieldBrojTelefona.getText());
		validacija[3] = ValidacijeServis.validirajAdresu(textFieldAdresa.getText());
		validacija[4] = ValidacijeServis.validirajEmail(textFieldEmail.getText());
		
		for(int i = 0; i < validacija.length; i++)
		{
			if(!validacija[i])
			{
				errorMessage += greske[i];
			}
		}
		return errorMessage;
	}
}
