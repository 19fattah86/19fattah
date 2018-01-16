package center;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.sun.xml.ws.security.addressing.impl.policy.Constants.logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Ab-ps
 */
//wifisales2.czbcq8wwsorw.us-east-2.rds.amazonaws.com
//aa1wbgvwjqyg3t6.czbcq8wwsorw.us-east-2.rds.amazonaws.com:3306
//jdbc:mysql://wifisales2.czbcq8wwsorw.us-east-2.rds.amazonaws.com:3306/wificntr?user=fat2007ah&password=fat2007ah174
public class Db {
    
    public static Connection connect() {
      if(System.getProperty("RDS_HOSTNAME")!=null){
       
      try {
      Class.forName("com.mysql.jdbc.Driver");
      String dbName =System.getProperty("RDS_DB_NAME");
      String userName = System.getProperty("RDS_USERNAME");
      String password = System.getProperty("RDS_PASSWORD");
      String hostname = System.getProperty("RDS_HOSTNAME");
      String port =System.getProperty("RDS_PORT");
      String unicod="?useUnicode=true&characterEncoding=UTF-8";
      //String JDBC_CONNECTION_STRING=System.getProperty("JDBC_CONNECTION_STRING");
      String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/"+dbName;
    // String jdbcUrl="jdbc:mysql://localhost:3306/wificntr";
            Connection con = DriverManager.getConnection(jdbcUrl+unicod,userName,password);
       return con;
    }
    catch (ClassNotFoundException | SQLException e) {System.err.println(e);}
 }
    return null;
        
}
}


