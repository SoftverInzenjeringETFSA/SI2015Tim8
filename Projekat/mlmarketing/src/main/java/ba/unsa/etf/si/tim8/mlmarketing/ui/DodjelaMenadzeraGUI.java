package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.hibernate.Session;
import org.jboss.logging.Logger;

import ba.unsa.etf.si.tim8.mlmarketing.models.Akterprodaje;
import ba.unsa.etf.si.tim8.mlmarketing.services.AkterServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;

public class DodjelaMenadzeraGUI {

	final static Logger logger = Logger.getLogger(DodjelaMenadzeraGUI.class);
	private Session s;
	private int id = -1;
	private AkterServis aks;
	private SefProdajeMainGUI refreshableRoditelj;
	
	private JFrame frmDodijeliMenadera;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public void startDodjelaMenadzera() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodjelaMenadzeraGUI window = new DodjelaMenadzeraGUI(s,refreshableRoditelj,id);
					window.frmDodijeliMenadera.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodjelaMenadzeraGUI(Session s,SefProdajeMainGUI roditelj, int id) {
		this.s = s;
		this.id = id;
		this.aks = new AkterServis(s);
		this.refreshableRoditelj=roditelj;
		initialize();
		Akterprodaje a = aks.dajAktera(id);
		
		textField.setText(a.getIme()+" "+a.getPrezime());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodijeliMenadera = new JFrame();
		frmDodijeliMenadera.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(aks.dajAktera(id).getAkterprodaje()==null){
					JOptionPane.showMessageDialog(null,"Morate odabrati odgovornog menadzera");
				}
				else frmDodijeliMenadera.dispose();
			}
		});
		frmDodijeliMenadera.setTitle("Dodijeli menad\u017Eera");
		frmDodijeliMenadera.setBounds(100, 100, 313, 204);
		frmDodijeliMenadera.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmDodijeliMenadera.getContentPane().setLayout(null);
		
		JLabel lblImeIPrezime = new JLabel("Ime i prezime prodava\u010Da:");
		lblImeIPrezime.setBounds(12, 57, 154, 14);
		frmDodijeliMenadera.getContentPane().add(lblImeIPrezime);
		
		JLabel lblOdaberiMenadera = new JLabel("Odaberi menad\u017Eera:");
		lblOdaberiMenadera.setBounds(35, 82, 120, 14);
		frmDodijeliMenadera.getContentPane().add(lblOdaberiMenadera);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(166, 54, 120, 20);
		frmDodijeliMenadera.getContentPane().add(textField);
		textField.setColumns(10);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(165, 82, 121, 20);
		frmDodijeliMenadera.getContentPane().add(comboBox);
		ArrayList<Akterprodaje> akteri = aks.dajSveAkterePoTipu("regmen");
		for(int i = 0; i<akteri.size();i++) comboBox.addItem(akteri.get(i));
		
		JButton btnDodijeliMenadzera = new JButton("Dodijeli");
		btnDodijeliMenadzera.setBounds(197, 113, 89, 23);
		btnDodijeliMenadzera.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(SesijaServis.dajTipKorisnika().equals("sef")){
					Akterprodaje a = aks.dajAktera(id);
					a.setAkterprodaje((Akterprodaje)comboBox.getSelectedItem());
					aks.izmjeniAktera(a);
					refreshableRoditelj.refreshajTabeluProdavaci();
					refreshableRoditelj.refreshajTabeluMenadzeri();
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste logovani sa odgovarajućim privilegijama za ovu akciju.");
					frmDodijeliMenadera.dispose();
				}
					
			}
		});
		frmDodijeliMenadera.getContentPane().add(btnDodijeliMenadzera);
	}

}
