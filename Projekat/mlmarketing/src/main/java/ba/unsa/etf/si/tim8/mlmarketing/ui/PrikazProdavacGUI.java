package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;

public class PrikazProdavacGUI {

	private Session s;
	private AkterServis aks;
	private int id;
	private JFrame frmPregledProdavac;
	private JTextField textFieldImePrezime;
	private JTextField textFieldBrojTelefona;
	private JTextField textFieldAdresa;
	private JTextField textFieldEmail;
	private JTextField textFieldRegija;
	private JTextField textFieldNM;

	/**
	 * Launch the application.
	 */
	public void startPrikazProdavac() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProdavacGUI window = new PrikazProdavacGUI(s, id);
					window.frmPregledProdavac.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProdavacGUI(Session s, int id) {
		this.s = s;
		this.id = id;
		aks = new AkterServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPregledProdavac = new JFrame();
		frmPregledProdavac.setTitle("Pregled prodava\u010Da");
		frmPregledProdavac.setBounds(100, 100, 450, 300);
		frmPregledProdavac.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPregledProdavac.getContentPane().setLayout(null);
		
		textFieldImePrezime = new JTextField();
		textFieldImePrezime.setEditable(false);
		textFieldImePrezime.setColumns(10);
		textFieldImePrezime.setBounds(134, 31, 130, 20);
		frmPregledProdavac.getContentPane().add(textFieldImePrezime);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setEditable(false);
		textFieldBrojTelefona.setColumns(10);
		textFieldBrojTelefona.setBounds(134, 62, 130, 20);
		frmPregledProdavac.getContentPane().add(textFieldBrojTelefona);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setEditable(false);
		textFieldAdresa.setColumns(10);
		textFieldAdresa.setBounds(134, 93, 130, 20);
		frmPregledProdavac.getContentPane().add(textFieldAdresa);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(134, 121, 130, 20);
		frmPregledProdavac.getContentPane().add(textFieldEmail);
		
		textFieldRegija = new JTextField();
		textFieldRegija.setEditable(false);
		textFieldRegija.setColumns(10);
		textFieldRegija.setBounds(134, 152, 130, 20);
		frmPregledProdavac.getContentPane().add(textFieldRegija);
		
		JLabel label = new JLabel("Ime i prezime:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(28, 34, 99, 14);
		frmPregledProdavac.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Broj telefona:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 65, 117, 14);
		frmPregledProdavac.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Adresa:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(81, 96, 46, 14);
		frmPregledProdavac.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Email:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(81, 124, 46, 14);
		frmPregledProdavac.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Regija:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(81, 155, 46, 14);
		frmPregledProdavac.getContentPane().add(label_4);
		
		JLabel lblNadleniMenader = new JLabel("Nadle\u017Eni menad\u017Eer:");
		lblNadleniMenader.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNadleniMenader.setBounds(28, 186, 99, 14);
		frmPregledProdavac.getContentPane().add(lblNadleniMenader);
		
		textFieldNM = new JTextField();
		textFieldNM.setEditable(false);
		textFieldNM.setBounds(134, 183, 130, 20);
		frmPregledProdavac.getContentPane().add(textFieldNM);
		textFieldNM.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(335, 227, 89, 23);
		frmPregledProdavac.getContentPane().add(btnOk);
		prikazPodatakaProdavaca();
	}
	
	private void prikazPodatakaProdavaca()
	{
		
		Akterprodaje a = aks.dajAktera(id);
		if(a != null)
		{
			textFieldImePrezime.setText(a.getIme() + " " + a.getPrezime());
			textFieldBrojTelefona.setText(a.getBrojtelefona()); 
			textFieldEmail.setText(a.getEmail());
			textFieldAdresa.setText(a.getAdresa()); 
			textFieldNM.setText(a.getAkterprodaje().getIme() + " " + a.getAkterprodaje().getPrezime());
			textFieldRegija.setText(a.getRegija().getIme());
		}
		
		
	}

}
