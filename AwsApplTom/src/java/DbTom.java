
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
//wifisales1.czbcq8wwsorw.us-east-2.rds.amazonaws.com:3306
//aa1rj5nhe5ndvso.czbcq8wwsorw.us-east-2.rds.amazonaws.com:3306
public class DbTom {
    public static Connection connect() {
       if(System.getProperty("RDS_HOSTNAME")!=null){
       
      try {
      Class.forName("com.mysql.jdbc.Driver");
      String dbName =System.getProperty("RDS_DB_NAME");
      String userName = System.getProperty("RDS_USERNAME");
      String password = System.getProperty("RDS_PASSWORD");
      String hostname = System.getProperty("RDS_HOSTNAME");
      String port =System.getProperty("RDS_PORT");
      String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" 
              + dbName + "?user=" + userName + "&password=" + password;
            Connection con = DriverManager.getConnection(jdbcUrl);
       return con;
    }
    catch (ClassNotFoundException | SQLException e) {System.err.println(e);}
    }
    return null;
        
}
}
