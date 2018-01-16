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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.Date;
//import java.time.LocalDate;
/**
 *
 * @author Ab-ps
 */
@WebServlet(urlPatterns = {"/points","/insert","/search",
    "/update","/delete","/exist","/addrevengue"
,"/salessrch","/deleterevengue","/setdefvaltoedpoint","/setdefvaltodelpoint",
        "/srchsalesbydat","/sortsalesfrtodat","/EdtSrchTxtValue"
,"/srchsalesbyId","/serchemplactiv","/pemplactiv","/searchptoaddrevengue",
"/clientprocerure","/searchpoint2del","/searchpoint2edt"})


public class PointSalesServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
       String path=request.getServletPath();
      
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            switch (path) {
                case "/insert":
                    insert(request,out);
                    break;
                case "/search":
                    search(request,out);
                    break;
                case "/update":
                    update(request, out);
                    break;
                case "/delete":
                    delete(request, out);
                    break;
                case "/exist":
                    IfPointExist(request,out);
                    break;
                case "/addrevengue":
                    addrevengue(request,out);
                    break;
                case "/salessrch":
                    salessrch(request,out);
                    break;
                case "/deleterevengue":
                    delrevengue(request,out);
                    break;
                case "/setdefvaltoedpoint":
                    SetDeFvalueToEdit(request, out);
                    break;
                case "/srchsalesbydat":
                    SrchSalesByDat(request,out);
                    break;
                case "/sortsalesfrtodat":
                    SortSalesFromToDate(request,out);
                    break;
                case "/EdtSrchTxtValue":
                    EdtSrchTxtValue(request,out);
                    break;
                case "/srchsalesbyId":
                    SalesSrchById(request,out);
                    break;
                    case "/serchemplactiv":
                        searchemplactivities(request, out);
                        break;
                        case "/pemplactiv":
                       PEmplactivities(request, out);
                        break;
                        case "/searchptoaddrevengue":
                            searchP2AddRevengue(request, out);
                            break;
                            case"/searchpoint2del":
                                searchpoint2del(request, out);
                                break;
                                case"/searchpoint2edt":
                                    searchpoint2edt(request, out);
                                    break;
                        case"/clientprocerure":
                        ClProcsrures(request, out);
                        break;
                default:
                    break;
            }
 
        }
         
        

    }
    public void searchpoint2edt(HttpServletRequest request, PrintWriter out){
    try {
            request.setCharacterEncoding("UTF-8");
           
        Connection conn=Db.connect();
        String pid=request.getParameter("EdtSrcTxt");
              
        String command=("SELECT * FROM salespoints WHERE Id = '"+pid+"' ");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الهاتف</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> العنوان</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> الهوية</th>\n" +                    
"                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n" +                    
"                    <th style=\"width:100px; border: 1px solid black;\"> سقف التامين</th>\n" +
"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    double insur=(double) Double.parseDouble(rs.getObject(7).toString());
                    double plusinsur=insur*(-1);
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
  out.println("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(plusinsur);
  out.println("</td>");
    out.println("</tr>");
    out.print("</table>");
    
            }
           out.print("تمت بنجاح");   
        }catch(SQLException e) {
            out.print(e);
            out.print("لم تتم العملية");
        }catch(UnsupportedEncodingException ex){
            out.print(ex);
        
        }
    }
    public void searchpoint2del(HttpServletRequest request, PrintWriter out){
    try {
            request.setCharacterEncoding("UTF-8");
           
        Connection conn=Db.connect();
        String pid=request.getParameter("DelSrcTxt");
              
        String command=("SELECT * FROM salespoints WHERE Id = '"+pid+"' ");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الهاتف</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> العنوان</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> الهوية</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n" +                    
"                    <th style=\"width:100px; border: 1px solid black;\"> سقف التامين</th>\n" +
"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    double insur=(double) Double.parseDouble(rs.getObject(7).toString());
                    double plusinsur=insur*(-1);
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
  out.println("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(plusinsur);
  out.println("</td>");
    out.println("</tr>");
    out.print("</table>");
    
            }
           out.print("تمت بنجاح");   
        }catch(SQLException e) {
            out.print(e);
            out.print("لم تتم العملية");
        }catch(UnsupportedEncodingException ex){
            out.print(ex);
        
        }
    
    }
        public void insert(HttpServletRequest request, PrintWriter out )
        {
          
          HttpSession session=request.getSession(false);
            String eId=(String) session.getAttribute("eId");
          
            Connection conn=Db.connect();
       
             try {
                  request.setCharacterEncoding("UTF-8");
                // String nm1 =request.getParameter("Txtname");
        String nm= request.getParameter("Txtname");
        String nr= request.getParameter("TxtTelephon");
        String ad= request.getParameter("TxtAdress");
        String pasId= request.getParameter("TxtPasId");
        String amnt= request.getParameter("TxtInsurance");
        String usn= request.getParameter("txtuserName");
         String psw= request.getParameter("textpassWord");
        
         int pId=0;
            double range=0-((double)Double.parseDouble(amnt));              
             String sqlCheckusn=("Select EmplUserName From pointsemployees "
                     + "Where Binary EmplUserName='"+usn+"' "); 
             Statement checkst=conn.createStatement();
             ResultSet checkrs=checkst.executeQuery(sqlCheckusn);
             if(!checkrs.isBeforeFirst()){
        String command=("INSERT INTO salespoints(PointName,TelephonNumber,Adress,ident_No,"
                + "Insurancerange) "
                + "VALUES(?,?,?,?,?)") ;
                 PreparedStatement preparedStatement = conn.prepareStatement(command) ;
                    preparedStatement.setString(1, nm);
                    preparedStatement.setString(2, nr);
                    preparedStatement.setString(3, ad);
                    preparedStatement.setString(4,pasId);
                    preparedStatement.setDouble(5,range);
                    int i =preparedStatement.executeUpdate();
                      
                           String comand2=("Select Id From salespoints Where PointName='"+nm+"'");
                     Statement stm=conn.createStatement();
                     ResultSet rs=stm.executeQuery(comand2);
                     while (rs.next()){
                     pId=rs.getInt(1);
                    
                     }
                    
                                
                         String command1=("INSERT INTO pointsemployees(EmplName ,EmplNumber,Adress,"
                            + "EmplUserName,EmplPassword,pId,Class) VALUES(?,?,?,?,?,?,?)") ;
                    DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     LocalDate localDate = LocalDate.now();
                 PreparedStatement preparedStatement1 = conn.prepareStatement(command1) ;
                    preparedStatement1.setString(1, nm);
                    preparedStatement1.setString(2, nr);
                    preparedStatement1.setString(3, ad);
                    preparedStatement1.setString(4,usn);
                     preparedStatement1.setString(5,psw);
                     preparedStatement1.setInt(6,pId);
                     preparedStatement1.setString(7,"director");
                    int j =preparedStatement1.executeUpdate();
                    
                     String command3=("INSERT INTO procedures(proceType,eId,pId,pocdate,Amount) "
                + "VALUES(?,?,?,?,?)") ;
                 PreparedStatement preparedStatement3 = conn.prepareStatement(command3) ;
                    preparedStatement3.setString(1, "اضافة نقطة");
                      preparedStatement3.setInt(2,Integer.parseInt(eId));
                    preparedStatement3.setInt(3, pId);
                    preparedStatement3.setString(4,TodayDate.format(localDate));
                    preparedStatement3.setDouble(5, (double)Double.parseDouble(amnt));
                    int k =preparedStatement3.executeUpdate();
                    
                     
                    if (i>0 && j>0 && k>0){
                    
                      out.print("تم الاضافة بنجاح!");
                    } else
                    {
                    out.print("لم تنجح الاضافة!!!");
                   }
             } else {
            out.print("لايمكنك استعمال اسم المستخدم هذا لانهتم استعماله من قبل مستخدم اخر"+usn+"يرجى استخدام غيره");
             }
                                  
                
         conn.close();
           }
           catch(SQLException se)
            {
                out.print(se);
                se.getStackTrace();
                System.out.println("!!!!!خطا في الاتصال بقاعدة البيانات");
            }
            catch(UnsupportedEncodingException | NumberFormatException ex) {
                System.out.println("!!!!!خطا في النظام");
                ex.getStackTrace();
                out.print(ex);
           
            }
            
         
        
        }
        public  void searchP2AddRevengue(HttpServletRequest request,PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
           
        Connection conn=Db.connect();
        String num=request.getParameter("AddRevenguePIdnamTxt");
              
        String command=("SELECT * FROM salespoints WHERE Id = '"+num+"' ");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الهاتف</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> العنوان</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الهوية</th>\n" +                    
"                    <th style=\"width:100px; border: 1px solid black;\"> الرصيد الحالي</th>\n" +                    
"                    <th style=\"width:100px; border: 1px solid black;\"> سقف التامين</th>\n" +
"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    double insur=(double) Double.parseDouble(rs.getObject(7).toString());
                    double plusinsur=insur*(-1);
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
  out.println("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(plusinsur);
  out.println("</td>");
    out.println("</tr>");
    out.print("</table>");
    
            }
           out.print("تمت بنجاح");   
        }catch(SQLException e) {
            out.print(e);
            out.print("لم تتم العملية");
        }catch(UnsupportedEncodingException ex){
            out.print(ex);
        
        }
    }
        public void PEmplactivities(HttpServletRequest request,PrintWriter out){
         try {
             
            request.setCharacterEncoding("UTF-8");
            
        Connection conn=Db.connect();
        
          String operdat=request.getParameter("operdate");
            
        String command=("SELECT * FROM pempprocedures WHERE procedate ='"+operdat+"'");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, "'"+operdat+"'");
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">نوع الاجراء</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الموظف</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم العميل</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> المبلغ</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n" +

"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
  out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(rs.getObject(6).toString());
  out.println("</td>");
    out.println("</tr>");
    out.print("</table>");
    
            }
           out.print("تمت بنجاح");   
        }catch(SQLException e) {
            out.print(e);
            out.print("لم تتم العملية");
        }catch(UnsupportedEncodingException ex){
            out.print(ex);
        }
        }
        public void searchemplactivities(HttpServletRequest request,PrintWriter out){
        try {
            request.setCharacterEncoding("UTF-8");
         
        Connection conn=Db.connect();
        String typ=request.getParameter("typ");
          String operdat=request.getParameter("operdate");
            
        String command=("SELECT * FROM procedures WHERE (proceType ="+typ+" AND pocdate = "+operdat+")");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">نوع الاجراء</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الموظف</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم النقطة</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> المبلغ</th>\n" +

"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
  out.println("</td>");
    out.println("</tr>");
    out.print("</table>");
    
            }
           out.print("تمت بنجاح");   
        }catch(SQLException e) {
            out.print(e);
            out.print("لم تتم العملية");
        }catch(UnsupportedEncodingException ex){
            out.print(ex);
        }
        }
       public  void search(HttpServletRequest request,PrintWriter out) {
        try {
            request.setCharacterEncoding("UTF-8");
           
        Connection conn=Db.connect();
        String nm=request.getParameter("STxtName");
          String numbr=request.getParameter("STxtTelephon");
            
        String command=("SELECT * FROM salespoints WHERE (PointName LIKE '%' '"+nm+"' '%' AND Id LIKE '%' '"+numbr+"' '%')");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">اسم نقطة البيع</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الهاتف</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> العنوان</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الهوية</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> الرصيدالحالي</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> سقف التامين</th>\n" +
"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    double insur= (double) Double.parseDouble(rs.getObject(7).toString());
                    double plusinsur=insur*(-1);
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
  out.println("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(plusinsur);
  out.println("</td>");
    out.println("</tr>");
    out.print("</table>");
    
            }
           out.print("تمت بنجاح");   
        }catch(SQLException e) {
            out.print(e);
            out.print("لم تتم العملية");
        }catch(UnsupportedEncodingException | NumberFormatException ex){
            out.print(ex);
        
        }
    }
       public void update(HttpServletRequest request,PrintWriter out){
            HttpSession session=request.getSession(false);
            String eId=(String) session.getAttribute("eId");
        try {
            
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PointSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           try {
               DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     LocalDate localDate = LocalDate.now();
        String nm= request.getParameter("RsEdtNamTxt");
        String nr= request.getParameter("RsEdtTelTxt");
        String snr= request.getParameter("EdtSrcTxt");
        String ad= request.getParameter("RsEdtAdrTxt");
        String amnt= request.getParameter("RsEdtInsurTxt");
        String usn= request.getParameter("UsEdtTxt");
        String psw= request.getParameter("PswEdtTxt");
        String psidC= request.getParameter("RsEdtPssIdTxt");
               
               Connection conn=Db.connect();
               double _amnt=0-(double)Double.parseDouble(amnt);
                String sqlCheckusn=("Select EmplUserName From pointsemployees "
                     + "Where Binary EmplUserName='"+usn+"' "); 
             Statement checkst=conn.createStatement();
             ResultSet checkrs=checkst.executeQuery(sqlCheckusn);
             if(!checkrs.isBeforeFirst()){
               String command=("UPDATE salespoints SET PointName='"+nm+"',"
             + "TelephonNumber='"+nr+"',Adress='"+ad+"',ident_No='"+psidC+"',Insurancerange="+_amnt+" "
                       + " WHERE Id="+snr+"");
              PreparedStatement pstmnts =conn.prepareStatement(command);
              // pstmnts.setString(1, nm);
               //pstmnts.setString(2, nr);
               //pstmnts.setString(3, ad);
               //pstmnts.setString(4, amnt);
               //pstmnts.setString(5, nr);
               int i =pstmnts.executeUpdate();
                String command1=("UPDATE pointsemployees SET EmplName='"+nm+"',"
             + "EmplNumber='"+nr+"',PassIdCardNo='"+psidC+"',Adress='"+ad+"',"
                        + "EmplUserName='"+usn+"',EmplPassword='"+psw+"'"
                       + " WHERE pId="+snr+"");
              PreparedStatement pstmnts1 =conn.prepareStatement(command1);
              // pstmnts.setString(1, nm);
               //pstmnts.setString(2, nr);
               //pstmnts.setString(3, ad);
               //pstmnts.setString(4, amnt);
               //pstmnts.setString(5, nr);
               int j =pstmnts1.executeUpdate();
               String command3=("INSERT INTO procedures(proceType,eId,pId,pocdate,Amount) "
                + "VALUES(?,?,?,?,?)") ;
                 PreparedStatement preparedStatement3 = conn.prepareStatement(command3) ;
                    preparedStatement3.setString(1, "تعديل نقطة");
                      preparedStatement3.setInt(2,Integer.parseInt(eId));
                    preparedStatement3.setInt(3, Integer.parseInt(snr));
                    preparedStatement3.setInt(3, Integer.parseInt(snr));
                    preparedStatement3.setString(4,TodayDate.format(localDate));
                     preparedStatement3.setDouble(5, (double)Double.parseDouble(amnt));
                    int k =preparedStatement3.executeUpdate();
                    if (i>0 && j>0 && k>0) {
                    out.print("!تم التعديل بنجاح");
                    
               }
                    else
                    {
                    out.print("!لم بتم التعديل");
                   }
           } else {
            out.print("لايمكنك استعمال اسم المستخدم هذا لانهتم استعماله من قبل مستخدم اخر"+usn+"يرجى استخدام غيره");
             }
           } catch (SQLException e) {
               out.print(e);
                out.print("لم تتم العملية");
           }
           
       }
       public void delete(HttpServletRequest request,PrintWriter out){
          HttpSession session=request.getSession(false);
            String eId=(String) session.getAttribute("eId");
           try {
               DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
     LocalDate localDate = LocalDate.now();
                request.setCharacterEncoding("UTF-8");
       // String nm= request.getParameter("");
        //String nr= request.getParameter("");
        String dbynr= request.getParameter("DelSrcTxt");
        //String ad= request.getParameter("");
        //String amnt= request.getParameter("");
               
               Connection conn=Db.connect();
             //  Statement stmnts=conn.createStatement();
               String command=("DELETE  FROM salespoints WHERE Id=?");
              PreparedStatement pstmnts =conn.prepareStatement(command);
               pstmnts.setString(1, dbynr);
               //pstmnts.setString(2, nr);
               //pstmnts.setString(3, ad);
               //pstmnts.setString(4, amnt);
               //pstmnts.setString(5, nr);
               int i =pstmnts.executeUpdate();
                String commanddelEm=("DELETE  FROM pointsemployees WHERE pId=?");
              PreparedStatement pstmntsdel =conn.prepareStatement(commanddelEm);
               pstmntsdel.setString(1, dbynr);
               
               int r =pstmntsdel.executeUpdate();
               String command3=("INSERT INTO procedures(proceType,eId,pId,pocdate) "
                + "VALUES(?,?,?,?)") ;
                 PreparedStatement preparedStatement3 = conn.prepareStatement(command3) ;
                    preparedStatement3.setString(1, "حذف نقطة");
                      preparedStatement3.setInt(2,Integer.parseInt(eId));
                    preparedStatement3.setInt(3, Integer.parseInt(dbynr));
                    preparedStatement3.setString(4,TodayDate.format(localDate));
                    int k =preparedStatement3.executeUpdate();
                   
                    if (i>0 && k>0 && r>0) {
                    out.print("تم الحذف بنجاح!");
                    
               }
                    else
                    {
                    out.print("لم تنجح العملية");
                   }
           } catch (SQLException e) {
               out.print(e);
                out.print("لم تتم العملية");
           } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PointSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       }
       public void IfPointExist(HttpServletRequest request,PrintWriter out) {
           try {
                request.setCharacterEncoding("UTF-8");
             
               Connection conn=Db.connect();
               Statement stmnts=conn.createStatement();
               String numbr=request.getParameter("TxTelephon");
               //Statement stmnts=conn.createStatement();
               String command=("SELECT * FROM salespoints WHERE TelephonNumber='"+numbr+"'");
              // PreparedStatement pstmnts =conn.prepareStatement(command);
              
               ResultSet rs =stmnts.executeQuery(command);
               while (rs.next()) { 
                   out.print("!توجد هناك نقطة بهذا الرقم");
                   
               }
                    

           } catch (SQLException e) {
               out.print("هناك خطاء في عملية الاتصال بقاعدة البيانات");
           } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PointSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       public void salessrch(HttpServletRequest request,PrintWriter out)
       {
           try {
               
           } catch (Exception e) {
           }
   
       }
       
        public void addrevengue(HttpServletRequest request,PrintWriter out)
       {
           
           try {
                HttpSession session=request.getSession(false);
             String eId=(String) session.getAttribute("eId");
                request.setCharacterEncoding("UTF-8");

               try (Connection conn = Db.connect()) {
                   double newbalance;
                   double balance=0;
                   DateTimeFormatter TodayDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                   LocalDate localDate = LocalDate.now();
                   String pId= request.getParameter("AddRevenguePIdnamTxt");
                   String details=request.getParameter("AddRevengueNoteTxt");
                   String dat= request.getParameter("DatAddRevenguePnumTxt");
                   String amnt= request.getParameter("AddRevenguePinsurTxt");
                   double damnt= Double.parseDouble(amnt);
                   DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
                   //java.util.Date date=dateFormat.parse(dat);
                   // dateFormat=new SimpleDateFormat("yyyy-MM-dd");
                   //dateFormat.format(date)
                   //Statement statement =conn.createStatement();
                   String command=("INSERT INTO revengue(RPointId,EmployeeId,RDate,RAmount,Details) "
                           + "VALUES('"+pId+"','"+eId+"','"+dat+"','"+amnt+"','"+details+"')") ;
                   PreparedStatement preparedStatement = conn.prepareStatement(command) ;
                   //   preparedStatement.setString(1, pId);
                   //  preparedStatement.setString(2, emId);
                   // preparedStatement.setString(3, dateFormat.format(date));
                   // preparedStatement.setString(4,amnt);
                   int i =preparedStatement.executeUpdate();
                   String command1=("Select Insurance FROM salespoints Insurance WHERE Id="+pId+" ");
                   Statement sm=conn.createStatement();
                   ResultSet rs=sm.executeQuery(command1);
                   while (rs.next()) {
                       balance=(double) rs.getObject(1);
                       
                   }
                   newbalance=balance+damnt;
                   String command2=("UPDATE salespoints SET Insurance="+newbalance+" WHERE Id="+pId+"");
                   PreparedStatement pr=conn.prepareStatement(command2);
                   int j=pr.executeUpdate();
                   String command3=("INSERT INTO procedures(proceType,eId,pId,pocdate,Amount) "
                           + "VALUES(?,?,?,?,?)") ;
                   PreparedStatement preparedStatement3 = conn.prepareStatement(command3) ;
                   preparedStatement3.setString(1, "ايراد");
                   preparedStatement3.setInt(2,Integer.parseInt(eId));
                   preparedStatement3.setInt(3, Integer.parseInt(pId));
                   preparedStatement3.setString(4,TodayDate.format(localDate));
                   preparedStatement3.setDouble(5, (double)Double.parseDouble(amnt));
                   int k =preparedStatement3.executeUpdate();
                   if (i>0 && j>0 && k>0) {
                       out.print("تم الاضافة بنجاح!");
                       
                   }
                   else
                   {
                       out.print("لم تنجح الاضافة!");
                   }
               }
           } 
           catch (SQLException e) {
               
               out.print("!خطا في الاتصال");
                
           }catch (Exception ex){
           out.print(ex);
           }
           
       }
       
        public void delrevengue(HttpServletRequest request, PrintWriter out){
            try {
                 request.setCharacterEncoding("UTF-8");
                 
                String dbydate= request.getParameter("DelAddRevenguePnumTxt");
                String pId= request.getParameter("DelRevenguePIdTxt");
        //String ad= request.getParameter("");
        //String amnt= request.getParameter("");
               
               Connection conn=Db.connect();
             //  Statement stmnts=conn.createStatement();
               String command=("DELETE  FROM revengue WHERE RDate=? AND RPointId=?");
              PreparedStatement pstmnts =conn.prepareStatement(command);
               pstmnts.setString(1, dbydate);
               pstmnts.setString(2, pId);
               //pstmnts.setString(3, ad);
               //pstmnts.setString(4, amnt);
               //pstmnts.setString(5, nr);
               int i =pstmnts.executeUpdate();
                    if (i>0) {
                    out.print("تم الحذف بنجاح!");
                    
               }
                    else
                    {
                    out.print("لم تنجح العملية");
                   }
            } catch (SQLException e) {
                out.print(e);
                out.print("خطا في الاتصال بقاعدة البيانات");
            } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PointSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        public void  EdtSrchTxtValue(HttpServletRequest request, PrintWriter out){
            try {
                 request.setCharacterEncoding("UTF-8");
                
        Connection conn=Db.connect();
                String command=("SELECT Id, PointName FROM salespoints");
                Statement stmnts=conn.createStatement();
                 ResultSet rs=stmnts.executeQuery(command);
                  out.println("<form>");
                  
                  out.println(" <input type=\"text\" name=\"cid\" list=\"selectClientId\" id=\"cid\"/>");
                   out.println("<datalist id=\"selectClientId\">");
                   while(rs.next()){
                    String pId=rs.getObject(1).toString();
                     //String pnam=rs.getObject(2).toString();
                   out.println("<option value=\""+pId+"\">"+pId+"</option>");
                  
            }
                   out.println("</datalist>");
                    out.println("</form>");
                   
            } catch (SQLException e) {
                out.print(e);
            } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PointSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        public void SetDeFvalueToEdit (HttpServletRequest request, PrintWriter out) {
            try {
                 request.setCharacterEncoding("UTF-8");
                
        Connection conn=Db.connect();
       String numbr =request.getParameter("EdtSrcTxt");
         // String numbr=request.getParameter("EdtSrcTxt");
            
        String command=("SELECT * FROM salespoints WHERE TelephonNumber='"+numbr+"'");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
                        
                while(rs.next()){
                    String EdtName=rs.getObject(2).toString();
                    String EdtNumbr=rs.getObject(3).toString();
                    String EdtAdress=rs.getObject(4).toString();
                    String EdtAmount=rs.getObject(5).toString();
                   
 
                 out.print("تمت بنجاح");
            }
            } catch (SQLException e) {
            out.print(e);
            out.print("هناك خطا في ");
            } catch(Exception ex) {
          out.print("هناك خطا في ");
          out.print(ex);
            }
        }
        public void SalesSrchById(HttpServletRequest request, PrintWriter out)
        {
        try {
             request.setCharacterEncoding("UTF-8");
                 
        Connection conn=Db.connect();
        String pId=request.getParameter("SrcSalesTxt");
        //  String sDate=request.getParameter("DatSrchField");
            
        String command=("SELECT * FROM sales WHERE  SPointId='"+pId+"'");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">رقم نقطة البيع</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">رقم موظف النقطة</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الزبون</th>\n" +
                    "<th style=\"width:100px; border: 1px solid black;\"> المبلغ</th>\n"+
"                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n" +
"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
    
            }
                 out.print("تمت بنجاح");
            } catch (SQLException sq){out.print("!هناك خطا في الاتصال");
            out.print(sq);
            out.print("هناك خطا في الاتصال");
           
            } 
            catch (Exception e) {
            out.print(e);
            out.print("خطا برمجي");
            }
        }
        public void SrchSalesByDat(HttpServletRequest request, PrintWriter out){
            try {
                 request.setCharacterEncoding("UTF-8");
                 
        Connection conn=Db.connect();
       // String pId=request.getParameter("SrcSalesTxt");
          String sDate=request.getParameter("DatSrchField");
            
        String command=("SELECT * FROM sales WHERE  SaleDate='"+sDate+"'");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">رقم نقطة البيع</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">رقم موظف النقطة</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الزبون</th>\n" +
                    "<th style=\"width:100px; border: 1px solid black;\"> المبلغ</th>\n"+
"                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n" +
"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
    
            }
                out.print("تمت بنجاح");
            } catch (SQLException sq){out.print("!هناك خطا في الاتصال");
            out.print(sq);
            out.print("هناك خطا في الاتصال");
           
            } 
            catch (UnsupportedEncodingException e) {
            out.print(e);
            out.print("خطا برمجي");
            }
        }
    public void ClProcsrures(HttpServletRequest request, PrintWriter out){
    try {
                 request.setCharacterEncoding("UTF-8");
                
        Connection conn=Db.connect();
        String datFrom=request.getParameter("datefrom");
          String datTo=request.getParameter("dateto");
          HttpSession session = request.getSession(false);
            String clId = (String) session.getAttribute("uId");
        String command=("SELECT * FROM clientsprocedures WHERE (ClientId ='"+clId+"'"
                + "AND oper_date BETWEEN '"+datFrom+"' AND '"+datTo+"')");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
        out.print(":تفاصيل اجراءاتك هي");
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">نوع العملية</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">تاريخ العملية</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> المبلغ</th>\n" +
                    "<th style=\"width:100px; border: 1px solid black;\"> ملاحظات</th>\n"+

"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                  
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
              out.println("<tr style=\"border: 1px solid black;\">");
              out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
  out.println(rs.getObject(1).toString());
  out.println("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(rs.getObject(3).toString());
out.println("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(rs.getObject(4).toString());
  out.print("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(rs.getObject(5).toString()+"ر.ي");
  out.println("</td>");
  out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(rs.getObject(6).toString());
  out.println("</td>");
       out.println("</tr>");
    out.print("</table>");
    
            }
                 out.print(":رصيدك المتبقي");
                String remsuSql=("Select ClientBalance From clients Where Id='"+clId+"'") ;
                Statement remsSt=conn.createStatement();
                ResultSet remsRs=remsSt.executeQuery(remsuSql);
                while (remsRs.next()){
                out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
              out.println("<tr style=\"border: 1px solid black;\">");
              out.println("<td style=\" text-align: center;width:100px; border: 1px solid black;\">");
  out.println(remsRs.getObject(1).toString()+"ر.ي");
  out.println("</td>");
   out.println("<td style=\"text-align: center;width:100px; border: 1px solid black;\">");
  out.println(":الرصيد المتبقي هو");
out.println("</td>");
       out.println("</tr>");
    out.print("</table>");         
                }
                
            }catch (SQLException sq){out.print("!هناك خطا في الاتصال");
            out.print(sq);
            } 
            catch (UnsupportedEncodingException e) {
            out.print(e);
            }
    }    
        public void SortSalesFromToDate(HttpServletRequest request, PrintWriter out){
            try {
                 request.setCharacterEncoding("UTF-8");
                
        Connection conn=Db.connect();
        String datFrom=request.getParameter("SrcByDateFrom");
          String datTo=request.getParameter("SrcByDateTo");
            
        String command=("SELECT * FROM sales WHERE (SaleDate BETWEEN '"+datFrom+"' AND'"+datTo+"')");
        Statement stmnts=conn.createStatement();
       // PreparedStatement preparedStatement = conn.prepareStatement(command) ;
         //          preparedStatement.setString(1, nm);
           //         preparedStatement.setString(2, numbr);
        ResultSet rs=stmnts.executeQuery(command);
            out.println(" <table  style=\"border-collapse: collapse; width:100% ; border: 1px solid black;\n" +
"            background: yellow;\">\n" +
"                    <tr style=\"width:100px; border: 1px solid black;\"> \n" +
"                    <th style=\"width:100px; border: 1px solid black;\">الرقم</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">رقم نقطة البيع</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\">رقم موظف النقطة</th>\n" +
"                    <th style=\"width:100px; border: 1px solid black;\"> رقم الزبون</th>\n" +
                    "<th style=\"width:100px; border: 1px solid black;\"> المبلغ</th>\n"+
"                    <th style=\"width:100px; border: 1px solid black;\"> التاريخ</th>\n" +
"                </tr>\n" +
"               \n" +
"            </table>");
            
                while(rs.next()){
                    out.print("<table  style=\" border-collapse: collapse;width:100% ; border: 1px solid black;\n" +
"            background: red;\">");
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
    
            }
                 out.print("تمت بنجاح");
            }catch (SQLException sq){out.print("!هناك خطا في الاتصال");
            out.print(sq);
            } 
            catch (UnsupportedEncodingException e) {
            out.print(e);
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

    private String EdtSrchTxtValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

