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

public class RegMenadzerDodajIzmjeniGUI {
	
	final static Logger logger = Logger.getLogger(RegMenadzerDodajIzmjeniGUI.class);
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
	public void pokreniRegMenDodajIzmjeni(final String what) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegMenadzerDodajIzmjeniGUI window;
					if(id==-1)window = new RegMenadzerDodajIzmjeniGUI(what,s,refreshableRoditelj);
					else window = new RegMenadzerDodajIzmjeniGUI(what,s,refreshableRoditelj,id);
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
	public RegMenadzerDodajIzmjeniGUI(String what,Session s,SefProdajeMainGUI roditelj) {
		this.s=s;
		this.rs= new RegijaServis(s);
		this.aks=new AkterServis(s);
		this.refreshableRoditelj=roditelj;
		initialize(what);
		
	}
	
	public RegMenadzerDodajIzmjeniGUI(String what,Session s,SefProdajeMainGUI roditelj, int id) {
		this.s=s;
		this.rs= new RegijaServis(s);
		this.aks=new AkterServis(s);
		this.refreshableRoditelj=roditelj;
		this.id=id;
		initialize(what);
		Akterprodaje trenutni = aks.dajAktera(id);
		textFieldIme.setText(trenutni.getIme());
		textFieldPrezime.setText(trenutni.getPrezime());
		textFieldAdresa.setText(trenutni.getAdresa());
		textFieldBrojTelefona.setText(trenutni.getBrojtelefona());
		textFieldEmail.setText(trenutni.getEmail());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String what) {
		frmDodajizmijeni = new JFrame();
		if(what=="dodaj")frmDodajizmijeni.setTitle("Dodaj");
		else frmDodajizmijeni.setTitle("Izmijeni");
		frmDodajizmijeni.setBounds(100, 100, 321, 274);
		frmDodajizmijeni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajizmijeni.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ime:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(46, 33, 84, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Broj telefona:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(35, 85, 95, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Adresa:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(56, 110, 74, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(56, 135, 74, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Regija:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(46, 160, 84, 14);
		frmDodajizmijeni.getContentPane().add(lblNewLabel_4);
		
		textFieldIme = new JTextField();
		textFieldIme.setBounds(140, 30, 129, 20);
		frmDodajizmijeni.getContentPane().add(textFieldIme);
		textFieldIme.setColumns(10);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setBounds(140, 82, 129, 20);
		frmDodajizmijeni.getContentPane().add(textFieldBrojTelefona);
		textFieldBrojTelefona.setColumns(10);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setBounds(140, 107, 129, 20);
		frmDodajizmijeni.getContentPane().add(textFieldAdresa);
		textFieldAdresa.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(140, 132, 129, 20);
		frmDodajizmijeni.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		final JComboBox comboBox = new JComboBox(new DefaultComboBoxModel(rs.dajRegije().toArray()));
		comboBox.setBounds(140, 157, 129, 20);
		frmDodajizmijeni.getContentPane().add(comboBox);
		if(id!=-1){
			int regijaid=aks.dajAktera(id).getRegija().getId();
			for(int i = 0; i < comboBox.getItemCount();i++){
				if(((Regija)comboBox.getModel().getElementAt(i)).getId()==regijaid){
					comboBox.setSelectedIndex(i);
					break;
				}
			}
		}
		JButton btnDodajIzmjeni;
		if(what=="dodaj") btnDodajIzmjeni = new JButton("Dodaj");
		else btnDodajIzmjeni = new JButton("Izmijeni");
		btnDodajIzmjeni.setBounds(180, 188, 89, 23);
		btnDodajIzmjeni.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(SesijaServis.dajTipKorisnika().equals("sef")){
						if(id==-1){
							Akterprodaje a = new Akterprodaje();
							a.setIme(textFieldIme.getText());
							a.setPrezime(textFieldPrezime.getText());
							a.setAdresa(textFieldAdresa.getText());
							a.setBrojtelefona(textFieldBrojTelefona.getText());
							a.setEmail(textFieldEmail.getText());
							a.setTip("regmen");
							a.setRegija((Regija)comboBox.getSelectedItem());
							aks.kreirajAktera(a);
						}
						else{
							int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
							if(rez == JOptionPane.YES_OPTION){
							Akterprodaje a = aks.dajAktera(id);
							a.setIme(textFieldIme.getText());
							a.setPrezime(textFieldPrezime.getText());
							a.setAdresa(textFieldAdresa.getText());
							a.setBrojtelefona(textFieldBrojTelefona.getText());
							a.setEmail(textFieldEmail.getText());
							a.setTip("regmen");
							a.setRegija((Regija)comboBox.getSelectedItem());
							aks.izmjeniAktera(a);
							}
						}
						refreshableRoditelj.refreshajTabeluMenadzeri();
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste logovani sa odgovarajućim privilegijama za ovu akciju.");
					frmDodajizmijeni.dispose();
				}
				
			}
		});
		frmDodajizmijeni.getContentPane().add(btnDodajIzmjeni);
		
		
		textFieldPrezime = new JTextField();
		textFieldPrezime.setBounds(140, 58, 129, 20);
		frmDodajizmijeni.getContentPane().add(textFieldPrezime);
		textFieldPrezime.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrezime.setBounds(84, 60, 46, 14);
		frmDodajizmijeni.getContentPane().add(lblPrezime);
		
	}
}
