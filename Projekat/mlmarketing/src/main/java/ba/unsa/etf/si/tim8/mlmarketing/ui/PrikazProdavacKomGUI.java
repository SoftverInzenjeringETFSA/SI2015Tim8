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

public class PrikazProdavacKomGUI {
	
	private Session s;
	private AkterServis aks;
	private int id;

	private JFrame frmProdava;
	private JTextField textFieldImePrezime;
	private JTextField textFieldAdresa;
	private JTextField textFieldBrojTelefona;
	private JTextField textFieldEmail;
	private JTextField textFieldRegija;
	private JTextField textFieldNadlezniMenadzer;

	/**
	 * Launch the application.
	 */
	public void startPrikazProdavaca() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProdavacKomGUI window = new PrikazProdavacKomGUI(s, id);
					window.frmProdava.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProdavacKomGUI(Session s, int id) 
	{
		this.s = s;
		this.id = id;
		this.aks = new AkterServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProdava = new JFrame();
		frmProdava.setTitle("Prodava\u010D");
		frmProdava.getContentPane().setBackground(Color.WHITE);
		frmProdava.setBounds(100, 100, 406, 425);
		frmProdava.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdava.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Ime i prezime:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(83, 27, 93, 16);
		frmProdava.getContentPane().add(label);
		
		textFieldImePrezime = new JTextField();
		textFieldImePrezime.setEditable(false);
		textFieldImePrezime.setColumns(10);
		textFieldImePrezime.setBounds(188, 24, 116, 22);
		frmProdava.getContentPane().add(textFieldImePrezime);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setEditable(false);
		textFieldAdresa.setColumns(10);
		textFieldAdresa.setBounds(188, 60, 116, 22);
		frmProdava.getContentPane().add(textFieldAdresa);
		
		JLabel label_1 = new JLabel("Adresa:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(120, 63, 56, 16);
		frmProdava.getContentPane().add(label_1);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setEditable(false);
		textFieldBrojTelefona.setColumns(10);
		textFieldBrojTelefona.setBounds(188, 99, 116, 22);
		frmProdava.getContentPane().add(textFieldBrojTelefona);
		
		JLabel label_2 = new JLabel("Broj telefona:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(83, 102, 93, 16);
		frmProdava.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Email:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(120, 142, 56, 16);
		frmProdava.getContentPane().add(label_3);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(188, 139, 116, 22);
		frmProdava.getContentPane().add(textFieldEmail);
		
		textFieldRegija = new JTextField();
		textFieldRegija.setEditable(false);
		textFieldRegija.setColumns(10);
		textFieldRegija.setBounds(188, 175, 116, 22);
		frmProdava.getContentPane().add(textFieldRegija);
		
		JLabel label_4 = new JLabel("Regija:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(120, 178, 56, 16);
		frmProdava.getContentPane().add(label_4);
		
		JButton button = new JButton("Prika\u017Ei narud\u017Ebe");
		button.setBounds(135, 268, 169, 25);
		frmProdava.getContentPane().add(button);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				PrikazNarudzbiAkterGUI prikazNarudzbiAkter = new PrikazNarudzbiAkterGUI(s, id);
				prikazNarudzbiAkter.startPrikazNarudzbiAkter();
			}
		});
		
		JButton button_1 = new JButton("Prika\u017Ei fakture");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				PrikazFakturaAkterGUI prikazFakturaAkter = new PrikazFakturaAkterGUI(s, id);
				prikazFakturaAkter.startPrikazFakturaAkter();
			}
		});
		button_1.setBounds(135, 306, 169, 25);
		frmProdava.getContentPane().add(button_1);
		
		JLabel lblNadleniMenader = new JLabel("Nadle\u017Eni menad\u017Eer:");
		lblNadleniMenader.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNadleniMenader.setBounds(60, 213, 116, 16);
		frmProdava.getContentPane().add(lblNadleniMenader);
		
		textFieldNadlezniMenadzer = new JTextField();
		textFieldNadlezniMenadzer.setEditable(false);
		textFieldNadlezniMenadzer.setColumns(10);
		textFieldNadlezniMenadzer.setBounds(188, 210, 116, 22);
		frmProdava.getContentPane().add(textFieldNadlezniMenadzer);
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
			textFieldNadlezniMenadzer.setText(prikaz.getAkterprodaje().getIme() + " " + prikaz.getAkterprodaje().getPrezime());
		}
	}

}
