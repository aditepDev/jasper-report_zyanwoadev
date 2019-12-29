package org.model.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public final class JDBCConnection
{
	
	public static Connection getJDBCConnection() {
		Connection conn = null;
		
		try {
        	Class.forName(Config.DRIVER);
        	conn =  DriverManager.getConnection(Config.URL + "?user="+Config.USERNAME+"&password="+Config.PASSWORD);         
        	if(conn != null){
				System.out.println("Database Connected.");
			} else {
				System.out.println("Database Connect Failed.");
			}
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your mariadb JDBC Driver?");
            e.printStackTrace();
        }
		
		return conn;
	}
}
