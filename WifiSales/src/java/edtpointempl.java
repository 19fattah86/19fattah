/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import center.Db;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ab-ps
 */
@WebServlet(urlPatterns = {"/edtpointempl"})
public class edtpointempl extends HttpServlet {

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
        String path=request.getServletPath();
        try (PrintWriter out = response.getWriter()) {
             switch(path){
            
                case "/edtpointempl":
                    adtpemployee(request, out);
                        
            }
          
        }
    }
    public void adtpemployee(HttpServletRequest request, PrintWriter out){
     try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PointSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           try {
         String eid=request.getParameter("idEd");
        String nm= request.getParameter("nameEd");
        String nr= request.getParameter("telephonEd");
        String pasId= request.getParameter("passportEd");
        String ad= request.getParameter("adressEd");
         String usn= request.getParameter("usernameEd");
          String psw= request.getParameter("pswEd");
               
               Connection conn=Db.connect();
                String sqlCheckusn=("Select EmplUserName From pointsemployees "
                     + "Where Binary EmplUserName='"+usn+"' "); 
             Statement checkst=conn.createStatement();
             ResultSet checkrs=checkst.executeQuery(sqlCheckusn);
             if(!checkrs.isBeforeFirst()){
               String command=("UPDATE pointsemployees SET EmplName='"+nm+"',"
             + "EmplNumber='"+nr+"',PassIdCardNo='"+pasId+"',Adress='"+ad+"',EmplUserName='"+usn+"',EmplPassword='"+psw+"' "
                       + "WHERE Id='"+eid+"'");
              PreparedStatement pstmnts =conn.prepareStatement(command);
              // pstmnts.setString(1, nm);
               //pstmnts.setString(2, nr);
               //pstmnts.setString(3, ad);
               //pstmnts.setString(4, amnt);
               //pstmnts.setString(5, nr);
               int i =pstmnts.executeUpdate();
                    if (i>0) {
                    out.print("!تم التعديل بنجاح");
                    
               }
                    else
                    {
                    out.print("!لم بتم التعديل");
                   }
           }
             else {
             out.print("لايمكنك استعمال اسم المستخدم هذا لانهتم استعماله من قبل مستخدم اخر يرجى استخدام غيره");
             }
           } catch (SQLException e) {
               out.print(e);
                out.print("لم تتم العملية");
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
