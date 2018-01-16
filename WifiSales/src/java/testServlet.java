/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import center.Db;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.Date;
import java.time.LocalDate;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ab-ps
 */
@WebServlet(urlPatterns = {"/returnvalue", "/sumreport",
     "/fromtoreport", "/todayreport", "/srchpointrevengue",
    "/topointinerface", "/charge", "/currentpoinreport",
    "/currentpoinsales", "/currentpointrevengue", "/buycards",
    "/knowbalance", "/searccardbydate", "/loaddata", "/sumreportbynam","/cartcategdetail"})
public class testServlet extends HttpServlet {

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
        

        String path = request.getServletPath();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            switch (path) {
                case "/search":
                    select(request, out);
                    break;
                case "/returnvalue":
                    returnbalance(request, out);
                    break;
                case "/sumreport":
                    RemBalance(request, out);
                    break;
                case "/sumreportbynam":
                    SumReportByNam(request, out);
                    break;
                case "/fromtoreport":
                    FromToReport(request, out);
                    break;
                case "/todayreport":
                    TodayReport(request, out);
                    break;
                case "/srchpointrevengue":
                    SrchRevengue(request, out);
                    break;
                case "/topointinerface":
                    toPInterface(request, out);
                    break;
                case "/charge":
                    charge(request, out);
                    break;
                case "/currentpoinreport":
                    currenpointReport(request, out);
                    break;
                case "/currentpoinsales":
                    currenpointSales(request, out);
                    break;
                case "/currentpointrevengue":
                    currentpointRevengue(request, out);
                    break;
                case "/buycards":
                    BuyCards(request, out);
                    break;
                case "/knowbalance":
                    KnowBalance(request, out);
                    break;
                case "/searccardbydate":
                    SearchCardByDate(request, out);
                    break;
                
                case "/cartcategdetail":
                    CardCategDetails(request,out);
                    break;
                default:
                    break;
            }

        } 
    }

    public void CardCategDetails(HttpServletRequest request, PrintWriter out) {
try {
            request.setCharacterEncoding("UTF-8");
            Connection conn = Db.connect();

            //   DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String categ = request.getParameter("STxtNam");
            String command = ("Select CardPrice, Expirty From cards "
                    + "Where CardCategory ='"+categ+"' LIMIT 1");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(command);
            out.print("!:بيانت الكروت من هذه الفئة");
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    
                    + "                    <th style=\"width:100px; border: 1px solid black;\">السعر</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">فترة الصلاحية</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            while (rs.next()) {
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getString(2));
                out.println("</td>");
                 out.println("</tr>");
                out.print("</table>");
            }
        } catch (SQLException e) {
            out.print(e);
            out.print("!يوجد خطا في الاتصال بقاعدة البيانات");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SearchCardByDate(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(false);

            String clId = (String) session.getAttribute("uId");
            
            Connection conn = Db.connect();

            //   DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dat = request.getParameter("buyingDatCard");
            String command = ("Select CardUserName, CardPassword, CardCategory,Expirty From clientscards "
                    + "Where ClientId =" + clId + " AND SaleDate='" + dat + "'");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(command);
            out.print("!:بيانات كروتك هي كالتالي");
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">اسم المستخدم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم السري</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الفئة</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">فترة الصلاحية</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            while (rs.next()) {
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getString(2));
                out.println("</td>");
                 out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getString(3));
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getString(4));
                out.println("</td>");
                out.println("</tr>");
                out.print("</table>");
            }
        } catch (SQLException e) {
            out.print(e);
            out.print("!يوجد خطا في الاتصال بقاعدة البيانات");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void KnowBalance(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(false);
            String clId = (String) session.getAttribute("uId");
            Db db = new Db();
            Connection conn = db.connect();
            String command = ("Select ClientBalance From clients Where id =" + clId + "");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(command);
            while (rs.next()) {
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(":رصيدك الحالي هو");
                out.println("</td>");
                out.println("</tr>");
                out.print("</table>");
            }
        } catch (SQLException e) {
            out.print(e);
            out.print("!يوجد خطا في الاتصال بقاعدة البيانات");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BuyCards(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(false);
            
            Connection conn = Db.connect();
            double clientbalance = 0;
            double newclientbalance = 0;
            double Sumprice = 0;
            double cardprice = 0;
            int cardid = 0;
            String cardusnam=null;
            String crdpsw=null;
            String crdprice=null;
            String cardexpirty=null;
            String crdcateg=null;
            // double cardprice=0;
            //int j = 0;
            int i=0;
            int k =0;
            int r=0;
            DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            //int quant=Integer.parseInt(request.getParameter("Cardquant"));
            String categ = request.getParameter("CardCategory");
            String command = ("SELECT CardPrice FROM cards WHERE CardCategory='" + categ + "' "
                    + "AND CardStatus='free' ORDER BY No ASC LIMIT 1");
            Statement stmnt = conn.createStatement();
            ResultSet rs = stmnt.executeQuery(command);
            
            while (rs.next()) {
                cardprice = Double.parseDouble(rs.getString(1));
                Sumprice = cardprice * 1;

            }
            String clId = (String) session.getAttribute("uId");
            String command1 = ("SELECT ClientBalance FROM clients WHERE Id=" + clId + "");
            Statement sms = conn.createStatement();
            ResultSet rs1 = sms.executeQuery(command1);
            
            while (rs1.next()) {
                clientbalance = Double.parseDouble(rs1.getString(1));

            }
            if (clientbalance >= Sumprice) {
               String command2 = ("SELECT DISTINCT(No), CardUsrName, CardPassWord,"
                       + "CardCategory,CardPrice, Expirty FROM cards"
                        + " WHERE CardCategory ='" + categ + "' AND CardStatus='free' "
                        + "ORDER BY No ASC LIMIT 1");
                Statement stms = conn.createStatement();
                ResultSet rs2 = stms.executeQuery(command2);
               
                 if(!rs2.isBeforeFirst()){
                      out.print("عفوا لا توجد لدينا كروت من هذه الفئة حاليا  يمكنك اختيار فئة اخرى ");
               
                 } else {
                 out.print("!:بيانات كرتك هي كالتالي");
                out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                        + "            background: yellow;\">\n"
                        + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                        + "                    <th style=\"width:100px; border: 1px solid black;\">اسم المستخدم</th>\n"
                        + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم السري</th>\n"
                        + "                    <th style=\"width:100px; border: 1px solid black;\">مدة الصلاحية</th>\n"
                        + "                </tr>\n"
                        + "               \n"
                        + "            </table>");
                while (rs2.next()) {
                    cardid = Integer.parseInt(rs2.getString(1));
                    cardusnam= rs2.getObject(2).toString();
                    crdpsw= rs2.getObject(3).toString();
                    crdcateg=rs2.getObject(4).toString();
                    crdprice= rs2.getObject(5).toString();
                    cardexpirty=rs2.getObject(6).toString();
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                            + "            background: red;\">");
                    out.println("<tr style=\"border: 1px solid black;\">");
                    out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                    out.println(cardusnam);
                    out.println("</td>");
                    out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                    out.println(crdpsw);
                    out.println("</td>");
                    out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                    out.println(cardexpirty);
                    out.println("</td>");
                    out.println("</tr>");
                    out.print("</table>");
                }
                newclientbalance = clientbalance - Sumprice;
                String command5 = ("UPDATE clients SET ClientBalance=" + newclientbalance + " WHERE Id=" + clId + "");
                PreparedStatement prs = conn.prepareStatement(command5);
                k= prs.executeUpdate();
                String command3 = ("UPDATE cards SET  CardStatus='sold' WHERE CardStatus='free' "
                        + "AND CardCategory='" + categ + "' ORDER BY No ASC LIMIT 1");
                PreparedStatement prs1 = conn.prepareStatement(command3);
                i = prs1.executeUpdate();
                
              //   String command4 = ("INSERT INTO clientssales(ClientId, CardId, "
                //        + "CardCategory, SAmount, SaleDate )"
                  //      + "VALUES(?,?,?,?,?)");
                //PreparedStatement prs2 = conn.prepareStatement(command4);

                //prs2.setString(1, "" + clId + "");
                //prs2.setString(2, "" + cardid + "");
                //prs2.setString(3, "'" + categ + "'");
                //prs2.setString(4, "" + cardprice + "");
                //prs2.setString(5, TodayDate.format(localDate));
               // j = prs2.executeUpdate();
                 String command6 = ("INSERT INTO clientscards(ClientId,CardUserName, CardPassword, "
                        + "CardCategory, CardPrice,Expirty, SaleDate)"
                        + "VALUES(?,?,?,?,?,?,?)");
                PreparedStatement prs3 = conn.prepareStatement(command6);
                 prs3.setString(1, "" + clId + "");
                prs3.setString(2, "" + cardusnam + "");
                prs3.setString(3, "" + crdpsw + "");
                prs3.setString(4, "'" + crdcateg + "'");
                prs3.setString(5, "" + crdprice + "");
                prs3.setString(6,""+cardexpirty+"");
                prs3.setString(7, TodayDate.format(localDate));
                r = prs3.executeUpdate();
                String ClPrSql = ("INSERT INTO clientsprocedures(ClientId,oper_type,oper_date,SAmount,notes) "
                    + "VALUES(?,?,?,?,?)");
            PreparedStatement preparedStatement4 = conn.prepareStatement(ClPrSql);
            preparedStatement4.setInt(1, Integer.parseInt(clId));
           preparedStatement4.setString (2, "شراء كرت");
           preparedStatement4.setString(3, TodayDate.format(localDate));
            preparedStatement4.setDouble(4, -(double)Double.parseDouble(crdprice));
            preparedStatement4.setString(5, crdcateg+"فئة");
            int n = preparedStatement4.executeUpdate();
                if (i > 0 && k > 0 && r>0 && n>0) {
                    out.print("!نتمنى لك قضاء وقتا ممتعا");

                } else{
                out.print("!لم تتم العملية");
                }
                 }
                 

            } else{
             out.print("<p>!عزيز العميل ان رصيدك غير كاف لاتمام عملية الشراء يرجى اعادة تعبئة رصيدك في اقرب فرع لنا وشكرا </p>");
                out.print("\n\n");
                out.print("<p>صنعاء حدة</p>");
            }

        } catch (SQLException e) {
            out.print(e);
            out.print("!يوجد خطا في الاتصال بقاعدة البيانات");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void currentpointRevengue(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(false);
            String pId = (String) session.getAttribute("pId");
            Db db = new Db();
            Connection conn = db.connect();
            String datFr = request.getParameter("RevdatFrom");
            String datTo = request.getParameter("RevdatTo");
            //String numbr=request.getParameter("STxtTelephon");
DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate datfr = LocalDate.parse(datFr);
            LocalDate datto = LocalDate.parse(datTo);
                       
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                  
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n"
                   +"                    <th style=\"width:100px; border: 1px solid black;\"> المبلغ</th>\n"
                     +"                    <th style=\"width:100px; border: 1px solid black;\"> الملاحظات</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
String command = ("SELECT * FROM revengue WHERE RPointId=" + pId + " AND RDate BETWEEN '" + TodayDate.format(datfr) + "' AND '" + TodayDate.format(datto) + "'");
            Statement stmnts = conn.createStatement();
            // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
            //          preparedStatement.setString(1, nm);
            //         preparedStatement.setString(2, numbr);
            ResultSet rs = stmnts.executeQuery(command);
            while (rs.next()) {
               // double rev = 0;

               //rev = Double.parseDouble(rs.getString(4));
               // String dat = rs.getString(5);
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(4).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(5).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getString(6));
                out.print("</td>");
                out.println("</tr>");
                out.print("</table>");

            }
        } catch (SQLException e) {
            out.print(e);
            out.print("!يوجد خطا في الاتصال");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void currenpointSales(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(false);
            String pId = (String) session.getAttribute("pId");
            Db db = new Db();
            Connection conn = db.connect();
            String datFr = request.getParameter("SaledatFrom");
            String datTo = request.getParameter("SaledatTo");
            //String numbr=request.getParameter("STxtTelephon");
 out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> مدين</th>\n"
          + "                    <th style=\"width:100px; border: 1px solid black;\"> رقم الزبون</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            String command = ("SELECT salespoints.Insurance,"
                    + "sales.ClientId, sales.`SAmount`, sales.SaleDate\n"
                    + "FROM salespoints \n"
                    + "LEFT JOIN sales ON salespoints.`Id`=sales.`SPointId` \n"
                    + "WHERE salespoints.`Id`=" + pId + " AND SaleDate BETWEEN '" + datFr + "' AND '" + datTo + "'");
            Statement stmnts = conn.createStatement();
            // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
            //          preparedStatement.setString(1, nm);
            //         preparedStatement.setString(2, numbr);
            ResultSet rs = stmnts.executeQuery(command);
            
                   while (rs.next()) {
                //double sal = 0;

               // sal = Double.parseDouble(rs.getString(4));

                //String dat = rs.getString(5);

                //double rembalance = 0;
                //rembalance = Double.parseDouble(rs.getString(3));
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(3).toString());
                out.println("</td>");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(4).toString());
                out.println("</td>");
                 out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(1).toString());
                out.println("</td>");
                 out.println("</tr>");
                out.print("</table>");

            }
        } catch (SQLException e) {
            out.print(e);
            out.print("!يوجد خطا في الاتصال");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void currenpointReport(HttpServletRequest request, PrintWriter out) {
        try {
            HttpSession session = request.getSession(false);
            String pId = (String) session.getAttribute("pId");
            request.setCharacterEncoding("UTF-8");
            Db db = new Db();
            Connection conn = db.connect();
            // String datFr=request.getParameter("datFrom");
            // String datTo=request.getParameter("datTo");
            //String numbr=request.getParameter("STxtTelephon");

            String command1 = ("SELECT salespoints.Id,salespoints.PointName,COALESCE((SUM(sales.`SAmount`)),0)"
                    + "FROM salespoints "
                    + "LEFT JOIN sales ON salespoints.`Id`=sales.`SPointId` \n"
                    + "WHERE salespoints.`Id`='" + pId + "' ");
            Statement stmnts1 = conn.createStatement();
            ResultSet rs1 = stmnts1.executeQuery(command1);
            String command = ("SELECT salespoints.Id,salespoints.PointName,"
                    + " COALESCE((SUM(revengue.`RAmount`)),0)\n"
                    + "FROM salespoints LEFT JOIN revengue ON salespoints.`Id`=revengue.`RPointId` \n"
                    + "WHERE salespoints.`Id`='" + pId + "' ");
            Statement stmnts = conn.createStatement();
            ResultSet rs = stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> مدين</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> دائن</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            double rembalance = 0;
            double sal = 0;
            double rev = 0;
            while (rs1.next()) {

                sal = Double.parseDouble(rs1.getString(3));

                //   rev= Double.parseDouble( rs.getString(4));
                //  rembalance=rev-sal;
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(sal);
                out.print("</td>");
            }
            while (rs.next()) {
                rev = Double.parseDouble(rs.getString(3));
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rev);
                out.println("</td>");
            }
            rembalance = rev - sal;
            out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
            out.println(rembalance);
            out.println("</td>");
            out.println("</tr>");
            out.print("</table>");

            
        } catch (SQLException e) {
            out.print(e);
            out.print("هناك خطا في الاتصال");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void charge(HttpServletRequest request, PrintWriter out) {
        try {

            HttpSession session = request.getSession(false);
            String pId = (String) session.getAttribute("pId");
            String eId = (String) session.getAttribute("emplId");
            Db db = new Db();
            double clientbalance = 0;
            double Pinsur = 0;
            double newbalance = 0;
            double newPInsurance = 0;
            DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            String cId = request.getParameter("cid");
            String balanceToCharge = request.getParameter("amountofBalance");
            double balance = 0;
             double checkbalance = 0;
             String pName=null;
            Connection conn = Db.connect();
            String sqlCheckusn=("Select Id From clients "
                     + "Where Id='"+cId+"' "); 
             Statement checkst=conn.createStatement();
             ResultSet checkrs=checkst.executeQuery(sqlCheckusn);
             if(!checkrs.isBeforeFirst()){
            out.print("عذرا لايوجد عميل بهذا الرقم");
             }
             else{
            String command = ("SELECT PointName, Insurancerange, Insurance FROM salespoints WHERE Id=" + pId + "");
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery(command);
            while (rs.next()) {
                pName=rs.getObject(1).toString();
                Pinsur = (double) rs.getObject(2);
                balance=(double) rs.getObject(3);
                checkbalance= balance- (double)Double.parseDouble(balanceToCharge);
            }
            if (Pinsur < checkbalance) {
                String command1 = ("SELECT ClientBalance FROM clients WHERE Id=" + cId + "");
                Statement sm1 = conn.createStatement();
                ResultSet rs1 = sm1.executeQuery(command1);
                while (rs1.next()) {
                    clientbalance = (double) rs1.getObject(1);
                }
                newbalance = clientbalance + (double)Double.parseDouble(balanceToCharge);
                newPInsurance = balance-(double)Double.parseDouble(balanceToCharge);
 String comannd2 = ("UPDATE clients SET ClientBalance=" + newbalance + " WHERE Id=" + cId + "");
            String command3 = ("UPDATE salespoints SET Insurance=" + newPInsurance + " WHERE Id=" + pId + "");
            String command4 = ("INSERT INTO sales(SPointId,PEmplId,ClientId,SAmount,SaleDate) VALUES(?,?,?,?,?)");
            PreparedStatement pstmnts = conn.prepareStatement(comannd2);
            PreparedStatement pstmnts1 = conn.prepareStatement(command3);
            PreparedStatement pstmnts2 = conn.prepareStatement(command4);
            pstmnts2.setString(1, pId);
            pstmnts2.setString(2, eId);
            pstmnts2.setString(3, cId);
            pstmnts2.setDouble(4, (double)Double.parseDouble(balanceToCharge));
            pstmnts2.setString(5, TodayDate.format(localDate));
            int i = pstmnts.executeUpdate();
            int j = pstmnts1.executeUpdate();
            int k = pstmnts2.executeUpdate();
            String command5 = ("INSERT INTO pempprocedures(proceType,eId,cId,Amount,procedate) "
                    + "VALUES(?,?,?,?,?)");
            PreparedStatement preparedStatement3 = conn.prepareStatement(command5);
            preparedStatement3.setString(1, "تعبئة رصيد");
            preparedStatement3.setInt(2, Integer.parseInt(eId));
            preparedStatement3.setInt(3, Integer.parseInt(cId));
            preparedStatement3.setDouble(4, (double)Double.parseDouble(balanceToCharge));
            preparedStatement3.setString(5, TodayDate.format(localDate));
            int r = preparedStatement3.executeUpdate();
            String ClPrSql = ("INSERT INTO clientsprocedures(ClientId,oper_type,oper_date,SAmount,notes) "
                    + "VALUES(?,?,?,?,?)");
            PreparedStatement preparedStatement4 = conn.prepareStatement(ClPrSql);
            preparedStatement4.setInt(1, Integer.parseInt(cId));
           preparedStatement4.setString (2, "تعبئة رصيد");
           preparedStatement4.setString(3, TodayDate.format(localDate));
            preparedStatement4.setDouble(4, (double)Double.parseDouble(balanceToCharge));
            preparedStatement4.setString(5, pName+"اسم نقطة البيع");
           
            
            int m = preparedStatement4.executeUpdate();
            if (k > 0 && i > 0 && j > 0 && r > 0 && m > 0) {
                out.print("!تمت التعبئة ");

            } else {
                out.print("خطا");
            }
            } else {
                out.print("عذرا لقد تجاوزتم السقف المسموح به يرجى مراجعة مركرز الشبكة");
            }
        }
           

        } catch (SQLException e) {
            out.print(e);
            out.print("!خطا في الاتصال بقاعدة البيانات");
        }

    }

    public void toPInterface(HttpServletRequest request, PrintWriter out) {
        out.println("<!DOCTYPE html>\n"
                + "<!--\n"
                + "To change this license header, choose License Headers in Project Properties.\n"
                + "To change this template file, choose Tools | Templates\n"
                + "and open the template in the editor.\n"
                + "-->\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>TODO supply a title</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "        <style>\n"
                + ".dropbtn {\n"
                + "    background-color: #4CAF50;\n"
                + "    color: white;\n"
                + "    padding: 16px;\n"
                + "    font-size: 16px;\n"
                + "    border: none;\n"
                + "    cursor: pointer;\n"
                + "}\n"
                + "\n"
                + ".dropdown {\n"
                + "    position: relative;\n"
                + "    display: inline-block;\n"
                + "}\n"
                + "\n"
                + ".dropdown-content {\n"
                + "    display: none;\n"
                + "    position: absolute;\n"
                + "    right: 0;\n"
                + "    background-color: #f9f9f9;\n"
                + "    min-width: 160px;\n"
                + "    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n"
                + "    z-index: 1;\n"
                + "}\n"
                + "\n"
                + ".dropdown-content a {\n"
                + "    color: black;\n"
                + "    padding: 12px 16px;\n"
                + "    text-decoration: none;\n"
                + "    display: block;\n"
                + "}\n"
                + "\n"
                + ".dropdown-content a:hover {background-color: #f1f1f1}\n"
                + "\n"
                + ".dropdown:hover .dropdown-content {\n"
                + "    display: block;\n"
                + "}\n"
                + "\n"
                + ".dropdown:hover .dropbtn {\n"
                + "    background-color: #3e8e41;\n"
                + "}\n"
                + " div.menu {\n"
                + "            \n"
                + "        }\n"
                + "</style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <div  id=\"headDiv\" style=\"width: 100%; height: 40px; background-color:\n"
                + "              blue;display: block; text-align: center\">\n"
                + "            <h1 style=\"font-family:verdana; color: white\">WifiCenter</h1>\n"
                + "        </div>\n"
                + "          <div id=\"pageDiv\" style=\"display: inline-block;float: left;width: 86%;\n"
                + "              background: aliceblue;height: 100%; margin: auto\">\n"
                + "                   \n"
                + "          </div>\n"
                + "                <div id=\"menueDiv\" class=\"menu\" style=\"display:inline-block;float: right;width: 14%;\n"
                + "                    height: 100%;background-color: deeppink;margin: auto; text-align: center\" >\n"
                + "                     <div class=\"dropdown\" style=\"float:right;\">\n"
                + "  <button class=\"dropbtn\">القائمة</button>\n"
                + "  <div class=\"dropdown-content\">\n"
                + "    <a href=\"WifiSales\">تعبئة رصيد</a>\n"
                + "    <a href=\"#\">تقاريرك</a>\n"
                + "  \n"
                + "  </div>    \n"
                + "                </div>\n"
                + "                </div>\n"
                + "                </body>\n"
                + "</html>\n"
                + "");
    }

    public void select(HttpServletRequest request, PrintWriter out) {
        try {
            Db db = new Db();
            Connection conn = Db.connect();
            String nm = request.getParameter("STxtName");
            String numbr = request.getParameter("STxtTelephon");

            String command = ("SELECT * FROM salespoints WHERE (PointName='" + nm + "' AND TelephonNumber='" + numbr + "')");
            Statement stmnts = conn.createStatement();
            // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
            //          preparedStatement.setString(1, nm);
            //         preparedStatement.setString(2, numbr);
            ResultSet rs = stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> رقم الهاتف</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> العنوان</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> سقف التامين</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");

            while (rs.next()) {
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(3).toString());
                out.print("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(4).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(5).toString());
                out.println("</td style=\"text-align: center;border: 1px solid black;\">");
                out.println("</tr>");
                out.print("</table>");

                //Integer pointNo=re.getInt("No");
                //String pointname=re.getString("PointName");
                //String pointhonNumber =re.getString("TelephonNumber");
                //String pointAdress=re.getString("Adress");
                //String pointInsurance=re.getString("Insurance");
                //out.print(pointNo+"::");
                //out.print(pointname+"::");
                //out.print(pointhonNumber+"::");
                //out.print(pointAdress+"::");
                //out.print(pointInsurance+"::");
                out.print("hiiii");
            }

        } catch (SQLException e) {
            out.print(e);

        } catch (Exception ex) {
            out.print(ex);

        }
    }

    public static double returnbalance(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            //String id=request.getParameter("pid");
            //ReturnBalance.pId=id;
            //ReturnBalance.returnbalance(request, out);
            //double  c=ReturnBalance.returnbalance(request, out);
            Db db = new Db();
            String id = request.getParameter("pid");
            Connection conn = Db.connect();
            String command = ("SELECT Insurance FROM salespoints WHERE Id='" + id + "' ");
            Statement stmnts = conn.createStatement();
            ResultSet rslt = stmnts.executeQuery(command);
            while (rslt.next()) {
                double retB = (double) rslt.getObject(1);
                // return retB;
                out.print(retB);
            }
        } catch (SQLException e) {
            System.err.println(e);
            out.print("يوجد خطا");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void SumReportByNam(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            Db db = new Db();
            Connection conn = Db.connect();
            String pnam = request.getParameter("PNamSumReport");
            //String numbr=request.getParameter("STxtTelephon");
            String command1 = ("SELECT salespoints.Id,salespoints.PointName,COALESCE((SUM(sales.`SAmount`)),0)"
                    + "FROM salespoints "
                    + "LEFT JOIN sales ON salespoints.`Id`=sales.`SPointId` \n"
                    + "WHERE salespoints.`PointName`='" + pnam + "' ");
            Statement stmnts1 = conn.createStatement();
            ResultSet rs1 = stmnts1.executeQuery(command1);
            String command = ("SELECT salespoints.Id,salespoints.PointName,"
                    + " COALESCE((SUM(revengue.`RAmount`)),0)\n"
                    + "FROM salespoints LEFT JOIN revengue ON salespoints.`Id`=revengue.`RPointId` \n"
                    + "WHERE salespoints.`PointName`='" + pnam + "' ");
            Statement stmnts = conn.createStatement();
            ResultSet rs = stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> مدين</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> دائن</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            double rembalance = 0;
            double sal = 0;
            double rev = 0;
            while (rs1.next()) {

                sal = Double.parseDouble(rs1.getString(3));

                //   rev= Double.parseDouble( rs.getString(4));
                //  rembalance=rev-sal;
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(sal);
                out.print("</td>");
            }
            while (rs.next()) {
                rev = Double.parseDouble(rs.getString(3));
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rev);
                out.println("</td>");
            }
            rembalance = rev - sal;
            out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
            out.println(rembalance);
            out.println("</td>");
            out.println("</tr>");
            out.print("</table>");

        } catch (SQLException e) {
            out.print(e);
            out.print("!خطا في الاتصال");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//Remained balance and summary point report. 

    public void RemBalance(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            Db db = new Db();
            Connection conn = Db.connect();
            String pId = request.getParameter("PIdSumReport");
            //String numbr=request.getParameter("STxtTelephon");
            String command1 = ("SELECT salespoints.Id,salespoints.PointName,COALESCE((SUM(sales.`SAmount`)),0)"
                    + "FROM salespoints "
                    + "LEFT JOIN sales ON salespoints.`Id`=sales.`SPointId` \n"
                    + "WHERE salespoints.`Id`='" + pId + "' ");
            Statement stmnts1 = conn.createStatement();
            ResultSet rs1 = stmnts1.executeQuery(command1);
            String command = ("SELECT salespoints.Id,salespoints.PointName,"
                    + " COALESCE((SUM(revengue.`RAmount`)),0)\n"
                    + "FROM salespoints LEFT JOIN revengue ON salespoints.`Id`=revengue.`RPointId` \n"
                    + "WHERE salespoints.`Id`='" + pId + "' ");
            Statement stmnts = conn.createStatement();
            ResultSet rs = stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> مدين</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> دائن</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            double rembalance = 0;
            double sal = 0;
            double rev = 0;
            while (rs1.next()) {

                sal = Double.parseDouble(rs1.getString(3));

                //   rev= Double.parseDouble( rs.getString(4));
                //  rembalance=rev-sal;
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(sal);
                out.print("</td>");
            }
            while (rs.next()) {
                rev = Double.parseDouble(rs.getString(3));
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rev);
                out.println("</td>");
            }
            rembalance = rev - sal;
            out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
            out.println(rembalance);
            out.println("</td>");
            out.println("</tr>");
            out.print("</table>");

        } catch (SQLException e) {
            out.print(e);
            out.print("!خطا في الاتصال");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void FromToReport(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            Db db = new Db();
            Connection conn = Db.connect();
            String pId = request.getParameter("IdTxt");
            String DateFr =request.getParameter("DateFr");
            String DateTo = request.getParameter("DateTo");
            DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate datfr = LocalDate.parse(DateFr);
            LocalDate datto = LocalDate.parse(DateTo);
           // DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
            //java.util.Date dfr=originalFormat.parse(DateFr);
            //java.util.Date dto=originalFormat.parse(DateTo);
            //DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
           // DateFr=targetFormat.format(dfr);
            //DateTo=targetFormat.format(dto);
             String command1 = ("SELECT salespoints.Id,salespoints.PointName,"
                    + "COALESCE((SUM(sales.`SAmount`)),0)"
                    + "FROM salespoints "
                    + "LEFT JOIN sales ON salespoints.`Id`=sales.`SPointId`"
                    + "WHERE salespoints.`Id`='"+pId+"' "
                    + "AND sales.SaleDate BETWEEN '"+TodayDate.format(datfr)+"' AND '"+TodayDate.format(datto)+"'");
            Statement stmnts1 = conn.createStatement();
            ResultSet rs1 = stmnts1.executeQuery(command1);
            String command = ("SELECT salespoints.Id,salespoints.PointName,"
                    + " COALESCE((SUM(revengue.`RAmount`)),0)"
                    + "FROM salespoints LEFT JOIN revengue ON salespoints.`Id`=revengue."
                    + "`RPointId`"
                    + "WHERE salespoints.`Id`='"+pId+"'"
                    + "AND revengue.RDate BETWEEN '"+TodayDate.format(datfr)+"' AND '"+TodayDate.format(datto)+"'");
            Statement stmnts = conn.createStatement();
            ResultSet rs = stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> مدين</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> دائن</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            double rembalance = 0;
            double sal = 0;
            double rev = 0;
            while (rs1.next()) {

                sal = Double.parseDouble(rs1.getString(3));

                //   rev= Double.parseDouble( rs.getString(4));
                //  rembalance=rev-sal;
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(sal);
                out.print("</td>");
            }
            while (rs.next()) {
                rev = Double.parseDouble(rs.getString(3));
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rev);
                out.println("</td>");
            }
            rembalance = rev - sal;
            out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
            out.println(rembalance);
            out.println("</td>");
            out.println("</tr>");
            out.print("</table>");

        } catch (SQLException e) {
            out.print(e);
            out.print("!خطا في الاتصال");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TodayReport(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            Db db = new Db();
            Connection conn = Db.connect();
            String pId = request.getParameter("IdSelect");
            //String TodayDate=request.getParameter("today");
            DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            //String numbr=request.getParameter("STxtTelephon");
            String command1 = ("SELECT salespoints.Id,salespoints.PointName,COALESCE((SUM(sales.`SAmount`)),0)"
                    + "FROM salespoints "
                    + "LEFT JOIN sales ON salespoints.`Id`=sales.`SPointId`"
                    + "WHERE salespoints.`Id`='"+pId+"' "
                    + "AND sales.SaleDate='"+TodayDate.format(localDate)+"'");
            Statement stmnts1 = conn.createStatement();
            ResultSet rs1 = stmnts1.executeQuery(command1);
            String command = ("SELECT salespoints.Id,salespoints.PointName,"
                    + " COALESCE((SUM(revengue.`RAmount`)),0)\n"
                    + "FROM salespoints LEFT JOIN revengue ON salespoints.`Id`=revengue.`RPointId`"
                    + "WHERE salespoints.`Id`='"+pId+"' "
                    + "AND revengue.RDate='"+TodayDate.format(localDate)+"'");
            Statement stmnts = conn.createStatement();
            ResultSet rs = stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> مدين</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> دائن</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");
            double rembalance = 0;
            double sal = 0;
            double rev = 0;
            while (rs1.next()) {

                sal = Double.parseDouble(rs1.getString(3));

                //   rev= Double.parseDouble( rs.getString(4));
                //  rembalance=rev-sal;
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs1.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(sal);
                out.print("</td>");
            }
            while (rs.next()) {
                rev = Double.parseDouble(rs.getString(3));
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rev);
                out.println("</td>");
            }
            String sqlbalance=("Select Insurance From salespoints Where Id='"+pId+"'");
            Statement bst=conn.createStatement();
            ResultSet brs=bst.executeQuery(sqlbalance);
            while (brs.next()){
            rembalance = Double.parseDouble(brs.getString(1));
            out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
            out.println(rembalance);
            out.println("</td>");
            out.println("</tr>");
            out.print("</table>");
            }

        } catch (SQLException e) {
            out.print(e);
            out.print("!خطا في الاتصال");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SrchRevengue(HttpServletRequest request, PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
            Db Db = new Db();
            Connection conn = Db.connect();
            String revdat = request.getParameter("SrchPRevengueByDate");
            String revenId = request.getParameter("SrchPRevengueById");
            //DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
            // Date date=dateFormat.parse(revdat);
            //   dateFormat=new SimpleDateFormat("yyyy-MM-dd");

            //  java.util.Date parsedDate=dateFormat.parse(revdat);
            //  dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            //  revdat=dateFormat.format(parsedDate);
            // java.sql.Date sqlDate;
            //sqlDate = new java.sql.Date(dateFormat.getTime());
            String command = ("SELECT * FROM revengue WHERE (RPointId=" + revenId + " AND RDate=" + revdat + " )");
            //PreparedStatement pr=conn.prepareStatement(command);
            //pr.setString(1, revenId);
            //pr.setDate(2, sqlDate);
            Statement stmnts = conn.createStatement();
            // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
            //          preparedStatement.setString(1, nm);
            //         preparedStatement.setString(2, numbr);
            ResultSet rs = stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n"
                    + "            background: yellow;\">\n"
                    + "                    <tr style=\"width:100px; border: 1px solid black;\"> \n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\">رقم نقطة البيع</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> رقم الموضف</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> المبلغ المورد</th>\n"
                    + "                    <th style=\"width:100px; border: 1px solid black;\"> الملاحظات</th>\n"
                    + "                </tr>\n"
                    + "               \n"
                    + "            </table>");

            while (rs.next()) {
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n"
                        + "            background: red;\">");
                out.println("<tr style=\"border: 1px solid black;\">");
                out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(1).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(2).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(3).toString());
                out.print("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(4).toString());
                out.println("</td>");
                out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(5).toString());
                 out.println("</td>");
                 out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
                out.println(rs.getObject(6).toString());
                out.println("</td style=\"text-align: center;border: 1px solid black;\">");
                out.println("</tr>");
                out.print("</table>");

                //Integer pointNo=re.getInt("No");
                //String pointname=re.getString("PointName");
                //String pointhonNumber =re.getString("TelephonNumber");
                //String pointAdress=re.getString("Adress");
                //String pointInsurance=re.getString("Insurance");
                //out.print(pointNo+"::");
                //out.print(pointname+"::");
                //out.print(pointhonNumber+"::");
                //out.print(pointAdress+"::");
                //out.print(pointInsurance+"::");
            }
            out.print("تمت بنجاح");
        } catch (SQLException e) {
            out.print(e);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(testServlet.class.getName()).log(Level.SEVERE, null, ex);
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
