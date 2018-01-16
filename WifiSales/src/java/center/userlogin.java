/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package center;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
/**
 *
 * @author Ab-ps
 */
@WebServlet(name = "userlogin", urlPatterns = {"/userlogin"})
public class userlogin extends HttpServlet {

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
            //String usntocompare;
           //String pswtocompare;
          String usntocompare=null;
                          String pswtocompare=null;
                          String clId=null;
                           String _username=null;
            String _password=null;
            try {
             
              Connection conn=Db.connect();
              _username=request.getParameter("usernametxt");
            _password=request.getParameter("passwordtxt");
            String command=("Select Id, UserName,password From clients "
           + "Where (Binary UserName = '"+_username+"' And Binary password='"+_password+"')");
                      Statement stm=conn.createStatement();
                      ResultSet rs =stm.executeQuery(command);
                      while (rs.next()) {
                         usntocompare=(String) rs.getObject(2);
                          pswtocompare=(String) rs.getObject(3);
                          clId=(String) rs.getString(1);
               
            }
            } catch (SQLException e) {
                out.print("<p>"+e+"</p>");
                out.print("<p>يوجد خطا اتصال بقاعدة البيانات</p>");
            }
        if( _username.equals(""+usntocompare+"") && pswtocompare.equals(_password)){
      HttpSession session=request.getSession();
      session.setAttribute("user", _username);
      session.setAttribute("uId", clId);
    response.sendRedirect("userinterface");
   //  RequestDispatcher rd=request.getRequestDispatcher("Center.html");
     //rd.include(request, response);
      
      } else{
           
     response.sendRedirect("http://yemwfsales-env.us-east-2.elasticbeanstalk.com/UserLogin.html");
           
   
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
