
import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ab-ps
 */
public class Dbs {
    public static Connection connect() {
   if ("wifisales.czbcq8wwsorw.us-east-2.rds.amazonaws.com" != null) {     
       
      try {
      Class.forName("com.mysql.jdbc.Driver");
      String dbName ="wificntr";
      String userName = "fat2007ah";
      String password = "fat2007ah174";
      String hostname = "wifisales.czbcq8wwsorw.us-east-2.rds.amazonaws.com";
      String port ="3306";
      String jdbcUrl ="jdbc:mysql://" + hostname + ":" + port + "/"
               + dbName + "?user=" + userName + "&password=" + password;
            Connection con = DriverManager.getConnection(jdbcUrl);
       return con;
    }
    catch (ClassNotFoundException | SQLException e) {System.err.println(e);}
   }
    return null;
        
}
    
}
