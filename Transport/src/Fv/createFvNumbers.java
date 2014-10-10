package Fv;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import MySQL.MySQL_Conf;

public class createFvNumbers {
	
	 MySQL_Conf conn;
	 Statement statement;
	 
	 String zakres[];
	 
	public createFvNumbers(Date dateStart, Date dateEnd) throws HeadlessException, SQLException {
		// TODO Auto-generated constructor stub
		MySQL_init();
		
		zakres=new String[2];
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		zakres[0]=df.format(dateStart);
		zakres[1]=df.format(dateEnd);
		
		
		String maxYear[]=getMaxYear(zakres);
		String maxNumber="";
		
		if(maxYear==null)
		{
			JOptionPane.showMessageDialog(null, "W podanym zakresie dat nie ma faktur.");
		}
		else
		{
			if(checkRange(zakres)!=null){
				JOptionPane.showMessageDialog(null, "Poza podanym okresem istniej¹ puste numery faktur.");
			}
			else{
			maxNumber=getMaxNumber(maxYear);

			setFvNumber(zakres, maxNumber);
			}	
		}
	}
	
	private void MySQL_init(){
	
	    conn=new MySQL_Conf();
	    try {
			statement=conn.getConn().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private String checkRange(String[] zakres) throws SQLException{
		
		String flag=null;
		
		String query="select fv_id "
				+ "from transport.fv "
				+ "where "
				+ "date not between '"+zakres[0]+"' and '"+zakres[1]+"' and "
				+ "number='';";
		
		ResultSet rs = statement.executeQuery(query);
		
		while (rs.next()){
			flag=rs.getString(1);
		}		
		
		return flag;
	}
	
	private String[] getMaxYear(String[] zakres) throws SQLException{
		
		String maxYear[]=new String[2];

		
		String query="select "
				+ "min(month) min_month, "
				+ "max(year) max_year "
				+ "from	"
				+ "( "
				+ "SELECT "
				+ "DATE_FORMAT(date,'%m') month, "
				+ "DATE_FORMAT(date,'%Y') year "
				+ "FROM "
				+ "transport.fv "
				+ "where "
				+ "date between '"+zakres[0]+"' and '"+zakres[1]+"' "
				+ "group by "
				+ "DATE_FORMAT(date,'%m'), "
				+ "DATE_FORMAT(date,'%Y') "
				+ "order by "
				+ "DATE_FORMAT(date,'%Y'), "
				+ "DATE_FORMAT(date,'%m') "
				+ ") zakres;";
		
		ResultSet rs = statement.executeQuery(query);
		
		while (rs.next()){
			maxYear[0]=rs.getString(1);
			maxYear[1]=rs.getString(2);
		}
	
		return maxYear;
	}
	
	private String getMaxNumber(String year[]) throws SQLException{
		
		String maxNumber="";
		
		int prevMonth=Integer.parseInt(year[0]);
		prevMonth--;
		
		String query="SELECT "
				+ "max(number) max "
				+ "FROM "
				+ "transport.fv "
				+ "where "
				+ "date between STR_TO_DATE('01,01,"+year[1]+"','%d,%m,%Y') and STR_TO_DATE('31,"+String.valueOf(prevMonth)+","+year[1]+"','%d,%m,%Y') ;";
		

		ResultSet rs = statement.executeQuery(query);
		
		while (rs.next()){
			maxNumber=rs.getString(1);
		}		
		
		if(maxNumber==null){
			maxNumber="0";
		}
		
//		return Integer.parseInt(maxNumber);
		return maxNumber;
	}
	
	private void setFvNumber(String[] zakres, String maxNumber) throws SQLException{
		
		Vector<String[]> okresy=new Vector<String[]>();
		
		String query="SELECT "
				+ "DATE_FORMAT(date,'%m') month, "
				+ "DATE_FORMAT(date,'%Y') year "
				+ "FROM "
				+ "transport.fv "
				+ "where "
				+ "date between '"+zakres[0]+"' and '"+zakres[1]+"' "
				+ "group by "
				+ "DATE_FORMAT(date,'%m'), "
				+ "DATE_FORMAT(date,'%Y') "
				+ "order by "
				+ "DATE_FORMAT(date,'%Y'), "
				+ "DATE_FORMAT(date,'%m');";
		
		ResultSet rs = statement.executeQuery(query);
		
		while (rs.next()){			
			String row[]={rs.getString(1),rs.getString(2)};
			okresy.add(row);
		}
	
		int number=Integer.parseInt(maxNumber);
		
		for(String[] okres: okresy){
			
		int okrNum=number;
		
		query="select fv_id from transport.fv where date between STR_TO_DATE('01,"+okres[0]+","+okres[1]+"','%d,%m,%Y') and LAST_DAY(STR_TO_DATE('01,"+okres[0]+","+okres[1]+"','%d,%m,%Y')) order by  zaklad_id, date ;";

		rs = statement.executeQuery(query);
		
		Vector<String> update=new Vector<String>();
		
		while (rs.next()){			
			number=number+1;
			
			String qupdate="UPDATE `transport`.`fv` SET `number`='"+String.valueOf(number)+"' WHERE `fv_id`='"+rs.getString(1)+"'; ";
			
			update.add(qupdate);	
		}	

		if(number==okrNum){
			JOptionPane.showMessageDialog(null, "Brak faktur do uzupe³nienia.");
		}
		else{
			for(String q: update){
				
				try {
					statement.executeUpdate(q);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		}
		}
	}
}
