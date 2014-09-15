package MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class MySQL_Fv extends MySQL {
	
//	  Vector<String[]> cache; // will hold String[] objects . . .
//	  int colCount;
//	  String[] headers;
//	  MySQL_Conf conn;
//	  Connection db;
//	  Statement statement;

	public void getFv(){
		String query="SELECT "
				+ "fv.fv_id,"
				+ "fv.number, "
				+ "fv.date, "
				+ "zaklad.name, "
				+ "if (fv.is_inw=0, 'rozliczenie kosztowe', 'rozliczenie inwestycyjne') r_rozl "
				+ " FROM "
				+ "`fv`, "
				+ "`zaklad` "
				+ " WHERE "
				+ "fv.zaklad_id=zaklad.zaklad_id";
		
				setQuery(query);
			
	}
}
