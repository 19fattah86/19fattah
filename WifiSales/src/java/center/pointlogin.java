/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package center;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ab-ps
 */
@WebServlet(name = "pointlogin", urlPatterns = {"/pointlogin"})
public class pointlogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
         request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String usntocompare = null;
              String pswtocompare = null;
              String emplId = null;
              String pId = null;
              String categ=null;
              String _username=request.getParameter("usernametxt");
            String _password=request.getParameter("passwordtxt");
            try {
              
              Connection conn=Db.connect();
              
                      String command=("Select pointsemployees.Id, pointsemployees.EmplUserName,"
                              + "pointsemployees.EmplPassword, "
                              + "salespoints.Id, pointsemployees.Class From pointsemployees Left Join salespoints On "
                              + "pointsemployees.pId=salespoints.Id "
                              + "Where (Binary pointsemployees.EmplUserName = '"+_username+"' "
                              + "And Binary EmplPassword='"+_password+"')"
                              );
                      Statement stm=conn.createStatement();
                      ResultSet rs =stm.executeQuery(command);
                      while (rs.next()) {
                           emplId=rs.getString(1);
                         usntocompare=rs.getString(2);
                          pswtocompare=rs.getString(3);
                          pId=rs.getString(4);
                          categ=rs.getString(5);
               }
            } catch (SQLException e) {
                out.print("<p>"+e+"</p>");
                out.print("<p>يوجد خطا اتصال بقاعدة البيانات</p>");
            } 
            
      if( _username.equals(""+usntocompare+"") && _password.equals(pswtocompare)){
      
          HttpSession session=request.getSession();
      session.setAttribute("user", _username);
      session.setAttribute("emplId", emplId);
      session.setAttribute("pId", pId);
          if("director".equals(categ)){
      response.sendRedirect("pouintsupusr");
      }
      else{
    response.sendRedirect("poinstinterfac");
          }
      
   //  RequestDispatcher rd=request.getRequestDispatcher("Center.html");
     //rd.include(request, response);
      
      }  else{
     response.sendRedirect("http://yemwfsales-env.us-east-2.elasticbeanstalk.com/PointLogin.html");
out.print("لقد قمت بادخال بيانات غير صحيحة");
      }  
        
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
