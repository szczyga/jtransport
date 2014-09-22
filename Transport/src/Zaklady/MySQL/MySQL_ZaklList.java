package Zaklady.MySQL;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import MySQL.MySQL;

public class MySQL_ZaklList extends MySQL {
	

	public void getZaklList(){
		String query="select zaklad_id, name as `Nazwa zak³adu` from zaklad";
		
		setQuery(query);
	}
	
	public void setZakladList(String[] buttons){
		
		String query="INSERT INTO "
				+ "`transport`.`zaklad` ("
				+ "`zaklad_id`, "
				+ "`name`) "
				+ "VALUES "
				+ "(NULL, "
				+ "'"+buttons[0]+"');";
		
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void editZakladList(Vector<String> row){
		

		String query="UPDATE "
				+ "`transport`.`zaklad` "
				+ "SET "
				+ "`name` = '"+row.get(0)+"' "
				+ "WHERE "
				+ "`zaklad`.`zaklad_id` = "+row.get(1);
		
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void delZakladList(String id){
		 String query="DELETE FROM `transport`.`zaklad` WHERE `zaklad`.`zaklad_id` ="+id;
		 
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Zak³ad u¿ywany");
			}
	}
	
	public DefaultComboBoxModel<String> getZaklListCombo(){
		
		getZaklList();
		
		DefaultComboBoxModel<String> priceLists = new DefaultComboBoxModel<String>();
		
		for(int i=0; i<cache.size();i++){
			priceLists.addElement(cache.get(i)[0]);
		}
		
		
		return priceLists;
	}
	
	public int getZakIndex(String Id){
		
		int index=666;
		
		for(int i=0; i<id.size(); i++){
			if(id.get(i).equals(Id))
			return i;
		}
		
		return index;
	}
	
	public String getZakId(int Id){
		
		return id.get(Id);
	}
	

		
}
