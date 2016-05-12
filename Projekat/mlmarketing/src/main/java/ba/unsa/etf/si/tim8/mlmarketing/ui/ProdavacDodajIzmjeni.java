package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;

public class ProdavacDodajIzmjeni {

	
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
					e.printStackTrace();
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
				
				if(id == -1)
				{
					Akterprodaje a = new Akterprodaje();
					a.setIme(textFieldIme.getText());
					a.setPrezime(textFieldPrezime.getText());
					a.setAdresa(textFieldAdresa.getText());
					a.setBrojtelefona(textFieldBrojTelefona.getText());
					a.setEmail(textFieldEmail.getText());
					a.setRegija((Regija) comboBoxRegije.getSelectedItem());
					a.setAkterprodaje((Akterprodaje)comboBoxNM.getSelectedItem());
					a.setTip("prodavac");
					aks.kreirajAktera(a);
				}
				else
				{
					Akterprodaje ap = aks.dajAktera(id);
					ap.setIme(textFieldIme.getText());
					ap.setPrezime(textFieldPrezime.getText());
					ap.setAdresa(textFieldAdresa.getText());
					ap.setBrojtelefona(textFieldBrojTelefona.getText());
					ap.setEmail(textFieldEmail.getText());
					ap.setRegija((Regija) comboBoxRegije.getSelectedItem());
					ap.setAkterprodaje((Akterprodaje)comboBoxNM.getSelectedItem());
					aks.izmjeniAktera(ap);
				}
				
				
				refreshableRoditelj.refreshajTabeluProdavaci();
				
				
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
}
