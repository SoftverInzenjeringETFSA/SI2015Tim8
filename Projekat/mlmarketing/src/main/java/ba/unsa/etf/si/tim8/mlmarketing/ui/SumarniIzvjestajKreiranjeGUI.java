package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.services.IzvjestajServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

public class SumarniIzvjestajKreiranjeGUI {

	private JFrame frame;
	final static Logger logger = Logger.getLogger(PojedinacniIzvjestajKreiranjeGUI.class);
	private Session s;
	private IzvjestajServis is;
	private JTextField danOd;
	private JTextField godinaOd;
	private JTextField danDo;
	private JTextField godinaDo;
	/**
	 * Launch the application.
	 */
	public void startSumarni() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SumarniIzvjestajKreiranjeGUI window = new SumarniIzvjestajKreiranjeGUI(s);
					window.frame.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SumarniIzvjestajKreiranjeGUI(Session s) {
		this.s=s;
		this.is= new IzvjestajServis(s);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 372, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MaskFormatter datum = new MaskFormatter();
		try {
			datum = new MaskFormatter("##.##.####.");
			datum.setPlaceholderCharacter(' ');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info(e);// e.printStackTrace();
		}
		
		JLabel lblSumarniIzvjestajZa = new JLabel("Sumarni izvjestaj za:");
		lblSumarniIzvjestajZa.setBounds(105, 50, 105, 14);
		frame.getContentPane().add(lblSumarniIzvjestajZa);
		
		final JComboBox comboBoxTip = new JComboBox();
		comboBoxTip.setBounds(233, 47, 105, 20);
		comboBoxTip.addItem("Proizvodi");
		comboBoxTip.addItem("Prodavaci");
		frame.getContentPane().add(comboBoxTip);
		
		JLabel lblOd = new JLabel("Od:");
		lblOd.setBounds(28, 90, 28, 14);
		frame.getContentPane().add(lblOd);
		
		JLabel lblDo = new JLabel("Do:");
		lblDo.setBounds(218, 90, 28, 14);
		frame.getContentPane().add(lblDo);
		
		
		
		JLabel lblDan = new JLabel("Dan:");
		lblDan.setBounds(20, 115, 28, 14);
		frame.getContentPane().add(lblDan);
		
		JLabel lblMjesec = new JLabel("Mjesec:");
		lblMjesec.setBounds(10, 140, 38, 14);
		frame.getContentPane().add(lblMjesec);
		
		JLabel lblGodina = new JLabel("Godina:");
		lblGodina.setBounds(10, 164, 38, 14);
		frame.getContentPane().add(lblGodina);
		
		danOd = new JTextField();
		danOd.setBounds(58, 112, 76, 20);
		frame.getContentPane().add(danOd);
		danOd.setColumns(10);
		
		godinaOd = new JTextField();
		godinaOd.setColumns(10);
		godinaOd.setBounds(58, 161, 76, 20);
		frame.getContentPane().add(godinaOd);
		
		final JComboBox mjesecOd = new JComboBox();
		mjesecOd.addItem("01");
		mjesecOd.addItem("02");
		mjesecOd.addItem("03");
		mjesecOd.addItem("04");
		mjesecOd.addItem("05");
		mjesecOd.addItem("06");
		mjesecOd.addItem("07");
		mjesecOd.addItem("08");
		mjesecOd.addItem("09");
		mjesecOd.addItem("10");
		mjesecOd.addItem("11");
		mjesecOd.addItem("12");
		mjesecOd.setBounds(58, 137, 76, 20);
		frame.getContentPane().add(mjesecOd);
		
		JLabel label = new JLabel("Dan:");
		label.setBounds(218, 115, 28, 14);
		frame.getContentPane().add(label);
		
		danDo = new JTextField();
		danDo.setColumns(10);
		danDo.setBounds(262, 112, 76, 20);
		frame.getContentPane().add(danDo);
		
		final JComboBox mjesecDo = new JComboBox();
		mjesecDo.addItem("01");
		mjesecDo.addItem("02");
		mjesecDo.addItem("03");
		mjesecDo.addItem("04");
		mjesecDo.addItem("05");
		mjesecDo.addItem("06");
		mjesecDo.addItem("07");
		mjesecDo.addItem("08");
		mjesecDo.addItem("09");
		mjesecDo.addItem("10");
		mjesecDo.addItem("11");
		mjesecDo.addItem("12");
		mjesecDo.setBounds(262, 137, 76, 20);
		frame.getContentPane().add(mjesecDo);
		
		godinaDo = new JTextField();
		godinaDo.setColumns(10);
		godinaDo.setBounds(262, 161, 76, 20);
		frame.getContentPane().add(godinaDo);
		
		JLabel label_1 = new JLabel("Godina:");
		label_1.setBounds(208, 164, 38, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Mjesec:");
		label_2.setBounds(208, 140, 38, 14);
		frame.getContentPane().add(label_2);
		
		
		JButton btnGenerisi = new JButton("Generisi");
		btnGenerisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tip = comboBoxTip.getSelectedItem().toString();				
				boolean[] validacije = {false, false, false, false};				
				String errorMsg = "";
				
				String[] greske = {
						"Početni dan mora biti između 1 i maksimalnog broja dana odabranog mjeseca.\n",
						"Krajnji dan mora biti između 1 i maksimalnog broja dana odabranog mjeseca.\n",
						"Unesite validnu pocetnu godinu.\n",
						"Unesite validnu krajnju godinu.\n"
					};
				
				int pocetniDan = 0;
				int krajnjiDan = 0; 
				int pg = 0;
				int kg = 0;
				String pocetnaGodina = godinaOd.getText();
				String krajnjaGodina = godinaDo.getText();	
				
				int pocetniMjesec = ValidacijeServis.dajBrojDana(mjesecOd.getSelectedItem().toString());
				int krajnjiMjesec = ValidacijeServis.dajBrojDana(mjesecDo.getSelectedItem().toString());
				
				if(ValidacijeServis.daLiJeInt(danOd.getText()))
				{
					try{
						pocetniDan = Integer.parseInt(danOd.getText());
						if(pocetniDan >= 1 && pocetniDan <= pocetniMjesec)
						{
							validacije[0] = true;
						}
						else{
							errorMsg += greske[0];
						}
					}
					catch(Exception e)
					{
						
						logger.error(e);
					}
				}
				else 
				{
					errorMsg += greske[0];
				}
				
				if(ValidacijeServis.daLiJeInt(danDo.getText()))
				{
					try{
						krajnjiDan = Integer.parseInt(danDo.getText());
						if(krajnjiDan >= 1 && krajnjiDan <= krajnjiMjesec)
						{
							validacije[1] = true;
						}
						else{
							errorMsg += greske[1];
						}
					}
					catch (Exception e)
					{
						logger.error(e);
					}
				}
				else 
				{
					errorMsg += greske[1];
				}
				
				if(ValidacijeServis.daLiJeInt(pocetnaGodina))
				{
					try{
						pg = Integer.parseInt(pocetnaGodina);
						validacije[2] = true;
					}
					catch(Exception e)
					{
						logger.error(e);
					}
					
				}
				else
				{
					errorMsg += greske[2];
				}
				
				if(ValidacijeServis.daLiJeInt(krajnjaGodina))
				{
					try{
						kg = Integer.parseInt(krajnjaGodina);
						validacije[3] = true;
					}
					catch(Exception e)
					{
						logger.error(e);
					}
					
				}
				else
				{
					errorMsg += greske[3];
				}
				
				if(errorMsg == "")
				{
					Date datumPocetni = is.pocetniDatumSaDanom(pocetniDan, mjesecOd.getSelectedItem().toString() , pg);
					Date datumKrajnji = is.krajnjiDatumSaDanom(krajnjiDan, mjesecDo.getSelectedItem().toString() , kg);
					IzvjestajPrikazForme izf = new IzvjestajPrikazForme(is.sumarniIzvjesta(datumPocetni,datumKrajnji,tip));
					izf.main();
				}
				else JOptionPane.showMessageDialog(null, errorMsg);
				
				
				
			}
		});
		btnGenerisi.setBounds(233, 210, 105, 23);
		frame.getContentPane().add(btnGenerisi);
		
	}
	

}
