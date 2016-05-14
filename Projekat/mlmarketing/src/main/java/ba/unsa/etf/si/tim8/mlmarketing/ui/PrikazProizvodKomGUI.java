package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;

public class PrikazProizvodKomGUI {
	
	final static Logger logger = Logger.getLogger(PrikazProizvodKomGUI.class);
	private int id;
	private Session s;
	private ProizvodServis ps;

	private JFrame frmProizvod;
	private JTextField textFieldNaziv;
	private JTextField textFieldNabavnaCijena;
	private JTextField textFieldProdajnaCijena;
	private JTextField textFieldStanje;

	/**
	 * Launch the application.
	 */
	public void startPrikazProizvodKom() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProizvodKomGUI window = new PrikazProizvodKomGUI(s, id);
					window.frmProizvod.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProizvodKomGUI(Session s, int id) 
	{
		this.s = s;
		this.id = id;
		this.ps = new ProizvodServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProizvod = new JFrame();
		frmProizvod.setTitle("Proizvod");
		frmProizvod.getContentPane().setBackground(Color.WHITE);
		frmProizvod.setBounds(100, 100, 372, 336);
		frmProizvod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProizvod.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Naziv proizvoda:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(50, 65, 105, 16);
		frmProizvod.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nabavna cijena:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(50, 112, 105, 16);
		frmProizvod.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Prodajna cijena:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(39, 160, 116, 16);
		frmProizvod.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Stanje na skladi\u0161tu:");
		label_3.setBounds(39, 205, 116, 16);
		frmProizvod.getContentPane().add(label_3);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setEditable(false);
		textFieldNaziv.setColumns(10);
		textFieldNaziv.setBounds(180, 62, 116, 22);
		frmProizvod.getContentPane().add(textFieldNaziv);
		
		textFieldNabavnaCijena = new JTextField();
		textFieldNabavnaCijena.setEditable(false);
		textFieldNabavnaCijena.setColumns(10);
		textFieldNabavnaCijena.setBounds(180, 109, 116, 22);
		frmProizvod.getContentPane().add(textFieldNabavnaCijena);
		
		textFieldProdajnaCijena = new JTextField();
		textFieldProdajnaCijena.setEditable(false);
		textFieldProdajnaCijena.setColumns(10);
		textFieldProdajnaCijena.setBounds(180, 157, 116, 22);
		frmProizvod.getContentPane().add(textFieldProdajnaCijena);
		
		textFieldStanje = new JTextField();
		textFieldStanje.setEditable(false);
		textFieldStanje.setColumns(10);
		textFieldStanje.setBounds(180, 202, 116, 22);
		frmProizvod.getContentPane().add(textFieldStanje);
		prikaziPodatkeZaProizvod();
	}
	
	public void prikaziPodatkeZaProizvod(){
		Proizvod p = ps.dajProizvod(id);
		textFieldNaziv.setText(p.getNaziv());
		textFieldNabavnaCijena.setText(Double.toString(p.getNabavnacijena()));
		textFieldProdajnaCijena.setText(Double.toString(p.getProdajnacijena()));
		textFieldStanje.setText(Integer.toString(p.getKolicina()));
	}

}
