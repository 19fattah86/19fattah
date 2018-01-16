/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package center;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
             String usntocompare=null;
                          String pswtocompare=null;
                          String eId=null;
                           String _username=null;
                           String categ=null;
            String _password=null;
            try{
                
                Connection conn=Db.connect();
            _username=request.getParameter("usernametxt");
            _password=request.getParameter("passwordtxt");
           String command=("Select Id, EmplUserName, EmplPasswoed, emplCategory From employees"
                   + " Where (Binary EmplUserName='"+_username+"'"
                   + "And Binary EmplPasswoed='"+_password+"')"); 
           Statement stm=conn.createStatement();
           ResultSet rs=stm.executeQuery(command);
           while (rs.next()){
           eId=rs.getString(1);
           usntocompare=rs.getString(2);
           pswtocompare=rs.getString(3);
           categ=rs.getString(4);
                     
           }
      if(_username.equals(""+usntocompare+"") && _password.equals(pswtocompare)){
      HttpSession session=request.getSession();
      session.setAttribute("user", _username);
      session.setAttribute("eId", eId);
      if("director".equals(categ)){
      response.sendRedirect("cntrsupuser");
      }
      else{
    response.sendRedirect("Center");
      }
   //  RequestDispatcher rd=request.getRequestDispatcher("Center.html");
     //rd.include(request, response);
      
      } else{
          System.err.println("لقد قمت بادخال بيانات غير صحية");
          out.print("لقد قمت بادخال بيانات غير صحية");
     response.sendRedirect("http://yemwfsales-env.us-east-2.elasticbeanstalk.com/LoginCenter.html");
   
      }
     
      
        }catch(SQLException e){
            out.print(e);
        out.print("خطا في الاتصال");
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
