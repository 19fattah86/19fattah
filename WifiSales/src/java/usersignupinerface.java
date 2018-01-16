/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ab-ps
 */
@WebServlet(urlPatterns = {"/usersignupinerface"})
public class usersignupinerface extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        <title>التسجيل المستخدمين الجدد</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <style>\n" +
"            input[type=text], input[type=password]{\n" +
"                width: 100%;\n" +
"    padding: 12px 20px;\n" +
"    margin: 8px 0;\n" +
"    display: inline-block;\n" +
"    border: 1px solid #ccc;\n" +
"    box-sizing: border-box;\n" +
"            }\n" +
"            button {\n" +
"    background-color: #4CAF50;\n" +
"    color: white;\n" +
"    padding: 14px 20px;\n" +
"    margin: 8px 0;\n" +
"    border: none;\n" +
"    cursor: pointer;\n" +
"    width: 100%;\n" +
"}\n" +
".cancelbtn {\n" +
"    padding: 14px 20px;\n" +
"    background-color: #f44336;\n" +
"}\n" +
".cancelbtn,.signupbtn {\n" +
"    float: left;\n" +
"    width: 50%;\n" +
"}\n" +
".container {\n" +
"    padding: 16px;\n" +
"}\n" +
".clearfix::after {\n" +
"    content: \"\";\n" +
"    clear: both;\n" +
"    display: table;\n" +
"}\n" +
"@media screen and (max-width: 300px) {\n" +
"    .cancelbtn, .signupbtn {\n" +
"       width: 100%;\n" +
"    }\n" +
"}\n" +
"        </style>\n" +
"    </head>\n" +
"    <body>\n" +
"       \n" +
"<h2>التسجيل</h2>\n" +
"\n" +
"<form action=\"usersignup\" method=\"POST\" style=\"border:1px solid #ccc\">\n" +
"  <div class=\"container\">\n" +
"    <label><b>الاسم</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل اسمك الرباعي\" "
                    + "name=\"name\" required>\n" +
                    "    <label><b>رقم الهاتف</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل رقم هاتفك\" "
                    + "name=\"telephon\" required>\n" +
                    "    <label><b>اسم المستخدم</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل اسم المستخدم حروف لاتينية\" "
                    + "name=\"username\" required>\n" +
"\n" +
"    <label><b>كلمة السر</b></label>\n" +
"    <input type=\"password\" placeholder=\"ادخل كلمة السر حروف لاتينية\""
                    + " name=\"psw\" required>\n" +
"\n" +
"    <input type=\"checkbox\" checked=\"checked\"> تذكرني\n" +
"    <p>بتسجيلك فان توافق على الشروط والخصوصية <a href=\"#\">الشروط والخصوصية</a>.</p>\n" +
"\n" +
"    <div class=\"clearfix\">\n" +

"      <button type=\"submit\" class=\"signupbtn\">تسجيل</button>\n" +
"    </div>\n" +
"  </div>\n" +
"</form>\n" +
"    </body>\n" +
"</html>\n" +
"");
            
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
