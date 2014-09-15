package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL_Conf {

	public Connection conn;
	public MySQL_Conf(){
		// TODO Auto-generated method stub
		conn = null;
		
		try {
			new com.mysql.jdbc.Driver();
			//			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=testuser&password=testpassword");
			String connectionUrl = "jdbc:mysql://localhost:3306/transport?useUnicode=true&characterEncoding=utf8";
			String connectionUser = "root";
			String connectionPassword = "";
			conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Connection getConn(){
		return conn;
	}

}
