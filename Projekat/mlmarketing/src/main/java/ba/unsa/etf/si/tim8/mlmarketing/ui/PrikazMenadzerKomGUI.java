package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;

public class PrikazMenadzerKomGUI {
	
	private Session s;
	private AkterServis aks;
	private int id;

	private JFrame frmRegionalniMenader;
	private JTextField textFieldImePrezime;
	private JTextField textFieldBrojTelefona;
	private JTextField textFieldAdresa;
	private JTextField textFieldEmail;
	private JTextField textFieldRegija;

	/**
	 * Launch the application.
	 */
	public void startPrikazMenadzer() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazMenadzerKomGUI window = new PrikazMenadzerKomGUI(s, id);
					window.frmRegionalniMenader.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazMenadzerKomGUI(Session s, int id) 
	{
		this.s = s;
		this.id = id;
		aks = new AkterServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegionalniMenader = new JFrame();
		frmRegionalniMenader.setTitle("Regionalni menad\u017Eer");
		frmRegionalniMenader.getContentPane().setBackground(Color.WHITE);
		frmRegionalniMenader.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImeIPrezime.setBounds(74, 45, 93, 16);
		frmRegionalniMenader.getContentPane().add(lblImeIPrezime);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresa.setBounds(111, 81, 56, 16);
		frmRegionalniMenader.getContentPane().add(lblAdresa);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojTelefona.setBounds(74, 120, 93, 16);
		frmRegionalniMenader.getContentPane().add(lblBrojTelefona);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(111, 160, 56, 16);
		frmRegionalniMenader.getContentPane().add(lblEmail);
		
		JLabel lblRegija = new JLabel("Regija:");
		lblRegija.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegija.setBounds(111, 196, 56, 16);
		frmRegionalniMenader.getContentPane().add(lblRegija);
		
		textFieldImePrezime = new JTextField();
		textFieldImePrezime.setEditable(false);
		textFieldImePrezime.setBounds(179, 42, 116, 22);
		frmRegionalniMenader.getContentPane().add(textFieldImePrezime);
		textFieldImePrezime.setColumns(10);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setEditable(false);
		textFieldAdresa.setColumns(10);
		textFieldAdresa.setBounds(179, 78, 116, 22);
		frmRegionalniMenader.getContentPane().add(textFieldAdresa);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setEditable(false);
		textFieldBrojTelefona.setColumns(10);
		textFieldBrojTelefona.setBounds(179, 117, 116, 22);
		frmRegionalniMenader.getContentPane().add(textFieldBrojTelefona);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(179, 157, 116, 22);
		frmRegionalniMenader.getContentPane().add(textFieldEmail);
		
		textFieldRegija = new JTextField();
		textFieldRegija.setEditable(false);
		textFieldRegija.setColumns(10);
		textFieldRegija.setBounds(179, 193, 116, 22);
		frmRegionalniMenader.getContentPane().add(textFieldRegija);
		
		JButton btnNewButton = new JButton("Prika\u017Ei narud\u017Ebe");
		btnNewButton.setBounds(126, 243, 169, 25);
		frmRegionalniMenader.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Prika\u017Ei fakture");
		btnNewButton_1.setBounds(126, 281, 169, 25);
		frmRegionalniMenader.getContentPane().add(btnNewButton_1);
		
		JButton btnPrikaziProdavace = new JButton("Prika\u017Ei prodava\u010De");
		btnPrikaziProdavace.setBounds(126, 319, 169, 25);
		frmRegionalniMenader.getContentPane().add(btnPrikaziProdavace);
		btnPrikaziProdavace.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				PrikazProdavacaMenadzeraGUI prikazprodavaca=new PrikazProdavacaMenadzeraGUI();
				prikazprodavaca.startPrikazProdavaca();
				
			}
		});
		frmRegionalniMenader.setBounds(100, 100, 406, 425);
		frmRegionalniMenader.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		prikaziPodatke();
	}
	
	private void prikaziPodatke()
	{
		Akterprodaje prikaz = aks.dajAktera(id);
		if(prikaz != null)
		{
			textFieldImePrezime.setText(prikaz.getIme() + " " + prikaz.getPrezime());
			textFieldAdresa.setText(prikaz.getAdresa());
			textFieldEmail.setText(prikaz.getEmail());
			textFieldBrojTelefona.setText(prikaz.getBrojtelefona());
			textFieldRegija.setText(prikaz.getRegija().getIme());
		}
	}

}
