package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;

public class PrikazRegionalniMenadzerGUI {

	private Session s;
	private AkterServis aks;
	private int id;

	private JFrame frmPregledMenadera;
	private JTextField textFieldimePrezime;
	private JTextField textFieldBrojTelefona;
	private JTextField textFieldAdresa;
	private JTextField textFieldEmail;
	private JTextField textFieldRegija;
	private JTable table;
	private JLabel lblNadleanZa;
	private JButton btnOk;

	/**
	 * Launch the application.
	 */
	public void startPrikazMenadzer() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazRegionalniMenadzerGUI window = new PrikazRegionalniMenadzerGUI(s,id);
					window.frmPregledMenadera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazRegionalniMenadzerGUI(Session s, int id) {
		this.s = s;
		this.id = id;
		aks= new AkterServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPregledMenadera = new JFrame();
		frmPregledMenadera.setTitle("Pregled menad\u017Eera");
		frmPregledMenadera.setBounds(100, 100, 665, 399);
		frmPregledMenadera.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPregledMenadera.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime:");
		lblImeIPrezime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImeIPrezime.setBounds(28, 40, 99, 14);
		frmPregledMenadera.getContentPane().add(lblImeIPrezime);
		
		JLabel lblBrojTelefona = new JLabel("Broj telefona:");
		lblBrojTelefona.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBrojTelefona.setBounds(10, 71, 117, 14);
		frmPregledMenadera.getContentPane().add(lblBrojTelefona);
		
		JLabel lblAdresa = new JLabel("Adresa:");
		lblAdresa.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresa.setBounds(81, 102, 46, 14);
		frmPregledMenadera.getContentPane().add(lblAdresa);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(81, 130, 46, 14);
		frmPregledMenadera.getContentPane().add(lblEmail);
		
		textFieldimePrezime = new JTextField();
		textFieldimePrezime.setEditable(false);
		textFieldimePrezime.setBounds(134, 37, 130, 20);
		frmPregledMenadera.getContentPane().add(textFieldimePrezime);
		textFieldimePrezime.setColumns(10);
		
		textFieldBrojTelefona = new JTextField();
		textFieldBrojTelefona.setEditable(false);
		textFieldBrojTelefona.setBounds(134, 68, 130, 20);
		frmPregledMenadera.getContentPane().add(textFieldBrojTelefona);
		textFieldBrojTelefona.setColumns(10);
		
		textFieldAdresa = new JTextField();
		textFieldAdresa.setEditable(false);
		textFieldAdresa.setBounds(134, 99, 130, 20);
		frmPregledMenadera.getContentPane().add(textFieldAdresa);
		textFieldAdresa.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBounds(134, 127, 130, 20);
		frmPregledMenadera.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Regija:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(81, 161, 46, 14);
		frmPregledMenadera.getContentPane().add(lblNewLabel);
		
		textFieldRegija = new JTextField();
		textFieldRegija.setEditable(false);
		textFieldRegija.setBounds(134, 158, 130, 20);
		frmPregledMenadera.getContentPane().add(textFieldRegija);
		textFieldRegija.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 55, 365, 125);
		frmPregledMenadera.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime i prezime",
				"Broj telefona",
				"Adresa",
				"Email",
				"Regija"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNadleanZa = new JLabel("Nadle\u017Ean za:");
		lblNadleanZa.setBounds(274, 40, 99, 14);
		frmPregledMenadera.getContentPane().add(lblNadleanZa);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(550, 326, 89, 23);
		frmPregledMenadera.getContentPane().add(btnOk);
		prikaziPodatke();
	}
	
	private void prikaziPodatke(){
		Akterprodaje prikaz=aks.dajAktera(id);
		if(prikaz != null)
		{
			textFieldimePrezime.setText(prikaz.getIme() + " " + prikaz.getPrezime());
			textFieldAdresa.setText(prikaz.getAdresa());
			textFieldEmail.setText(prikaz.getEmail());
			textFieldBrojTelefona.setText(prikaz.getBrojtelefona());
			textFieldRegija.setText(prikaz.getRegija().getIme());
		}
			
	}

}
