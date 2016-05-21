package ba.unsa.etf.si.tim8.mlmarketing.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.table.TableModel;

public class FajlUpisServis {
	public static void exportTable(TableModel ulazniModel,File file) throws IOException{
	    TableModel model = ulazniModel;
	    FileWriter out = new FileWriter(file);
	    BufferedWriter bw = new BufferedWriter(out);
	    for (int i=0;i<model.getColumnCount();i++){
	      bw.write(model.getColumnName(i)+"\t");
	    }
	    bw.write("\n");
	    for (int i=0;i<model.getRowCount();i++){
	      for (int j=0;j<model.getColumnCount();j++){
	        bw.write(model.getValueAt(i,j).toString()+"\t");
	      }
	      bw.write("\n");
	    }
	    bw.close();
	}

}
