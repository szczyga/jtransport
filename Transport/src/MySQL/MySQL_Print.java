package MySQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import print.DataBeanFv;

public class MySQL_Print extends MySQL {

	
		public void getFvHeadder(String FvId){
			
			String query="SELECT "
					+ "fv.fv_id, "
					+ "fv.number, "
					+ "fv.date, "
					+ "zaklad.name, "
					+ "if (fv.is_inw=0, 'Rozliczenie kosztowe', 'Rozliczenie inwestycyjne') r_rozl "
					+ "FROM `fv`, "
					+ "`zaklad` "
					+ "WHERE "
					+ "fv.zaklad_id=zaklad.zaklad_id and "
					+ "fv.fv_id="+FvId;
			
					setQuery(query);				
		}
	
		public void getFvRows(String FvId){
			
			String query="SELECT "
					+ "fr.`fv_id`, "
					+ "c.`name`, "
					+ "c.`nr_rej`, "
					+ "fr.`date`, "
					+ "fr.`what_do`, "
					+ "fr.`hours`, "
					+ "fr.`hours50`, "
					+ "fr.`hours100`, "
					+ "fr.`hours200`, "
					+ "fr.`km`, "
					+ "fr.`idle`, "
					+ "fr.`comment` "
					+ "FROM "
					+ "`cars` c, "
					+ "`fv_rows` fr "
					+ "WHERE "
					+ "fr.`cars_id`=c.`cars_id` and "
					+ "fr.`fv_id`="+FvId;
			
					setQuery(query);				
		}
		
		public void getFvSummary(String FvId){
			
			String query="SELECT "
					+"`fv`.`fv_id`, "
					+"round(sum(IF(`fv`.`is_inw`=0,`row`.`hours`*`pricelist`.`price`, `row`.`hours`*`pricelist`.`price_inw`)),2) as godz, "
					+"round(sum(`row`.`hours50`*`extras`.`price50`),2) as iloczyn_50, " 
					+"round(sum(`row`.`hours100`*`extras`.`price100`),2)as iloczyn_100,  "
					+"round(sum(`row`.`hours200`*`extras`.`price200`),2)as iloczyn_200,  "
					+"round(sum(`row`.`km`*`pricelist`.`price_km`),2)as iloczyn_km, " 
					+"round(sum(`row`.`idle`*`pricelist`.`price_idle`),2)as iloczyn_idle, "
					+"round(sum(`row`.`idle`*`pricelist`.`price_idle`),2)+ "
					+"round(sum(IF(`fv`.`is_inw`=0,`row`.`hours`*`pricelist`.`price`, `row`.`hours`*`pricelist`.`price_inw`)),2)+ "
					+"round(sum(`row`.`hours50`*`extras`.`price50`),2)+ " 
					+"round(sum(`row`.`hours100`*`extras`.`price100`),2)+  "
					+"round(sum(`row`.`hours200`*`extras`.`price200`),2)+ " 
					+"round(sum(`row`.`km`*`pricelist`.`price_km`),2)+ " 
					+"round(sum(`row`.`idle`*`pricelist`.`price_idle`),2)as suma "
					+"FROM " 
					+"`fv`, "
					+"`fv_rows` `row`, "
					+"`cars`, "
					+"`pricelist`, "
					+"`extras` "
					+"where "
					+"`row`.`fv_id`=`fv`.`fv_id` and "
					+"`row`.`cars_id`=`cars`.`cars_id` and "
					+"`cars`.`pricelist_id`=`pricelist`.`pricelist_id` and "
					+"`cars`.`extras_id`=`extras`.`extras_id` and "
					+"`fv`.`fv_id`="+FvId+" "
					+"group by "
					+"`fv`.`fv_id` ";
			
					setQuery(query);				
		}
		
		private void getFvKmSummary(String FvId){
			
			String query="SELECT " 
						+"`fv`.`fv_id`, "
						+"sum(`row`.`hours`) as suma_godz, "
						+"sum(`row`.`hours50`) as suma_50, "
						+"sum(`row`.`hours100`)as suma_100, " 
						+"sum(`row`.`hours200`)as suma_200, " 
						+"sum(`row`.`km`)as suma_km, "
						+"sum(`row`.`idle`)as suma_idle "
						+"FROM " 
						+"`fv`, "
						+"`fv_rows` `row` "
						+"where "
						+"`row`.`fv_id`=`fv`.`fv_id` and "
						+"`fv`.`fv_id`="+FvId+" "
						+"group by "
						+"`fv`.`fv_id` ";
			
			setQuery(query);
		}
		
		public Map<String, String> returnParams(String FvId){
			
			getFvHeadder(FvId);
			
			Map<String, String> param= new HashMap<String, String>();
			
			param.put("number", String.valueOf(getValueAt(0, 0)));
			param.put("date", String.valueOf(getValueAt(0, 1)));
			param.put("z_name", String.valueOf(getValueAt(0, 2)));
			param.put("r_rozl", String.valueOf(getValueAt(0, 3)));
			
			getFvSummary(FvId);
			
			param.put("godz", String.valueOf(getValueAt(0, 0)));
			param.put("iloczyn_50", String.valueOf(getValueAt(0, 1)));
			param.put("iloczyn_100", String.valueOf(getValueAt(0, 2)));
			param.put("iloczyn_200", String.valueOf(getValueAt(0, 3)));
			param.put("iloczyn_km", String.valueOf(getValueAt(0, 4)));
			param.put("iloczyn_idle", String.valueOf(getValueAt(0, 5)));
			
			param.put("suma", String.valueOf(getValueAt(0, 6)));
			
			getFvKmSummary(FvId);
			
			param.put("suma_godz", String.valueOf(getValueAt(0, 0)));
			param.put("suma_50", String.valueOf(getValueAt(0, 1)));
			param.put("suma_100", String.valueOf(getValueAt(0, 2)));
			param.put("suma_200", String.valueOf(getValueAt(0, 3)));
			param.put("suma_km", String.valueOf(getValueAt(0, 4)));
			param.put("suma_idle", String.valueOf(getValueAt(0, 5)));
			
			
			return param;
			
		}
		
		public ArrayList<DataBeanFv> returnFvRows(String FvId){
			
			getFvRows(FvId);
			
			ArrayList<DataBeanFv> lista=new ArrayList<DataBeanFv>();
			
			for(int r=0; r<getRowCount();r++){
				Vector<String> tmp=new Vector<String>();
				for(int c=0;c<getColumnCount();c++){
				tmp.add((String)getValueAt(r, c));
				}
				
				lista.add(produceBean(tmp));
			}
			
			
			return lista;
			
		}
		
        private DataBeanFv produceBean(Vector<String> row) {
            DataBeanFv dataBean = new DataBeanFv();
           
			dataBean.setName(row.get(0));
			dataBean.setNr_rej(row.get(1));
			dataBean.setDate(row.get(2));
			dataBean.setWhat_do(row.get(3));
			dataBean.setHours(Integer.parseInt(row.get(4)));
			dataBean.setHours50(Integer.parseInt(row.get(5)));
			dataBean.setHours100(Integer.parseInt(row.get(6)));
			dataBean.setHours200(Integer.parseInt(row.get(7)));
			dataBean.setKm(Integer.parseInt(row.get(8)));
			dataBean.setIdle(Integer.parseInt(row.get(9)));
			dataBean.setComment(row.get(10));
			
			return dataBean;
        }
}
