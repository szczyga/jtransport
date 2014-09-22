package PriceList.MySQL;

import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import MySQL.MySQL;

public class MySQL_PriceList extends MySQL {
	
	public void getPriceLists(){
		

		String query="SELECT "
		+"`pricelist_id`, "
		+"`nazwa`as `Nazwa cennika`, " 
		+"`price` as `Cena za godz.`," 
		+"`price_inw` as `Cena za godz. inw.`, " 
		+"`price_km` as `Cena za km`, " 
		+"`price_idle` as `Cena za post.` " 
		+"FROM "
		+"`pricelist`";
		
		setQuery(query);
			
	}
	
	public DefaultComboBoxModel<String> getPriceList(){
		
		String query="SELECT `pricelist_id`,`nazwa` FROM `pricelist`";
		
		setQuery(query);
		
		DefaultComboBoxModel<String> priceLists = new DefaultComboBoxModel<String>();
		
		for(int i=0; i<cache.size();i++){
			priceLists.addElement(cache.get(i)[0]);
		}
		
		
		return priceLists;
	}
	
	public void setPriceList(String[] buttons){
		
		String query="INSERT INTO "
				+ "`transport`.`pricelist` ("
				+ "`pricelist_id`, "
				+ "`nazwa`, "
				+ "`price`, "
				+ "`price_inw`, "
				+ "`price_km`, "
				+ "`price_idle`) "
				+ "VALUES "
				+ "(NULL, "
				+ "'"+buttons[0]+"', "
				+ "'"+buttons[1]+"', "
				+ "'"+buttons[2]+"', "
				+ "'"+buttons[3]+"', "
				+ "'"+buttons[4]+"');";
		
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void editPriceList(Vector<String> row){
		

		String query="UPDATE "
				+ "`transport`.`pricelist` "
				+ "SET "
				+ "`nazwa` = '"+row.get(0)+"', "
				+ "`price` = '"+row.get(1)+"', "
				+ "`price_inw` = '"+row.get(2)+"', "
				+ "`price_km` = '"+row.get(3)+"', "
				+ "`price_idle` = '"+row.get(4)+"' "
				+ "WHERE "
				+ "`pricelist`.`pricelist_id` = "+row.get(5);
		
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void delPriceList(String id){
		 String query="DELETE FROM `transport`.`pricelist` WHERE `pricelist`.`pricelist_id` ="+id;
		 
			try {
				statement.executeUpdate(query);
				getPriceLists();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Cennik u¿ywany");
			}
	}

	public String getPriceListId(String nazwa){
		
		String query="SELECT pricelist_id from pricelist where nazwa='"+nazwa+"'";
		
		setQuery(query);
				
		if(id.size()>0)
		return id.elementAt(0);
		else
		return null; 
	}

	public String getPriceListName(String id){
		
		String query="SELECT pricelist_id, nazwa from pricelist where pricelist_id='"+id+"'";
		
		setQuery(query);
		
		if(cache.size()>0)
		return cache.get(0)[0];
		else
		return null;
	}
}
