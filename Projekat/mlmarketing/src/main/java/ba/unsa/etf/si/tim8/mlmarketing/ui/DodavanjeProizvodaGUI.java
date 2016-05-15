package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class DodavanjeProizvodaGUI {
	
	final static Logger logger = Logger.getLogger(DodavanjeProizvodaGUI.class);
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
					logger.error(e);
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
				if(SesijaServis.dajTipKorisnika().equals("sef")){
					Proizvod p= new Proizvod();
					boolean[] validacije = {false, false, false, false};
					String errorMessage = validirajPolja(validacije);
					if(errorMessage.equals(""))
					{
						boolean exists = daLiPostoji(textFieldNaziv.getText().trim());
						if(!exists)
						{
							p.setNaziv(textFieldNaziv.getText().trim());
							p.setKolicina(Integer.parseInt(textFieldStanjeNaSkladistu.getText().trim()));
							p.setNabavnacijena(Double.parseDouble(textFieldNabavnaCijena.getText().trim()));
							p.setProdajnacijena(Double.parseDouble(textFieldProdajnaCijena.getText().trim()));
							ps.kreirajProizvod(p);
							refreshableRoditelj.refreshajTabeluProizvodi();
							frmDodajProizvod.dispose();
						}
						else JOptionPane.showMessageDialog(null, "Proizvod " + textFieldNaziv.getText() + " već postoji.");
						
					}
					else JOptionPane.showMessageDialog(null, errorMessage);
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste logovani sa odgovarajućim privilegijama za ovu akciju.");
					frmDodajProizvod.dispose();
				}
				
				
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
		
	}
	
	private boolean daLiPostoji(String nazivProizvoda)
	{
		ArrayList<Proizvod> proizvodi = ps.dajSveProizvode();
		for(int i = 0; i < proizvodi.size(); i++)
		{
			if(proizvodi.get(i).getNaziv().equals(nazivProizvoda))
				return true;
		}
		return false;
	}
	private String validirajPolja(boolean[] validacije)
	{
		String errorMessage = "";
		String[] greske = new String[]{
				"Naziv proizvoda može sadržavati samo slova, brojeve i razmake. (ne može ostati nepopunjen)\n",
				"Nabavna cijena mora biti broj, ne može biti negativna niti ostati nepopunjena.\n",
				"Prodajna cijena mora biti broj, ne može biti negativna niti ostati nepopunjena.\n",
				"Stanje na skladištu mora biti cijeli broj, ne može biti negativno niti može ostati nepopunjeno.\n"
		};
		validacije[0] = ValidacijeServis.validirajNazivProizvoda(textFieldNaziv.getText());
		validacije[1] = ValidacijeServis.daLiJeDouble(textFieldNabavnaCijena.getText());
		validacije[2] = ValidacijeServis.daLiJeDouble(textFieldProdajnaCijena.getText());
		
		validacije[3] = ValidacijeServis.daLiJeInt(textFieldStanjeNaSkladistu.getText());
		for(int i = 0; i < validacije.length; i++)
		{
			if(!validacije[i])
			{
				errorMessage += greske[i];
			}				
		}
		boolean poredakCijena = ValidacijeServis.nabavnaManjaOdProdajne(textFieldNabavnaCijena.getText(), textFieldProdajnaCijena.getText());
		if(!poredakCijena)
		{
			if(validacije[1] && validacije[2])
			{
				errorMessage += "Nabavna cijena mora biti manja od prodajne cijene.\n";
			}			
		}			
		return errorMessage;
			
	}
}
