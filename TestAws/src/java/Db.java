import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ab-ps
 */
public class Db {
     
    public static Connection connect() {
      Connection conn = null;
    String url="jdbc:mysql://localhost:3306/wificntr";
            String dbName="wificntr";
        //    String driver="com.mysql.jdbc.Driver";
            String unicod="?useUnicode=yes&characterEncoding=UTF-8";
        //String dbUserName="root";
        //String dbPassword="root";
        
        
       
try
{
    Class.forName("com.mysql.jdbc.Driver");
    conn =DriverManager.getConnection(url+unicod,"root","");
  // Do something with the Connection
} catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}       catch (ClassNotFoundException ex) {
   
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex);
        }
return conn;
    
}
}
