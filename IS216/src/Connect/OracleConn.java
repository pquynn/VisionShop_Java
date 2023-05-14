package Connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleConn {
    private static Connection con = null;

   public static Connection getConnection() {
	   try {
           Class.forName("oracle.jdbc.driver.OracleDriver");
           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##Vision", "21521368");
       } 
       catch (Exception ex) {
       	ex.printStackTrace();
       }
	   
	   return con;
   }
}
