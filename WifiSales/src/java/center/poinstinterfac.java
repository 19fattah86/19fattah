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
@WebServlet(name = "poinstinterfac", urlPatterns = {"/poinstinterfac"})
public class poinstinterfac extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (session!=null){
                String name=null;
                String id=null;
                try{
                Connection conn=Db.connect();
                String command =("Select id, EmplName From pointsemployees Where EmplUserName='"+session.getAttribute("user")+"'");
                Statement st=conn.createStatement();
              ResultSet rs=st.executeQuery(command);
              while (rs.next()){
               id=rs.getString(1);
              name=rs.getString(2);
              }
                } catch(SQLException E){
                    out.print(E);
                }
                 out.print("<p><h2 style=\"text-align: center;\">مرحبا "+name+" </h2></p>");
                            out.print("<p><h2 style=\"text-align: center;\"> رقمك  هو"+id+"</h2></p>"
                        + "<p><a href='pointlogout'>تسجيل الخروج</a></p>");
                out.print("<p><a href='pointeditlogindatauser'>تعديل بيانات الدخول</a></p>");
            out.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        <title>Charge Balance</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"          <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>\n" +
"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
"        <style>\n" +
"           \n" +
"            footer {\n" +
" \n" +
"    bottom:5%;\n" +
"    align-content: center;\n" +
"    \n" +
"    \n" +
"    width: 100%;\n" +
"}\n" +
"                         @media print {\n" +
"  body * {\n" +
"    visibility: hidden;\n" +
"  }\n" +
"  #resultRepDiv * {\n" +
"    visibility: visible;\n" +
"  }\n" +
"  #resultRepDiv {\n" +
"    position: absolute;\n" +
"    left: 0;\n" +
"    top: 0;\n" +
"  }\n" +
"}\n" +
" \n" +
"            div.displaynone{\n" +
"               display: none;\n" +
"            }\n" +
".dropbtn {\n" +
"    background-color: #4CAF50;\n" +
"    color: white;\n" +
"    padding: 16px;\n" +
"    font-size: 16px;\n" +
"    border: none;\n" +
"    cursor: pointer;\n" +
"    width: 200px;\n" +
"}\n" +
"\n" +
".dropdown {\n" +
"    position: relative;\n" +
"    display: inline-block;\n" +
                    
"}\n" +
"\n" +
".dropdown-content {\n" +
"    display: none;\n" +
"    position: absolute;\n" +
"    right: 0;\n" +
"    background-color: #f9f9f9;\n" +
"    min-width: 160px;\n" +
"    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n" +
"    z-index: 1;\n" +
"}\n" +
"\n" +
".dropdown-content a {\n" +
"    color: black;\n" +
"    padding: 12px 16px;\n" +
"    text-decoration: none;\n" +
"    display: block;\n" +
"}\n" +
"\n" +
".dropdown-content a:hover {background-color: #f1f1f1}\n" +
"\n" +
".dropdown:hover .dropdown-content {\n" +
"    display: block;\n" +
"}\n" +
"\n" +
".dropdown:hover .dropbtn {\n" +
"    background-color: #3e8e41;\n" +
"}\n" +
" div.menu {\n" +
"            \n" +
"        }\n" +
"        button.b1 {\n" +
"        \n" +
"             display: block;\n" +
"             width: 200px;\n" +
"             height: 50px;\n" +
"             background: aqua;\n" +
"        }\n" +
"</style>\n" +
"    </head>\n" +
"    <body>\n" +
"        <script>\n" +
"               function showReportDiv(){\n" +
"                document.getElementById(\"ChargBdiv\").style.display=\"none\";\n" +
"                document.getElementById(\"PReportDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"inline-block\";\n" +
"                 document.getElementById(\"currPSales\").style.display=\"none\";\n" +
"                document.getElementById(\"currPReven\").style.display=\"none\";\n" +
"                            }\n" +
"                            function showRCPSaleDiv(){\n" +
"                document.getElementById(\"ChargBdiv\").style.display=\"none\";\n" +
"                document.getElementById(\"PReportDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"currPSales\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"currPReven\").style.display=\"none\";\n" +
"                 document.getElementById(\"resultRepDiv\").style.display=\"none\";\n" +
"                            }\n" +
"                            function showRCPRevDiv(){\n" +
"                document.getElementById(\"ChargBdiv\").style.display=\"none\";\n" +
"                document.getElementById(\"PReportDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"currPSales\").style.display=\"none\";\n" +
"                document.getElementById(\"currPReven\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"none\";\n" +
"                            }\n" +
"                            function ChargeDivshow(){\n" +
"                                document.getElementById(\"ChargBdiv\").style.display=\"inline-block\";\n" +
"                                 document.getElementById(\"PReportDiv\").style.display=\"none\";\n" +
"                                 document.getElementById(\"resultRepDiv\").style.display=\"none\";\n" +
"                            }\n" +
"            function BalancCharge(){\n" +
"                var http= new XMLHttpRequest();\n" +
"    var cid=document.getElementById(\"cid\").value;\n" +
"    var balancetocharge=document.getElementById(\"amountofBalance\").value;\n" +
"     var parmtrs=\"cid=\"+cid+\"&amountofBalance=\"+balancetocharge+\"\";\n" +
"    http.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         // document.getElementById(\"PReportDiv\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"       http.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/charge\",false);\n" +
"    http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     http.send(parmtrs);\n" +
"            }\n" +
"            function currentPointReport(){\n" +
"                var http= new XMLHttpRequest();\n" +
"   // var datFr=document.getElementById(\"datFrom\").value;\n" +
"    //var datTo=document.getElementById(\"datTo\").value;\n" +
"            //var parmtrs=\"selectClientId=\"+datFr+\"&amountofBalance=\"+datTo+\"\";\n" +
"    http.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"            document.getElementById(\"ChargBdiv\").style.display=\"none\";\n" +
"                document.getElementById(\"PReportDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"inline-block\";\n" +
"                 document.getElementById(\"currPSales\").style.display=\"none\";\n" +
"                document.getElementById(\"currPReven\").style.display=\"none\";\n" +
"        document.getElementById(\"resultRepDiv\").innerHTML=this.responseText;\n" +
"            //var alrt=this.responseText;\n" +
"            //alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"       http.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/currentpoinreport\",false);\n" +
"    http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     http.send(); \n" +
"            }\n" +
"            function currentpointSales(){\n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"none\";\n" +
"                 var http= new XMLHttpRequest();\n" +
"  var datFr=document.getElementById(\"SaledatFrom\").value;\n" +
"    var datTo=document.getElementById(\"SaledatTo\").value;\n" +
"           var parmtrs=\"SaledatFrom=\"+datFr+\"&SaledatTo=\"+datTo+\"\";\n" +
"    http.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"            \n" +
"                \n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"block\";\n" +
"        document.getElementById(\"resultRepDiv\").innerHTML=this.responseText;\n" +
"            //var alrt=this.responseText;\n" +
"            //alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"       http.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/currentpoinsales\",false);\n" +
"    http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     http.send(parmtrs); \n" +
"            }\n" +
"            function currentpointRevengue(){\n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"none\";\n" +
"                 var http= new XMLHttpRequest();\n" +
"  var datFr=document.getElementById(\"RevdatFrom\").value;\n" +
"    var datTo=document.getElementById(\"RevdatTo\").value;\n" +
"           var parmtrs=\"RevdatFrom=\"+datFr+\"&RevdatTo=\"+datTo+\"\";\n" +
"    http.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"            \n" +
"                \n" +
"                document.getElementById(\"resultRepDiv\").style.display=\"block\";\n" +
"        document.getElementById(\"resultRepDiv\").innerHTML=this.responseText;\n" +
"            //var alrt=this.responseText;\n" +
"            //alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"       http.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/currentpointrevengue\",false);\n" +
"    http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     http.send(parmtrs); \n" +
"            }\n" +
"        </script>\n" +
"        \n" +
"         \n" +
"         <div  id=\"headDiv\" style=\"width: 100%; height: 80px; background-color:\n" +
"              blue;display: block; text-align: center\">\n" +
"            <h1 style=\"font-family:verdana; color: white\">العاصمة وايفاي</h1>\n" +
"            <h4 style=\"font-family:verdana; color: white\">7xxxxxxxx:للتواصل مع الشبكة عبر الارقام التالية </h4>\n" +
"        </div>\n" +
"        <div style=\"min-height: 600px;\">\n" +
"         <div id=\"menueDiv\" class=\"menu\" style=\"display:inline-block;float: right;width: 14%;\n" +
"                    height: 100%;background-color: deeppink;margin: auto; text-align: center\" >\n" +
"                     <div class=\"dropdown\" style=\"float:right;width:100%\">\n" +
"                         <button class=\"dropbtn\" >القائمة</button>\n" +
"                         <div class=\"dropdown-content\" style=\"width: 200px;\">\n" +
"                           \n" +
"                             <button id=\"bCharg\"  class=\"b1\"\n" +
"                                     onclick=\"ChargeDivshow()\">تعبئة رصيد</button>\n" +
"                                                      \n" +
"                                 <button id=\"pointSalebt\"  class=\"b1\"\n" +
"                                     onclick=\"showRCPSaleDiv()\"> مبيعاتك</button>\n" +
"                                 <button id=\"pointRevenbt\"  class=\"b1\"\n" +
"                                     onclick=\"showRCPRevDiv()\"> ايراداتك</button>\n" +
"                                 <button id=\"pointumReportbt\"  class=\"b1\"\n" +
"                                     onclick=\"currentPointReport()\"> تقرير نهائي</button>\n" +
"                                                          \n" +
"                                  </div>    \n" +
"                </div>\n" +
"                </div>\n" +
"          <div id=\"pageDiv\" style=\"display: inline-block;float: left;width: 86%;min-height: 600px\n" +
"              background: aliceblue;height: 100%; margin: auto\">\n");
           try{ request.setCharacterEncoding("UTF-8");
                Db db=new Db();
        Connection conn=db.connect();
                String command=("SELECT Id FROM clients");
                Statement stmnts=conn.createStatement();
                 ResultSet rs=stmnts.executeQuery(command);
out.println(" <div id=\"ChargBdiv\" class=\"displaynone\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">شحن رصيد</span></p>"+
 "<form  action=\"javascript:;\"onsubmit=\"BalancCharge(this)\" method=\"POST\">\n" +
"               <table>\n" +
"                    \n" +
"                      <tr>\n" +
"                           \n" +
"                          <td> \n" +
"                              \n" +
                          
"                                \n" +
"                   <input type=\"text\" name=\"cid\" list=\"selectClientId\" id=\"cid\" "
        + "placeholder=\"رقم العميل\" required>\n" +
"<datalist id=\"selectClientId\" >\n");
        while(rs.next()){
out.println("<option value=\""+rs.getObject(1).toString()+"\">"+rs.getObject(1).toString()+"</option>\n");

        } } catch(SQLException e)
        {
        out.print(e);
        }
out.println("                                      </datalist>\n" +
"                              </td> \n" +
"                              <td>:اختر رقم العميل من هنا</td>\n" +
"                              <td>\n" +
"                                  \n" +
"                                      <input id=\"amountofBalance\" type=\"text\""
        + "placeholder=\"المبلغ\"  required>\n" +
"                                  \n" +
"                              </td>\n" +
"                               <td>:ادخل المبلغ المراد تعبئته هنا</td>\n" +
"                      </tr>\n" +
"                      </table>\n" +
"                      <table>\n" +
"                      <tr>\n" +
"                          <td>\n" +
"       <input type=\"submit\" id=\"bChargeBalance\" style=\"width: 150px;height: 30px;\" value=\" تأكسد التعبئة\">\n" +
"                          </td>\n" +
"                      </tr>\n" +
"                      </table>\n" +
        "</form>"+
"                     \n" +
"              </div>\n" +
"                 <div id=\"PReportDiv\" >\n" +
"                  <div id=\"currPSales\" style=\"display: none;\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">المبيعات</span></p>"+
"                      <table>\n" +
"                      <tr>\n" +
"                          <td>\n" +
"                              <form method=\"post\">\n" +
"                                  <input id=\"SaledatFrom\" type=\"date\" onchange=\"currentpointSales()\">\n" +
"                              </form>\n" +
"                               </td>\n" +
"                          <td>:اختار التاريخ من </td>\n" +
"                          <td><form>\n" +
"                                  <input id=\"SaledatTo\" type=\"date\" onchange=\"currentpointSales()\">\n" +
"                              </form> </td>\n" +
"                              <td>:اختر التاريخ الى</td>\n" +
"                      </tr>\n" +
"                  </table>\n" +
"                  </div>\n" +
"                  <div id=\"currPReven\" style=\"display: none;\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">الايرادات</span></p>"+
"                      <table>\n" +
"                      <tr>\n" +
"                          <td>\n" +
"                              <form method=\"post\">\n" +
"                                  <input id=\"RevdatFrom\" type=\"date\" onchange=\"currentpointRevengue()\">\n" +
"                              </form>\n" +
"                               </td>\n" +
"                          <td>:اختار التاريخ من </td>\n" +
"                          <td><form>\n" +
"                                  <input id=\"RevdatTo\" type=\"date\" onchange=\"currentpointRevengue()\">\n" +
"                              </form> </td>\n" +
"                              <td>:اختر التاريخ الى</td>\n" +
"                      </tr>\n" +
"                  </table>\n" +
"                  </div>\n" +
"                 \n" +
"                  <div id=\"resultRepDiv\"></div>\n" +
"              </div>\n" +
"          </div>\n" +
"        </div>\n" +
"               <center><footer style=\"margin: 0 auto\">\n" +
"            <table>\n" +
"                 <tr>\n" +
"                     <td><h3>736108228/+79376282411</h3></td>\n" +
"                    <td> <h3> <a target=\"_blank\" href=\"https://www.facebook.com/abdulfattah.ali.5\">د.عبدالفتاح عبادي</a></h3></td>\n" +
"                    <td><h3>تصميم وتطوير</h3></td>\n" +
"                    </tr>\n" +
"                \n" +
"            </table>\n" +
"        </footer></center>\n" +
"                </body>\n" +
"</html>\n" +
"");
          } else{
            out.print("يجب عليك الدخول اولا "+"<a href='PointLogin.html'>تسجيل الدخول </a>");
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
