package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.hibernate.Session;
import org.jboss.logging.Logger;

import ba.unsa.etf.si.tim8.mlmarketing.models.Proizvod;
import ba.unsa.etf.si.tim8.mlmarketing.services.ProizvodServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class IzmjenaProizvodaGUI {

	final static Logger logger = Logger.getLogger(IzmjenaProizvodaGUI.class);
	private Session s;
	private int id = -1;
	private ProizvodServis ps;
	private SefProdajeMainGUI refreshableRoditelj;
	private JFrame frmIzmijeni;
	private JTextField textFieldStanjeSkladista;
	private JTextField textFieldProdajnaCijena;
	private JTextField textFieldNabavnaCijena;
	private JTextField textFieldNazivProizvoda;

	/**
	 * Launch the application.
	 */
	public void startIzmjenaProizvoda() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IzmjenaProizvodaGUI window = new IzmjenaProizvodaGUI(s, refreshableRoditelj, id);
					window.frmIzmijeni.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IzmjenaProizvodaGUI(Session s, SefProdajeMainGUI roditelj, int id) {
		this.s = s;
		ps = new ProizvodServis(s);
		this.id = id;
		this.refreshableRoditelj = roditelj;
		
		initialize();
		
		Proizvod p = ps.dajProizvod(id);
	    textFieldNazivProizvoda.setText(p.getNaziv());
		textFieldNabavnaCijena.setText(Double.toString(p.getNabavnacijena()));		
		textFieldProdajnaCijena.setText(Double.toString(p.getProdajnacijena()));
		textFieldStanjeSkladista.setText(Integer.toString(p.getKolicina()));
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIzmijeni = new JFrame();
		frmIzmijeni.setTitle("Izmijeni");
		frmIzmijeni.setBounds(100, 100, 304, 266);
		frmIzmijeni.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmIzmijeni.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Naziv proizvoda:");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setBounds(10, 36, 109, 14);
		frmIzmijeni.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Nabavna cijena:");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(10, 61, 109, 14);
		frmIzmijeni.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Prodajna cijena:");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setBounds(10, 86, 109, 14);
		frmIzmijeni.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Stanje na skladi\u0161tu:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(0, 111, 119, 14);
		frmIzmijeni.getContentPane().add(label_3);
		
		textFieldStanjeSkladista = new JTextField();
		textFieldStanjeSkladista.setColumns(10);
		textFieldStanjeSkladista.setBounds(129, 108, 146, 20);
		frmIzmijeni.getContentPane().add(textFieldStanjeSkladista);
		
		textFieldProdajnaCijena = new JTextField();
		textFieldProdajnaCijena.setColumns(10);
		textFieldProdajnaCijena.setBounds(129, 83, 146, 20);
		frmIzmijeni.getContentPane().add(textFieldProdajnaCijena);
		
		textFieldNabavnaCijena = new JTextField();
		textFieldNabavnaCijena.setColumns(10);
		textFieldNabavnaCijena.setBounds(129, 58, 146, 20);
		frmIzmijeni.getContentPane().add(textFieldNabavnaCijena);
		
		textFieldNazivProizvoda = new JTextField();
		textFieldNazivProizvoda.setColumns(10);
		textFieldNazivProizvoda.setBounds(129, 33, 146, 20);
		frmIzmijeni.getContentPane().add(textFieldNazivProizvoda);
		
		JButton btnIzmijeniProizvod = new JButton("Izmijeni proizvod");
		btnIzmijeniProizvod.setBounds(159, 154, 116, 23);
		btnIzmijeniProizvod.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(SesijaServis.dajTipKorisnika().equals("sef")){
					
					
						
						Proizvod p = ps.dajProizvod(id);
						boolean[] validacije = {false, false, false, false};
						String errorMessage = validirajPolja(validacije);
						if(errorMessage.equals(""))
						{
							if(!ps.daLiPostojiProizvod(textFieldNazivProizvoda.getText().trim()))
							{
								int rez = JOptionPane.showOptionDialog(null, "Da li ste sigurni?", "Provjera", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Da", "Ne"}, "default");
								if(rez == JOptionPane.YES_OPTION){
								p.setNaziv(textFieldNazivProizvoda.getText().trim());
								p.setNabavnacijena(Double.parseDouble(textFieldNabavnaCijena.getText().trim()));
								p.setProdajnacijena(Double.parseDouble(textFieldProdajnaCijena.getText().trim()));
								p.setKolicina(Integer.parseInt(textFieldStanjeSkladista.getText().trim()));
								ps.izmijeniProizvod(p);
								refreshableRoditelj.refreshajTabeluProizvodi();
								frmIzmijeni.dispose();
							}
							
							}
							else JOptionPane.showMessageDialog(null, "Proizvod " + textFieldNazivProizvoda.getText() + " već postoji." );
						}
						else JOptionPane.showMessageDialog(null, errorMessage);
						
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Niste logovani sa odgovarajućim privilegijama za ovu akciju.");
					frmIzmijeni.dispose();
				}
				
				
			}
		});
		frmIzmijeni.getContentPane().add(btnIzmijeniProizvod);
	}
	
	
	private String validirajPolja(boolean[] validacije)
	{
		String errorMessage = "";
		String[] greske = new String[]{
				"Naziv proizvoda može sadržavati samo slova, brojeve i razmake. (ne može ostati nepopunjen)\n",
				"Nabavna cijena mora biti broj, ne može biti negativna niti ostati nepopunjena.\n",
				"Prodajna cijena mora biti broj, ne može biti negativna niti ostati nepopunjena.\n",
				"Stanje na skladištu mora cijeli biti broj, ne može biti negativno niti može ostati nepopunjeno.\n"
		};
		validacije[0] = ValidacijeServis.validirajNazivProizvoda(textFieldNazivProizvoda.getText());
		validacije[1] = ValidacijeServis.daLiJeDouble(textFieldNabavnaCijena.getText());
		validacije[2] = ValidacijeServis.daLiJeDouble(textFieldProdajnaCijena.getText());
		
		validacije[3] = ValidacijeServis.daLiJeInt(textFieldStanjeSkladista.getText());
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
