/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import center.Db;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 *
 * @author Ab-ps
 */
@WebServlet(urlPatterns = {"/usersignup"})
public class usersignup extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            try{
           Db db=new Db();
           Connection conn=db.connect();
           request.setCharacterEncoding("UTF-8");
                // String nm1 =request.getParameter("Txtname");
        String nm= request.getParameter("name");
        String tel= request.getParameter("telephon");
        String usenam= request.getParameter("username");
        String psw= request.getParameter("psw");
          
             String sqlCheckusn=("Select UserName From clients "
                     + "Where Binary UserName='"+usenam+"' "); 
             Statement checkst=conn.createStatement();
             ResultSet checkrs=checkst.executeQuery(sqlCheckusn);
             if(!checkrs.isBeforeFirst()){
        String command=("INSERT INTO clients(ClientName,ClientTelephone,UserName,password) "
                + "VALUES(?,?,?,?)") ;
                 PreparedStatement preparedStatement = conn.prepareStatement(command) ;
                    preparedStatement.setString(1, nm);
                    preparedStatement.setString(2, tel);
                    preparedStatement.setString(3, usenam);
                    preparedStatement.setString(4,psw);
                    int i =preparedStatement.executeUpdate();
                    if (i>0) {
                    out.print("<p>تم التسجيل بنجاح</p>");
                    out.print("<p><a href='http://yemwfsales-env.us-east-2.elasticbeanstalk.com/UserLogin.html'>تسجيل الدخول</a></p>");
                    
               }
                    else
                    {
                    out.print("لم تتم عملية التسجيل!");
                   }
            }
             else {
            out.print("لايمكنك استعمال اسم المستخدم هذا لانهتم استعماله من قبل مستخدم اخر"+usenam+"يرجى استخدام غيره");
             }
         conn.close();
            }catch(SQLException e){
                out.print("<p>"+e+"</p>");
               out.print("<p>هناك خطا في الاتصال في قاعدة البيانات</p>");
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
