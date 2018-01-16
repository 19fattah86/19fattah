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
@WebServlet(name = "userinterface", urlPatterns = {"/userinterface"})
public class userinterface extends HttpServlet {

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
            Connection conn=Db.connect();
                String sqlCateg=("Select Distinct CardCategory From cards");
                Statement ctst=conn.createStatement();
                ResultSet ctrs=ctst.executeQuery(sqlCateg);
            if (session!=null){
               String name=null;
               String id=null;
                try{
                
                
                String command =("Select Id, ClientName From clients Where UserName='"+session.getAttribute("user")+"'");
                Statement st=conn.createStatement();
              ResultSet rs=st.executeQuery(command);
              while (rs.next()){
              name=rs.getString(2);
              id=rs.getObject(1).toString();
              }
                } catch(SQLException E){
                    out.print(E);
                }
                out.print("<p><h2 style=\"text-align: center;\"> مرحبا "+name+"</h2></p>"
                        +"<P><h2 style=\"text-align: center;\">رقمك هو<span style=\"color:red;font-weight:bold\">"+id+"</span> </h2></P>"
                        + "<p><a href='userlogou'>تسجيل الخروج</a></p>");
                out.print("<p><a href='edituserlogdatainterface'>تعديل بيانات الدخول</a></p>");
            out.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        <title>شراء كرت</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>\n" +
"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
" <style>\n" +
"     footer {\n" +
"   \n" +
"    bottom:5%;\n" +
"    align-content: center;\n" +
"    \n" +
"    \n" +
"    width: 100%;\n" +
"}\n" +
"                 @media print {\n" +
"  body * {\n" +
"    visibility: hidden;\n" +
"  }\n" +
"  #reviewDiv * {\n" +
"    visibility: visible;\n" +
"  }\n" +
"  #reviewDiv {\n" +
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
                    "  <script>\n" +
"            \n" +
"            function CardCategDetail(){\n" +
"                \n" +
"     document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
" var categ=document.getElementById(\"STxtNam\").value;\n" +
" \n" +
"\n" +
"//var vinsurance=document.getElementById(\\\"\\\").value;\\n\n" +
"   var xhttp= new XMLHttpRequest();\n" +
"var parmtrs=\"STxtNam=\"+categ+\"\";\n" +
"   xhttp.onreadystatechange = function (){\n" +
"if(this.readyState==4&& this.status==200){\n" +
"    document.getElementById(\"reviewDiv\").style.display=\"block\";\n" +
" document.getElementById(\"reviewDiv\").innerHTML=this.responseText;\n" +
"\n" +
"\n" +
"       }\n" +
"};\n" +
"xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/cartcategdetail\",false);\n" +
"  xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"xhttp.send(parmtrs); \n" +
"                \n" +
"            }\n" +
"        </script>"+
                    " <script> \n" +
"              function showClProDiv(){\n" +
"                document.getElementById(\"BuyCardDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"KnowBdiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SearcCardDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"ClintProcedures\").style.display=\"inline-block\";\n" +
"      }\n" +
"        function ClProcedures(){\n" +
"  document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
" var datfr=document.getElementById(\"datefrom\").value;\n" +
" var dateto=document.getElementById(\"dateto\").value;\n" +
"\n" +
"//var vinsurance=document.getElementById(\\\"\\\").value;\\n\n" +
"   var xhttp= new XMLHttpRequest();\n" +
"var parmtrs=\"datefrom=\"+datfr+\"&dateto=\"+dateto+\"\";\n" +
"   xhttp.onreadystatechange = function (){\n" +
"if(this.readyState==4&& this.status==200){\n" +
"    document.getElementById(\"reviewDiv\").style.display=\"block\";\n" +
" document.getElementById(\"reviewDiv\").innerHTML=this.responseText;\n" +
"\n" +
"\n" +
"       }\n" +
"};\n" +
"xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/clientprocerure\",false);\n" +
"  xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"xhttp.send(parmtrs);\n" +
"}\n" +
"        \n" +
"        </script>"+
"        <script>\n" +
"              function showsEARCHCaRdDiv(){\n" +
"                document.getElementById(\"BuyCardDiv\").style.display=\"none\"\n" +
"                 document.getElementById(\"KnowBdiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SearcCardDiv\").style.display=\"block\";\n" +
"                  document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
                    " document.getElementById(\"ClintProcedures\").style.display=\"none\";"+
"            }\n" +
"            function showBuyCaedDiv(){\n" +
"                document.getElementById(\"BuyCardDiv\").style.display=\"inline-block\"\n" +
"                 document.getElementById(\"KnowBdiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SearcCardDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
                    " document.getElementById(\"ClintProcedures\").style.display=\"none\";"+
"\n" +
"            }\n" +
"            function searchCardByDate(){\n" +
"                document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
"                 var http= new XMLHttpRequest();\n" +
"   // var quant=document.getElementById(\"Cardquant\").value;\n" +
"    var dat=document.getElementById(\"buyingDatCard\").value;\n" +
"     var parmtrs=\"buyingDatCard=\"+dat+\"\";\n" +
"    http.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"           document.getElementById(\"reviewDiv\").style.display=\"block\";\n" +
"          document.getElementById(\"reviewDiv\").innerHTML=this.responseText;\n" +
"         //   var alrt=this.responseText;\n" +
"           // alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"       http.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/searccardbydate\",false);\n" +
"    http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     http.send(parmtrs);\n" +
"            }\n" +
"            function knowBalance(){\n" +
"              document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
"              document.getElementById(\"KnowBdiv\").style.display=\"none\";\n" +
"               document.getElementById(\"BuyCardDiv\").style.display=\"none\"\n" +
"                document.getElementById(\"SearcCardDiv\").style.display=\"none\";\n" +
                    " document.getElementById(\"ClintProcedures\").style.display=\"none\";"+
"                 var http= new XMLHttpRequest();\n" +
"   // var quant=document.getElementById(\"Cardquant\").value;\n" +
"    \n" +
"    http.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"           document.getElementById(\"KnowBdiv\").style.display=\"block\";\n" +
"          document.getElementById(\"KnowBdiv\").innerHTML=this.responseText; \n" +
"      }  \n" +
"    };\n" +
"       http.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/knowbalance\",false);\n" +
"    http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     http.send();\n" +
"            \n" +
"        }\n" +
"            function buyCard(){\n" +
"                document.getElementById(\"reviewDiv\").style.display=\"none\";\n" +
"                 var http= new XMLHttpRequest();\n" +
"   // var quant=document.getElementById(\"Cardquant\").value;\n" +
"    var categ=document.getElementById(\"CardCategory\").value;\n" +
"     var parmtrs=\"CardCategory=\"+categ+\"\";\n" +
"    http.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"           document.getElementById(\"reviewDiv\").style.display=\"block\";\n" +
"          document.getElementById(\"reviewDiv\").innerHTML=this.responseText;\n" +
"         //   var alrt=this.responseText;\n" +
"           // alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"       http.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/buycards\",false);\n" +
"    http.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     http.send(parmtrs);\n" +
" }\n" +
"            \n" +
"        </script>  \n" +
"         <div  id=\"headDiv\" style=\"width: 100%; height: 80px; background-color:\n" +
"              blue;display: block; text-align: center\">\n" +
"            <h1 style=\"font-family:verdana; color: white\">العاصمة وايفاي</h1>\n" +
"            <h4 style=\"font-family:verdana; color: white\">7xxxxxxxx:للتواصل مع الشبكة عبر الارقام التالية </h4>\n" +
"        </div>\n" +
"        <div id=\"container\" style=\"min-height: 600px;\">\n" +
"            <div id=\"menueDiv\" class=\"menu\" style=\"display:inline-block;float: rjght;width: 14%;\n" +
"                 height: 100%;background-color: deeppink;margin: auto; text-align: center\" >\n" +
"                       <div class=\"dropdown\" style=\"float:right;width: 100%;\">\n" +
"                         <button class=\"dropbtn\" >القائمة</button>\n" +
"                         <div class=\"dropdown-content\" style=\"width: 200px;\">\n" +
"                           \n" +
"                             <button id=\"bBuyCard\"  class=\"b1\"\n" +
"                                     onclick=\"showBuyCaedDiv()\">شراء كرت</button>\n" +
"                                                      \n" +
"                                 <button id=\"KnowBalance\"  class=\"b1\"\n" +
"                                     onclick=\"knowBalance()\"> الاستعلام عن الرصيد</button>\n" +
"                                 <button id=\"Cards\"  class=\"b1\"\n" +
"                                     onclick=\"showsEARCHCaRdDiv()\"> كروتك</button>\n" +
                    "                                 <button id=\"Cards\"  class=\"b1\"\n" +
"                                     onclick=\"showClProDiv()\"> تقريرك</button>\n" +
"                                                                                      \n" +
"                                  </div>    \n" +
"                </div> \n" +
"            </div>\n" +
"                  \n" +
"          <div id=\"pageDiv\" style=\"display: inline-block;float: left;width: 86%;\n" +
"               background: aliceblue;height: 100%; margin: auto\">\n" +
"              <div id=\"BuyCardDiv\" style=\"display: none\">\n" +
                    "<p><span style=\"color:blue;font-weight:bold\">شراءكرت</span></p>"+
                    "<form action=\"javascript:;\"onsubmit=\"buyCard(this)\" method=\"POST\" style=\"border:1px solid #ccc\">"+
"                  <table>\n" +
"                      <tr>\n" +
"                   \n" +
"                          <td>\n" +
                    "<input type=\"text\" name=\"STxtNam\" id=\"STxtNam\"  list=\"selectCardCategory\" onchange=\"CardCategDetail()\""
                    + " placeholder=\"اختر الفئة من هنا\" id=\"CardCategory\" required>"+
 "<datalist id=\"selectCardCategory\">\n");
                    while(ctrs.next()){
out.println("<option value=\""+ctrs.getObject(1).toString()+"\">"+ctrs.getObject(1).toString()+"</option>\n");

        }  
out.println("</datalist>\n" +
"                          </td>\n" +
"                          <td>\n" +
"                              <input type=\"submit\" id=\"Buybt\" style=\"width: 150px;height: 30px\" value=\"تاكيد الشراء\">\n" +
"                          </td>\n" +
"                  </tr>\n" +
"                  </table>\n" +
        "</form>"+
"              </div>\n" +
"              <div id=\"KnowBdiv\" style=\"display: none\">\n" +
                    "<p><span style=\"color:blue;font-weight:bold\">معرفة الرصيد</span></p>"+
"                  \n" +
"              </div>\n" +
"              <div id=\"SearcCardDiv\" style=\"display: none\">\n" +
                    "<p><span style=\"color:blue;font-weight:bold\">البحث عن الكروت</span></p>"+
"                  <form method=\"post\">\n" +
"                      <input type=\"date\" id=\"buyingDatCard\" style=\"width: 200px\" onchange=\"searchCardByDate()\">\n" +
"                  </form>\n" +
"              </div>\n" +
        "<div id=\"ClintProcedures\" style=\"width: 52%; background: antiquewhite;display: none\">\n" +
        "  <table>\n" +
"                <tr>\n" +
"                    <td style=\"width: 300px\">التاريخ من </td>\n" +
"                    <td style=\"width: 300px\">التاريخ الى</td>\n" +
"                </tr>\n" +
"            </table>   "+
"        <form action=\"javascript:;\"onsubmit=\"ClProcedures(this)\" method=\"POST\" style=\"border:1px solid #ccc\">\n" +
"            <table>\n" +
"                <tr>\n" +
"                    <td> <input type=\"date\" id=\"datefrom\" name=\"datefrom\"\n" +
"                                onchange=\"ClProcedures()\"  style=\"height: 30px;width: 200px\"\n" +
"                                required></td> <td>اختر التاريخ من هنا</td>\n" +
"                    <td><input type=\"date\" id=\"dateto\" name=\"dateto\" \n" +
"                               onchange=\"ClProcedures()\"  style=\"height: 30px;width: 200px\" \n" +
"                               required></td><td>اختر التاريخ من هنا</td>\n" +
"                </tr>\n" +
"                \n" +
"            </table>\n" +
"            <table><tr><td><input type=\"submit\" value=\"ابحث\" \n" +
"                                  style=\"height: 30px;width: 200px\"></td></tr></table>\n" +
"        </form>\n" +
"        </div>"+
"              <div id=\"reviewDiv\" style=\"display: block;float: left;width: 80%;\">\n" +
"                  \n" +
"              </div>\n" +
"          </div>\n" +
"        </div>\n" +
"         <center><footer style=\"margin: 0 auto\">\n" +
"            <table>\n" +
"                 <tr>\n" +
"                     <td><h3>736108228/736108228</h3></td>\n" +
"                    <td> <h3> <a target=\"_blank\" href=\"https://www.facebook.com/abdulfattah.ali.5\">د.عبدالفتاح عبادي</a></h3></td>\n" +
"                    <td><h3>تصميم وتطوير</h3></td>\n" +
"                    </tr>\n" +
"                \n" +
"            </table>\n" +
"        </footer></center>\n" +
"    </body>\n" +
"</html>\n" +
"");
    } else{
            out.print("يجب عليك الدخول اولا "+"<a href='UserLogin.html'>تسجيل الدخول </a>");
            }         
        } catch (SQLException ex) {
            Logger.getLogger(userinterface.class.getName()).log(Level.SEVERE, null, ex);
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
