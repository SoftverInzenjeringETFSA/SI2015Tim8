package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class DodavanjeProizvodaGUI {
	
	private Session s;
	private ProizvodServis ps;
	private SefProdajeMainGUI refreshableRoditelj;
	
	private JFrame frmDodajProizvod;
	private JTextField textFieldNaziv;
	private JTextField textFieldNabavnaCijena;
	private JTextField textFieldProdajnaCijena;
	private JTextField textFieldStanjeNaSkladistu;

	/**
	 * Launch the application.
	 */
	public void startDodavanjeProizvoda() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeProizvodaGUI window = new DodavanjeProizvodaGUI(s,refreshableRoditelj);
					window.frmDodajProizvod.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeProizvodaGUI(Session s,SefProdajeMainGUI roditelj) {
		this.s=s;
		this.ps= new ProizvodServis(s);
		this.refreshableRoditelj=roditelj;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDodajProizvod = new JFrame();
		frmDodajProizvod.setTitle("Dodaj proizvod");
		frmDodajProizvod.setBounds(100, 100, 348, 300);
		frmDodajProizvod.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDodajProizvod.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naziv proizvoda:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(41, 46, 109, 14);
		frmDodajProizvod.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nabavna cijena:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(51, 71, 99, 14);
		frmDodajProizvod.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Prodajna cijena:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(41, 96, 109, 14);
		frmDodajProizvod.getContentPane().add(lblNewLabel_2);
		
		JLabel lblStanjeNaSkladitu = new JLabel("Stanje na skladi\u0161tu:");
		lblStanjeNaSkladitu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStanjeNaSkladitu.setBounds(12, 121, 138, 14);
		frmDodajProizvod.getContentPane().add(lblStanjeNaSkladitu);
		
		textFieldNaziv = new JTextField();
		textFieldNaziv.setBounds(160, 43, 146, 20);
		frmDodajProizvod.getContentPane().add(textFieldNaziv);
		textFieldNaziv.setColumns(10);
		
		JButton btnDodajProizvod = new JButton("Dodaj proizvod");
		btnDodajProizvod.setBounds(190, 164, 116, 23);
		btnDodajProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Proizvod p= new Proizvod();
				p.setNaziv(textFieldNaziv.getText());
				p.setKolicina(Integer.parseInt(textFieldStanjeNaSkladistu.getText()));
				p.setNabavnacijena(Double.parseDouble(textFieldNabavnaCijena.getText()));
				p.setProdajnacijena(Double.parseDouble(textFieldProdajnaCijena.getText()));
				ps.kreirajProizvod(p);
				refreshableRoditelj.refreshajTabeluProizvodi();
				
			}
		});
		frmDodajProizvod.getContentPane().add(btnDodajProizvod);
		
		textFieldNabavnaCijena = new JTextField();
		textFieldNabavnaCijena.setBounds(160, 68, 146, 20);
		frmDodajProizvod.getContentPane().add(textFieldNabavnaCijena);
		textFieldNabavnaCijena.setColumns(10);
		
		textFieldProdajnaCijena = new JTextField();
		textFieldProdajnaCijena.setBounds(160, 93, 146, 20);
		frmDodajProizvod.getContentPane().add(textFieldProdajnaCijena);
		textFieldProdajnaCijena.setColumns(10);
		
		textFieldStanjeNaSkladistu = new JTextField();
		textFieldStanjeNaSkladistu.setBounds(160, 118, 146, 20);
		frmDodajProizvod.getContentPane().add(textFieldStanjeNaSkladistu);
		textFieldStanjeNaSkladistu.setColumns(10);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		spinner.setBounds(41, 178, 61, 20);
		frmDodajProizvod.getContentPane().add(spinner);
	}
}
