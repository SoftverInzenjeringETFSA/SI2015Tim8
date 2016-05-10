package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;

public class PrikazProizvodGUI {
	
	private int id;
	private Session s;
	private ProizvodServis ps;

	private JFrame frmProizvod;
	private JTextField textFieldNaziv;
	private JTextField textFieldNabavnaCijena;
	private JTextField textFieldProdajnaCijena;
	private JTextField textFieldStanjeNaSkladistu;

	/**
	 * Launch the application.
	 */
	public void startPrikazProizvod() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazProizvodGUI window = new PrikazProizvodGUI(s,id);
					window.frmProizvod.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazProizvodGUI(Session s,int id) {
		this.s=s;
		this.id=id;
		this.ps= new ProizvodServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProizvod = new JFrame();
		frmProizvod.getContentPane().setBackground(Color.WHITE);
		frmProizvod.setTitle("Proizvod");
		frmProizvod.setBounds(100, 100, 450, 417);
		frmProizvod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProizvod.getContentPane().setLayout(null);
		
		JLabel lblNazivProizvoda = new JLabel("Naziv proizvoda:");
		lblNazivProizvoda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNazivProizvoda.setBounds(79, 76, 105, 16);
		frmProizvod.getContentPane().add(lblNazivProizvoda);
		
		JLabel lblNabavnaCijena = new JLabel("Nabavna cijena:");
		lblNabavnaCijena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNabavnaCijena.setBounds(79, 123, 105, 16);
		frmProizvod.getContentPane().add(lblNabavnaCijena);
		
		JLabel lblProdajnaCijena = new JLabel("Prodajna cijena:");
		lblProdajnaCijena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProdajnaCijena.setBounds(68, 171, 116, 16);
		frmProizvod.getContentPane().add(lblProdajnaCijena);
		
		JLabel lblStanjeNaSkladitu = new JLabel("Stanje na skladi\u0161tu:");
		lblStanjeNaSkladitu.setBounds(68, 216, 116, 16);
		frmProizvod.getContentPane().add(lblStanjeNaSkladitu);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setEditable(false);
		textFieldNaziv.setBounds(209, 73, 116, 22);
		frmProizvod.getContentPane().add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		textFieldNabavnaCijena = new JTextField();
		textFieldNabavnaCijena.setEditable(false);
		textFieldNabavnaCijena.setColumns(10);
		textFieldNabavnaCijena.setBounds(209, 120, 116, 22);
		frmProizvod.getContentPane().add(textFieldNabavnaCijena);
		
		textFieldProdajnaCijena = new JTextField();
		textFieldProdajnaCijena.setEditable(false);
		textFieldProdajnaCijena.setColumns(10);
		textFieldProdajnaCijena.setBounds(209, 168, 116, 22);
		frmProizvod.getContentPane().add(textFieldProdajnaCijena);
		
		textFieldStanjeNaSkladistu = new JTextField();
		textFieldStanjeNaSkladistu.setBounds(209, 213, 116, 22);
		frmProizvod.getContentPane().add(textFieldStanjeNaSkladistu);
		textFieldStanjeNaSkladistu.setColumns(10);
		
		JButton btnNewButton = new JButton("Potvrdi promjenu stanja");
		btnNewButton.setBounds(94, 269, 231, 25);
		frmProizvod.getContentPane().add(btnNewButton);
		prikaziPodatkeZaProizvod();
	}
	
	public void prikaziPodatkeZaProizvod(){
		Proizvod p = ps.dajProizvod(id);
		textFieldNaziv.setText(p.getNaziv());
		textFieldNabavnaCijena.setText(Double.toString(p.getNabavnacijena()));
		textFieldProdajnaCijena.setText(Double.toString(p.getProdajnacijena()));
		textFieldStanjeNaSkladistu.setText(Integer.toString(p.getKolicina()));
	}

}
