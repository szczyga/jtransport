package MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import models.FvRowModel;
import window.fv.FormFv;

public class MySQL_Fv extends MySQL {
	
//	  Vector<String[]> cache; // will hold String[] objects . . .
//	  int colCount;
//	  String[] headers;
//	  MySQL_Conf conn;
//	  Connection db;
//	  Statement statement;

	String lastFvId;
	
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

	private String getLastFvId(){
		String query="select max(fv_id) fv_id from fv";
		
		lastFvId=null;
		
	      try {
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()){
			lastFvId=rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	    return lastFvId;
	}
	
	private void setFv(Vector<String> row, String fvId){
		
		
		String query="INSERT INTO "
				+ "`transport`.`fv_rows` "
				+ "(`fv_rows_id`, "
				+ "`fv_id`, "
				+ "`cars_id`, "
				+ "`date`, "
				+ "`what_do`, "
				+ "`hours`, "
				+ "`hours50`, "
				+ "`hours100`,"
				+ "`hours200`, "
				+ "`km`, "
				+ "`idle`, "
				+ "`comment`) "
				+ "VALUES "
				+ "(NULL, "
				+ "'"+fvId+"', "
				+ "'"+row.get(0)+"', "
				+ "'"+row.get(3)+"', "
				+ "'"+row.get(4)+"', "
				+ "'"+row.get(5)+"', "
				+ "'"+row.get(6)+"', "
				+ "'"+row.get(7)+"', "
				+ "'"+row.get(8)+"', "
				+ "'"+row.get(9)+"', "
				+ "'"+row.get(10)+"', "
				+ "'"+row.get(11)+"');";

		JOptionPane.showMessageDialog(null, query);
		
		try {
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void setFv(Map<String, String> params){
		
		String isInw=params.get("rodzFv")=="Inwestycyjne"?"1":"0";
		
		String query="INSERT INTO "
				+ "`transport`.`fv` "
				+ "(`fv_id`, "
				+ "`zaklad_id`, "
				+ "`date`, "
				+ "`number`, "
				+ "`is_inw`) "
				+ "VALUES "
				+ "(NULL, "
				+ "'"+params.get("zaklad")+"', "
				+ "'"+params.get("data")+"', "
				+ "'"+params.get("fvNr")+"', "
				+ "'"+isInw+"');";
		
		try {
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void addFv(){
		
		FormFv car=new FormFv();
		int accept=car.addFv();
		
		if(accept==1){
			
			FvRowModel row=car.getRowSet();
			
			setFv(car.getFvHedder());
			
			String fvLastId=getLastFvId();
			
			for(int i=0; i<row.getRowCount();i++){
				setFv(row.getRow(i),fvLastId);
			}
			
		}
		else
		JOptionPane.showMessageDialog(null, "nie dodawanie");
	}
}
