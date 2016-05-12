package ba.unsa.etf.si.tim8.mlmarketing.ui;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {
	
	public MyTableModel(Object[][] o, String[] s)
	{
		super(o, s);
	}
	public boolean isCellEditable(int row, int column)
	{
		return false;
	}
}
