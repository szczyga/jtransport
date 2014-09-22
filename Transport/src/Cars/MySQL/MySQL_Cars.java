package Cars.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import MySQL.MySQL;

public class MySQL_Cars extends MySQL {
	
//	  Vector<String[]> cache; // will hold String[] objects . . .
//	  int colCount;
//	  String[] headers;
//	  MySQL_Conf conn;
//	  Connection db;
//	  Statement statement;


		public void getCars(){
			String query="SELECT " 
					    +"c.cars_id, "
						+"c.name as `Nazwa sam.`, "
						+"c.nr_rej as `Numer rej.`, "
						+"p.price as `Cena za godz.`, "
						+"p.price_inw as `Cena za godz. inw.`, "
						+"p.price_km as `Cena za km`, "
						+"p.price_idle as `Cena za post.`, "
						+"e.price50 as `Dodatek 50`, "
						+"e.price100 as `Dodatek 100`, "
						+"e.price200 as `Dodatek 200`"
						+" FROM " 
						+"cars c,"
						+"extras e,"
						+"pricelist p "
						+"WHERE "
						+"c.pricelist_id=p.pricelist_id and "
						+"c.extras_id=e.extras_id";
			
						setQuery(query);
				
		}
	
		public void setCars(String[] params){
			
			String query="INSERT INTO "
					+ "`transport`.`cars` "
					+ "(`cars_id`, `name`, `nr_rej`, `pricelist_id`, `extras_id`) "
					+ "VALUES "
					+ "(NULL, "
					+ "'"+params[0]+"', "
					+ "'"+params[1]+"', "
					+ "'"+params[2]+"', "
					+ "'1');";
			
			try {
				statement.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void delCars(String[] params){
			
			 String query="DELETE FROM `transport`.`cars` WHERE `cars`.`cars_id` = "+getCarsId(params);
			 
				try {
					statement.executeUpdate(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Samochód u¿ywany");
				}
		}

		public String getCarsPriceList(String[] params){
			
			String query="SELECT pricelist_id FROM `cars` WHERE `cars`.`cars_id` = "+getCarsId(params);
			
			setQuery(query);

//			return cache.get(0)[0];
			return id.elementAt(0);
		}
		
		public String getCarsId(String[] params){
			
			String query="SELECT cars_id FROM `cars` WHERE `name` = '"+params[0]+"' AND `nr_rej` = '"+params[1]+"' ";
			
			setQuery(query);

//			return cache.get(0)[0];
			return id.elementAt(0);
		}
		
		
		public void editCars(String[] params){
			

			String query="UPDATE "
					+ "`transport`.`cars` "
					+ "SET "
					+ "`name` = '"+params[1]+"', "
					+ "`nr_rej` = '"+params[2]+"', "
					+ "`pricelist_id` = "+params[3]
					+ " WHERE "
					+ "`cars`.`cars_id` ="+params[0];

				try {
					statement.executeUpdate(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
}
