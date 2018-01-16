/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import center.Db;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ab-ps
 */
@WebServlet(urlPatterns = {"/edituserlogdatainterface"})
public class edituserlogdatainterface extends HttpServlet {

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
        HttpSession session=request.getSession(false);
        String cid=(String) session.getAttribute("user");
        try (PrintWriter out = response.getWriter()) {
            Connection conn=Db.connect();
            String usnm=null;
            String sqledtusdt=("Select UserName From clients Where Id='"+cid+"'");
            Statement stm=conn.createStatement();
            ResultSet rs=stm.executeQuery(sqledtusdt);
            while(rs.next()) {
            usnm=rs.getObject(1).toString();
            }
            out.println("  <datalist id=\"selectClientId\">\n" +
"                    <option value=\""+usnm+"\">"+usnm+"</option>    \n" +
"            </datalist>");
            out.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        <title>تعديل بيانات الدخول للمستخدمين</title>\n" +
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
"       width: 50%;\n" +
"    }\n" +
"}\n" +
"        </style>\n" +
"    </head>\n" +
"    <body>\n" +
                    "<script> \n" +
"     function EditClLogData(){\n" +
"  \n" +
" var emunam=document.getElementById(\"username\").value;\n" +
" var empsw=document.getElementById(\"psw\").value;\n" +
"\n" +
"//var vinsurance=document.getElementById(\\\"\\\").value;\\n\n" +
"   var xhttp= new XMLHttpRequest();\n" +
"var parmtrs=\"username=\"+emunam+\"&psw=\"+empsw+\"\";\n" +
"   xhttp.onreadystatechange = function (){\n" +
"if(this.readyState==4&& this.status==200){\n" +
"// document.getElementById(\\\"divTest\\\").innerHTML=this.responseText;\n" +
"\n" +
"var servText=this.responseText;\n" +
"            if (servText==\"تم التعديل بنجاح\"){\n" +
"               \n" +
"             window.location=\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/userlogou\";   \n" +
"             \n" +
"            } else {\n" +
"          alert(servText);\n" +
"            }\n" +
"       }\n" +
"};\n" +
"xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/edtuserdata\",false);\n" +
"  xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"xhttp.send(parmtrs);\n" +
"}\n" +
"        \n" +
"        </script>"+
"       \n" +
"<h2>تعديل بيانات الدخول</h2>\n" +
"\n" +
"<form action=\"javascript:;\"onsubmit=\"EditClLogData(this)\" method=\"POST\" style=\"border:1px solid #ccc\">\n" +
"  <div class=\"container\">\n" +
"    <label><b>ادخل اسم المستخدم الجديد او اختر القديم من هنا</b></label>\n" +
"    <input type=\"text\" list=\"selectClientId\" placeholder=\"ادخل اسم المستخدم حروف لاتينية\" "
                    + "name=\"username\" id=\"username\" required>\n" +
"     <label><b>كلمة السر الجديدة</b></label>\n" +
"    <input type=\"password\" placeholder=\"ادخل كلمة السر حروف لاتينية\" name=\"psw\" id=\"psw\" required>\n" +
"    <input type=\"checkbox\" checked=\"checked\"> تذكرني\n" +
"    <p>بتسجيلك فان توافق على الشروط والخصوصية <a href=\"#\">الشروط والخصوصية</a>.</p>\n" +
"\n" +
"    <div class=\"clearfix\">\n" +
"     \n" +
"      <button type=\"submit\" class=\"signupbtn\">تعديل</button>\n" +
"    </div>\n" +
"  </div>\n" +
"</form>\n" +
"    </body>\n" +
"</html>\n" +
"");
        } catch (SQLException ex) {
            Logger.getLogger(edituserlogdatainterface.class.getName()).log(Level.SEVERE, null, ex);
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
