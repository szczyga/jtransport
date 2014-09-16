package MySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class MySQL_ZaklList extends MySQL {
	
//	  Vector<String[]> cache; // will hold String[] objects . . .
//	  int colCount;
//	  String[] headers;
//	  MySQL_Conf conn;
//	  Connection db;
//	  Statement statement;
	
	public MySQL_ZaklList() {
		// TODO Auto-generated constructor stub
		String query="select zaklad_id, name from zaklad";
		
		setQuery(query);	
	}


	public DefaultComboBoxModel<String> getZaklList(){
		
		DefaultComboBoxModel<String> priceLists = new DefaultComboBoxModel<String>();
		
		for(int i=0; i<cache.size();i++){
			priceLists.addElement(cache.get(i)[0]);
		}
		
		
		return priceLists;
	}
	
	public String getZakId(int Id){
		
		return id.get(Id);
	}

		
}
