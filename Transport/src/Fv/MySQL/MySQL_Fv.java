package Fv.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Fv.Window.FormFv;
import Fv.models.FvRowModel;
import MySQL.MySQL;

public class MySQL_Fv extends MySQL {

 /* lastFVId - zmienne przechowuj¹ca ostatni id faktury */
	String lastFvId;
	
/* getFV() zwraca listê faktur */	
	public void getFv(){
		String query="SELECT "
				+ "fv.fv_id,"
				+ "fv.number as `Nr faktury`, "
				+ "fv.date as `Data faktury`, "
				+ "zaklad.name as `Zaklad obci¹¿any`, "
				+ "if (fv.is_inw=0, 'rozliczenie kosztowe', 'rozliczenie inwestycyjne') `Rodz. rozl.` "
				+ " FROM "
				+ "`fv`, "
				+ "`zaklad` "
				+ " WHERE "
				+ "fv.zaklad_id=zaklad.zaklad_id";
		
				setQuery(query);
	}

/* getLastFvId() pobiera ostatni id z tablicy fv, zapisuje do zmiennej lastFvId i go zwraca */
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

/*@params Vector<String> row - zestaw danych wiersza do zapisania do bazy
 * @params  String fvId - numer faktury 
 *  setFv()- wprawadza dane wiersza faktury do tabeli fv_row
 */
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
		
		try {
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

/*
 * @params Map<String, String> params - przekazuje parametry nag³ówka faktury
 * setFv() zapisuje nag³ówek do tabeli fv
 */
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
	
	private Map<String, String> getFvHeadder(String fvId){
		
		String query="SELECT `fv_id`, `zaklad_id`, `date`, `number`, `is_inw` FROM `fv` WHERE `fv_id`="+fvId;
		
		Map<String, String> headder=new HashMap<String, String>();
		
		MySQL sql=new MySQL();
		sql.setQuery(query);
		
		headder.put("zaklad", (String)sql.getValueAt(0, 0));
		headder.put("data", (String)sql.getValueAt(0, 1));
		headder.put("fvNr", (String)sql.getValueAt(0, 2));
		headder.put("rodzFv", (String)sql.getValueAt(0, 3));
		
		return headder;
	}
	
	private FvRowModel getFvRows(String fvId){
		
		String query="SELECT "
				+ "fr.`fv_rows_id`, "		//0
				+ "c.`cars_id`, "			//0
				+ "c.`name`, "				//1
				+ "c.`nr_rej`, "			//2
				+ "fr.`date`, "				//3
				+ "fr.`what_do`, "			//4
				+ "fr.`hours`, "			//5
				+ "fr.`hours50`, "			//6
				+ "fr.`hours100`, "			//7
				+ "fr.`hours200`, "			//8
				+ "fr.`km`, "				//9
				+ "fr.`idle`, "				//10
				+ "fr.`comment` "			//11
				+ "FROM `cars` c, `fv_rows` fr WHERE fr.`cars_id`=c.`cars_id` and fr.`fv_id`="+fvId;
		
		FvRowModel row= new FvRowModel();
		
		Map<String, String> headder=new HashMap<String, String>();
		
		MySQL sql=new MySQL();
		sql.setQuery(query);
		
		for(int i=0; i<sql.getRowCount(); i++){
			Vector<String> tmp=new Vector<String>();
			
			for(int j=0; j<sql.getColumnCount(); j++){
			tmp.add((String)sql.getValueAt(i, j));
			}
			
			row.addRow(tmp);
		}
		
		return row;
	}
/*
 * addFv() - wywo³uje frame do zapisania nowej faktury
 */
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
//		else
//		JOptionPane.showMessageDialog(null, "nie dodawanie");
	}

	public void editFv(String fvId){
		
		getFvHeadder(fvId);
		
		FormFv car=new FormFv(); 		

		int accept=car.editFv(getFvHeadder(fvId), getFvRows(fvId));
		
		if(accept==1){
			
			FvRowModel row=car.getRowSet();
			
			editFv(car.getFvHedder(), fvId);
			
			delRows(fvId);
			
			for(int i=0; i<row.getRowCount();i++){
				setFv(row.getRow(i),fvId);
			}	
		}
//		else
//		JOptionPane.showMessageDialog(null, "nie dodawanie");
		
	}
	
	public void editFv(Map<String, String> params, String fvId){
		
		String isInw=params.get("rodzFv")=="Inwestycyjne"?"1":"0";

		String query="UPDATE "
				+ "`transport`.`fv` "
				+ "SET "
				+ "`zaklad_id` = '"+params.get("zaklad")+"', "
				+ "`date` = '"+params.get("data")+"', "
				+ "`number` = '"+params.get("fvNr")+"', "
				+ "`is_inw` = '"+isInw+"' "
				+ "WHERE "
				+ "`fv`.`fv_id` ="+fvId;
		
		try {
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delRows(String fvId){
		
		String query="DELETE FROM `transport`.`fv_rows` WHERE `fv_rows`.`fv_id`="+fvId;
		
		try {
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public void delFv(String fvId){
		
		delRows(fvId);
		
		String query="DELETE FROM `transport`.`fv` WHERE `fv`.`fv_id` ="+fvId;
		
		try {
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
