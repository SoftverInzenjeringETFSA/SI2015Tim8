package ba.unsa.etf.si.tim8.mlmarketing.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class DodavanjeNarudzbeGUI {

	private JFrame frmKreiranjeNarudbe;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void startDodavanjeNarudzbe() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DodavanjeNarudzbeGUI window = new DodavanjeNarudzbeGUI();
					window.frmKreiranjeNarudbe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DodavanjeNarudzbeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKreiranjeNarudbe = new JFrame();
		frmKreiranjeNarudbe.getContentPane().setBackground(Color.WHITE);
		frmKreiranjeNarudbe.setTitle("Kreiranje narud\u017Ebe");
		frmKreiranjeNarudbe.setBounds(100, 100, 376, 390);
		frmKreiranjeNarudbe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmKreiranjeNarudbe.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naru\u010Dilac:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(58, 56, 78, 16);
		frmKreiranjeNarudbe.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Proizvod:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(58, 112, 78, 16);
		frmKreiranjeNarudbe.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(148, 53, 128, 22);
		frmKreiranjeNarudbe.getContentPane().add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(80, 85, 196, 156);
		frmKreiranjeNarudbe.getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(72, 25, 111, 22);
		panel.add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(72, 60, 111, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Koli\u010Dina:");
		lblNewLabel_2.setBounds(-23, 63, 78, 16);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBounds(72, 101, 111, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kreiraj");
		btnNewButton_1.setBounds(129, 278, 97, 25);
		frmKreiranjeNarudbe.getContentPane().add(btnNewButton_1);
	}

}
