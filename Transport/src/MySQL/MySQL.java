package MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class MySQL extends AbstractTableModel {

	  Vector<String[]> cache; // will hold String[] objects . . .
	  int colCount;
	  String[] headers;
	  Vector<String> id;
	  MySQL_Conf conn;
	  Connection db;
	  Statement statement;
	  
	  public MySQL() {
		// TODO Auto-generated constructor stub
		    cache = new Vector<String[]>();
		    conn=new MySQL_Conf();
		    try {
				statement=conn.getConn().createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

//****Override methods***********
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colCount;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return cache.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return ((String[]) cache.elementAt(row))[col];
	}
	
//************************************
	
	public void setQuery(String q) {
		
	    cache = new Vector<String[]>();
	    id=new Vector<String>();
	    
	    try {
	      // Execute the query and store the result set and its metadata
	      ResultSet rs = statement.executeQuery(q);
	      ResultSetMetaData meta = rs.getMetaData();
	      colCount = meta.getColumnCount()-1;

	      // Now we must rebuild the headers array with the new column names
	      headers = new String[colCount];
	      for (int h = 1; h <= colCount; h++) {
	        headers[h - 1] = meta.getColumnName(h+1);
	      }

	      // and file the cache with the records from our query. This would
	      // not be
	      // practical if we were expecting a few million records in response
	      // to our
	      // query, but we aren't, so we can do this.

	      while (rs.next()) {
	        String[] record = new String[colCount];
	        
	        id.add(rs.getString(1));
	        
	        for (int i = 0; i < colCount; i++) {
	          record[i] = rs.getString(i + 2);
	        }
	        cache.addElement(record);
	      }
	      fireTableChanged(null); // notify everyone that we have a new table.
	    } catch (Exception e) {
	      cache = new Vector<String[]>(); // blank it out and keep going.
	      e.printStackTrace();
	    }

	  }
	
	public String getId(int rowNr){
	
		return id.get(rowNr);
	}
	
	public String getColumnName(int i) {
	return headers[i];
	}
}
