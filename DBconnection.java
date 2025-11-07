package campusconnect.db;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBconnection {
 public static Connection getConnection()
 {
	 Connection con =null;
	 try
	 {
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 con =DriverManager.getConnection(
				  "jdbc:mysql://localhost:3306/campus_connect", 
				  "root",
				  "suriya2526"
				);
	 }
	 catch(Exception e)
	 {
		 System.out.println("Database Connection Error: " + e.getMessage());
	 }
	return con;
	 
 }
}
