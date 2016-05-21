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
import org.jboss.logging.Logger;

import ba.unsa.etf.si.tim8.mlmarketing.models.Regija;
import ba.unsa.etf.si.tim8.mlmarketing.services.RegijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class DodavanjeRegijeGUI {
	
	final static Logger logger = Logger.getLogger(DodavanjeRegijeGUI.class);
	Session s;
	SefProdajeMainGUI refreshableRoditelj;
	private JFrame frmDodavanjeRegije;
	private JTextField textFieldNazivRegije;
	private JTextField textFieldDrzava;
	

	/**
	 * Launch the application.
	 */
	public void startDodavanjeRegije() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeRegijeGUI window = new DodavanjeRegijeGUI(s,refreshableRoditelj);
					window.frmDodavanjeRegije.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeRegijeGUI(Session s,SefProdajeMainGUI roditelj) {
		this.s=s;
		this.refreshableRoditelj=roditelj;
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
		
		textFieldNazivRegije = new JTextField();
		textFieldNazivRegije.setBounds(147, 64, 116, 22);
		frmDodavanjeRegije.getContentPane().add(textFieldNazivRegije);
		textFieldNazivRegije.setColumns(10);
		
		textFieldDrzava = new JTextField();
		textFieldDrzava.setBounds(147, 122, 116, 22);
		frmDodavanjeRegije.getContentPane().add(textFieldDrzava);
		textFieldDrzava.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBounds(147, 170, 116, 25);
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(SesijaServis.dajTipKorisnika().equals("sef")){
					
					
						boolean[] validacije = {false, false};
						String errorMessage = validirajPolja(validacije);
						if(errorMessage.equals(""))
						{
							RegijaServis r= new RegijaServis(s);
							if(!r.daLiPostojiRegija(textFieldNazivRegije.getText()))
							{
								Regija nova = new Regija();
								nova.setIme(textFieldNazivRegije.getText().trim());
								nova.setDrzava(textFieldDrzava.getText().trim());							
								r.dodajRegiju(nova);
								
								refreshableRoditelj.refreshajTabeluRegije();
								JOptionPane.showMessageDialog(null, "Regija uspješno dodana!");
								frmDodavanjeRegije.dispose();
								
							}
							else JOptionPane.showMessageDialog(null, "Regija sa nazivom " + textFieldNazivRegije.getText() + " već postoji.");
							
							
						}
						else JOptionPane.showMessageDialog(null, errorMessage);
						
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste logovani sa odgovarajućim privilegijama za ovu akciju.");
					frmDodavanjeRegije.dispose();
				}
				
				
			}
		});
		frmDodavanjeRegije.getContentPane().add(btnNewButton);
	}
	
	private String validirajPolja(boolean[] validacije)
	{
		String errorMessage = "";
		String[] greske = new String[]{
				"Naziv regije ne može ostati nepopunjen. Može sadržavati slova i razmake.\n",
				"Država ne može ostati nepopunjena. Može sadržavati slova i razmake.\n"
		};
		validacije[0] = ValidacijeServis.validirajRegiju(textFieldNazivRegije.getText());
		validacije[1] = ValidacijeServis.validirajRegiju(textFieldDrzava.getText());
		for(int i = 0; i < validacije.length; i++)
		{
			if(!validacije[i])
				errorMessage += greske[i];
		}
		return errorMessage;
	}
}
