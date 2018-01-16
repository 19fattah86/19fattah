/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import java.io.IOException;
import center.Db;
import java.io.PrintWriter;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author Ab-ps
 */
public class ReturnBalance {
    public static String pId;
    public static double returnbalance(HttpServletRequest request,PrintWriter out){
        try {
            
      Db db=new Db();
      String id=pId;
      Connection conn=db.connect();
      String command=("SELECT Insurance FROM salespoints WHERE Id='"+id+"' ");
      Statement stmnts=conn.createStatement();
      ResultSet rslt=stmnts.executeQuery(command);
            while (rslt.next()) {                
                double retB=(double) rslt.getObject(1);
                return retB;
            }
        } catch (SQLException e) {
            System.err.println(e);
            out.print("!يوجد خطا في الاتصال");
        }
        
    return 0;
    }
}
