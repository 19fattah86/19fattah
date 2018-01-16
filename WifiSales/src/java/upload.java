/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import center.Db;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Ab-ps
 */
@WebServlet(urlPatterns = {"/upload"})
@MultipartConfig
public class upload extends HttpServlet {

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
          ServletContext cntxt = this.getServletContext();
            
         
           Connection conn=Db.connect();
           //String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
  //String sql2=("Truncate cards");
    //                PreparedStatement pr2=conn.prepareStatement(sql2);
      //              pr2.executeUpdate();
        //            pr2.close();
           Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
 //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
  //Reader in=new FileReader(fileName);
            // StringBuilder value = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "UTF-8"))) {
                // StringBuilder value = new StringBuilder();
                String line=null;
                int i=0;
                
                while ((line=reader.readLine())!=null){
                    String []value=line.split(",");
                   
                    String sql=("Insert Into cards (CardUsrName,CardPassWord,CardCategory,CardPrice,Expirty,CardStatus)"
                            + "Values('"+value[0]+"','"+value[1]+"','"+value[2]+"','"+value[3]+"','"+value[4]+"','"+value[5]+"')");
                    PreparedStatement pr=conn.prepareStatement(sql);
                    // pr.setString(1,"'"+value[0]+"'");
                    //   pr.setString(2,"'"+value[1]+"'");
                    //  pr.setString(3,"'"+value[2]+"'");
                    //    pr.setString(4,"'"+value[3]+"'");
                    //   pr.setString(5,"'"+value[4]+"'");
                    i =pr.executeUpdate();
                }
                if (i>0) {
                    out.print("تم التحميل بنجاح");
                } else{
                    out.print("تعذر في عملية التحميل");
                }
                //out.print(line);
            }
}       catch (SQLException ex) {
            Logger.getLogger(upload.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//InputStream ins = cntxt.getResourceAsStream(fileName);
    //try{
    // if (ins != null){
      //   FileInputStream fis =
        //new FileInputStream(request.getRealPath(fileName));
          //      InputStreamReader isr = new InputStreamReader(ins);
            //    BufferedReader br = new BufferedReader(isr);
              //  String line=null;
                //while ((line=br.readLine())!= null) {
                  // out.print(line);
                //}
           // }
    
   // } finally {
     //   out.close();
    //}
          //  InputStream fileContent = filePart.getInputStream();
            
                   // BufferedReader br = new BufferedReader(new FileReader(fileName));
              // String line=br.readLine();
               
                //while (line!=null){
                    //String []value=line.split(",");
                    //out.print("'"+value[0]+"'");}
                    
              //  }         
            
     //catch (SQLException ex) {
           // Logger.getLogger(upload.class.getName()).log(Level.SEVERE, null, ex);
       // }   //catch (SQLException ex) {
           // Logger.getLogger(upload.class.getName()).log(Level.SEVERE, null, ex);
       //}
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
