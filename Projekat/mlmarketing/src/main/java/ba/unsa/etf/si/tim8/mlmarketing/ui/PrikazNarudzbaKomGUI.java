package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import ba.unsa.etf.si.tim8.mlmarketing.models.Narudzba;
import ba.unsa.etf.si.tim8.mlmarketing.models.ProizvodNarudzba;
import ba.unsa.etf.si.tim8.mlmarketing.services.NarudzbaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.SesijaServis;
import ba.unsa.etf.si.tim8.mlmarketing.services.ValidacijeServis;

import javax.swing.JButton;

public class PrikazNarudzbaKomGUI {
	
	final static Logger logger = Logger.getLogger(PrikazNarudzbaKomGUI.class);
	private Session s;
	private NarudzbaServis ns;
	private int id;
	private SefProdajeMainGUI refreshableRoditelj;
	

	private JFrame frmNarudba;
	private JTextField textFieldId;
	private JTextField textFieldDatum;
	private JTextField textFieldNarucilac;
	private JComboBox comboBoxStatus;
	private JTable table;	

	/**
	 * Launch the application.
	 */
	public void startPrikazNarudzbaKom() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrikazNarudzbaKomGUI window = new PrikazNarudzbaKomGUI(s, id, refreshableRoditelj);
					window.frmNarudba.setVisible(true);
				} catch (Exception e) {
					logger.error(e);
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PrikazNarudzbaKomGUI(Session s, int id, SefProdajeMainGUI roditelj) 
	{
		this.s = s;
		this.ns = new NarudzbaServis(s);
		this.id = id;
		this.refreshableRoditelj = roditelj;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNarudba = new JFrame();
		frmNarudba.getContentPane().setBackground(Color.WHITE);
		frmNarudba.setTitle("Narud\u017Eba");
		frmNarudba.setBounds(100, 100, 389, 478);
		frmNarudba.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmNarudba.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(71, 64, 56, 16);
		frmNarudba.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Datum:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(71, 103, 56, 16);
		frmNarudba.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Naru\u010Dilac:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(27, 147, 100, 16);
		frmNarudba.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Status:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(71, 345, 56, 16);
		frmNarudba.getContentPane().add(lblNewLabel_4);
		
		textFieldId = new JTextField();
		textFieldId.setEditable(false);
		textFieldId.setBounds(159, 61, 145, 22);
		frmNarudba.getContentPane().add(textFieldId);
		textFieldId.setColumns(10);
		
		textFieldDatum = new JTextField();
		textFieldDatum.setEditable(false);
		textFieldDatum.setColumns(10);
		textFieldDatum.setBounds(159, 100, 145, 22);
		frmNarudba.getContentPane().add(textFieldDatum);
		
		textFieldNarucilac = new JTextField();
		textFieldNarucilac.setEditable(false);
		textFieldNarucilac.setColumns(10);
		textFieldNarucilac.setBounds(159, 144, 145, 22);
		frmNarudba.getContentPane().add(textFieldNarucilac);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(71, 183, 233, 149);
		frmNarudba.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new MyTableModel(
			new Object[][] {
			},
			new String[] {
				"Proizvod", "Koli\u010Dina"
			}
		));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		comboBoxStatus = new JComboBox();
		comboBoxStatus.setEditable(true);
		comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Potvr\u0111ena", "Odbijena", "Na \u010Dekanju", "Fakturisana"}));
		comboBoxStatus.setBounds(159, 342, 145, 22);
		comboBoxStatus.setEditable(false);
		frmNarudba.getContentPane().add(comboBoxStatus);
		
				
		JButton btnPromijeniStatus = new JButton("Promijeni status");
		btnPromijeniStatus.setBounds(127, 391, 177, 23);		
		frmNarudba.getContentPane().add(btnPromijeniStatus);
		
		Narudzba n = ns.dajNarudzbu(id);
		if(n.getStatus().equals("Potvrđena"))
		{
			comboBoxStatus.setEnabled(false);
			btnPromijeniStatus.hide();
			
		}
		if(n.getStatus().equals("Fakturisana"))
		{
			comboBoxStatus.setEnabled(false);
			btnPromijeniStatus.hide();
		}
		else {
			comboBoxStatus.setModel(new DefaultComboBoxModel(new String[] {"Potvr\u0111ena", "Odbijena", "Na \u010Dekanju"}));
		}
		
		
		if(!SesijaServis.dajTipKorisnika().equals("sef"))
			btnPromijeniStatus.setVisible(false);		
		btnPromijeniStatus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				String status = (String)comboBoxStatus.getSelectedItem();
				Narudzba n = ns.dajNarudzbu(id);
				if(n.getStatus().equals(status))
				{
					JOptionPane.showMessageDialog(null, "Odabrani status narudžbe je isti kao i trenutni.");
					return;
				}
				
				
				if(n.getStatus().equals("Potvrđena") || n.getStatus().equals("Fakturisana"))
				{
					JOptionPane.showMessageDialog(null, "Nije moguće mijenjati status narudzbe koja je potvrđena ili fakturisana.");
					return;
				}
				if(status.equals("Fakturisana"))
				{
					JOptionPane.showMessageDialog(null, "Nije moguće promijeniti status narudžbe u fakturisana. Fakturisanje narudžbe obavlja komercijalista.");
					return;
				}
				
				if(!ns.izmijeniStatusNarudzbe(n, status))
				{
					JOptionPane.showMessageDialog(null, "Nije moguće potvrditi odabranu narudžbu jer nema dovoljno proizvoda na stanju.");
				}
				
				else
				{
										
					JOptionPane.showMessageDialog(null, "Uspješno ste promijenili status narudžbe.");
					refreshableRoditelj.refreshajTabeluNarudzba();
					frmNarudba.dispose();
					
					
				}
				//treba dodati refreshovanje tabele narudzbe i refreshovanje tabele proizvodi
			}
		});
		prikaziPodatke();
		refreshajTabeluProizvodi();
		
		
	}
	
	private void prikaziPodatke()
	{
		Narudzba prikaz = ns.dajNarudzbu(id);
		if(prikaz != null)
		{
			textFieldId.setText(prikaz.getId().toString());
			textFieldDatum.setText(prikaz.getDatum().toString());
			textFieldNarucilac.setText(prikaz.getAkterprodaje().getIme() + " " + prikaz.getAkterprodaje().getPrezime());
			comboBoxStatus.setSelectedItem(prikaz.getStatus());
			
		}
	}
	
	private void refreshajTabeluProizvodi()
	{
		Narudzba prikaz = ns.dajNarudzbu(id);
		if(prikaz != null)
		{
			ProizvodNarudzba[] pn = prikaz.getProizvodNarudzbas().toArray(new ProizvodNarudzba[prikaz.getProizvodNarudzbas().size()]);
			Object[][] data = new Object[pn.length][];
			for(int i = 0; i<pn.length;i++)
				data[i]= new Object[]
				{
					pn[i].getProizvod().getNaziv(), pn[i].getKolicina()
				};
			
			table.setModel(new MyTableModel(
					data,
					new String[] {
						"Proizvod", "Količina"
					}
				));
		}
	}
}
