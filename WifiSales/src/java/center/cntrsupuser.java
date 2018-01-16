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
@WebServlet(name = "cntrsupuser", urlPatterns = {"/cntrsupuser"})
public class cntrsupuser extends HttpServlet {

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
                   try {
                 
                Connection conn=Db.connect();
                
               
                String emsqln=("Select * From employees");
                Statement emstn=conn.createStatement();
                ResultSet emrsn=emstn.executeQuery(emsqln);
                
                String sqlcommand=("Select * From salespoints");
                Statement st1=conn.createStatement();
                ResultSet rs1=st1.executeQuery(sqlcommand);
                String sqlPemplUsn=("Select EmplUserName From pointsemployees");
                Statement st2=conn.createStatement();
                ResultSet rs2=st2.executeQuery(sqlPemplUsn);
            if (session!=null){
                String name=null;
                try{
               
                String command =("Select EmplName From employees Where EmplUserName='"+session.getAttribute("user")+"'");
                Statement st=conn.createStatement();
              ResultSet rs=st.executeQuery(command);
              while (rs.next()){
              name=rs.getString(1);
              }
                } catch(SQLException E){
                    out.print(E);
                }
                 out.print("<p><h2>"+name+" مرحبا</h2></p>"
                        + "<p><a href='logout'>تسجيل الخروج</a></p>");
                out.print("<p><a href='centereditlogindatainterface'>تعديل بيانات الدخول</a></p>");
            out.println("<!DOCTYPE html>\n" +
"<!--\n" +
"To change this license header, choose License Headers in Project Properties.\n" +
"To change this template file, choose Tools | Templates\n" +
"and open the template in the editor.\n" +
"-->\n" +
"<html>\n" +
"    <head>\n" +
"        <title>WifiSales</title>\n" +
"        <meta charset=\"UTF-8\">\n" +
"        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n" +
"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js\"></script>\n" +
"<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n" +
"        <style>\n" +
"             footer {\n" +
"    \n" +
"    bottom:5%;\n" +
"    align-content: center;\n" +
"    \n" +
"    \n" +
"    width: 100%;\n" +
"}\n" +
"            @media print {\n" +
"  body * {\n" +
"    visibility: hidden;\n" +
"  }\n" +
"  #divTest * {\n" +
"    visibility: visible;\n" +
"  }\n" +
"  #divTest {\n" +
"    position: absolute;\n" +
"    left: 0;\n" +
"    top: 0;\n" +
"  }\n" +
"}\n" +
"            input[type=text], input[type=password]{\n" +
"                width: 100%;\n" +
"    padding: 12px 20px;\n" +
"    margin: 8px 0;\n" +
"    display: inline-block;\n" +
"    border: 1px solid #ccc;\n" +
"    box-sizing: border-box;\n" +
"            }\n" +
"            \n" +
".cancelbtn {\n" +
"    padding: 14px 20px;\n" +
"    background-color: #f44336;\n" +
"}\n" +
".signupbtn {\n" +
"    float: left;\n" +
"    width: 50%;\n" +
"}\n" +
".container {\n" +
"    padding: 16px;\n" +
"    width: 86%;\n" +
"}\n" +
".clearfix::after {\n" +
"    content: \"\";\n" +
"    clear: both;\n" +
"    display: table;\n" +
"}\n" +
"@media screen and (max-width: 300px) {\n" +
"   .signupbtn {\n" +
"       width: 50%;\n" +
"    }\n" +
"}\n" +
"            div.dtest{\n" +
"                width: 200px;\n" +
"                height: 50px;\n" +
"            }\n" +
"            a:link {\n" +
"                text-decoration: none;\n" +
"            }\n" +
"            .dropdown {\n" +
"                position: relative;\n" +
"                display: inline-block;\n" +
"                width: 200px;\n" +
"                height: 50px;\n" +
"            }\n" +
"            .dropdown-content{\n" +
"                display: none;\n" +
"    position: absolute;\n" +
"    background-color: aliceblue;\n" +
"    min-width: 160px;\n" +
"    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\n" +
"    padding: 12px 16px;\n" +
"    z-index: 1;\n" +
"            }\n" +
"            .dropdown:hover .dropdown-content {\n" +
"    display: block;\n" +
"}\n" +
"        table.t1 th{\n" +
"            border: 2px solid black;\n" +
"            background: red\n" +
"               \n" +
"        }\n" +
"            table.t1 td {\n" +
"            border: 1px solid black;\n" +
"            background: yellow;\n" +
"        }\n" +
"        div.dtable {\n" +
"            width: 100%;\n" +
"            height: 20px;\n" +
"            display: none;\n" +
"           \n" +
"            background: gold;\n" +
"            margin: 0 auto;\n" +
"                }\n" +
"        button.b1 {\n" +
"        \n" +
"             display: block;\n" +
"             width: 200px;\n" +
"             height: 50px;\n" +
"             background: aqua;\n" +
"        }\n" +
"        div.menu {\n" +
"            \n" +
"        }\n" +
"    </style>\n" +
"    </head>\n" +
"         \n" +
"    \n" +
"    <body>\n" +
 "\n");
                  
                    while(emrsn.next())
                    {
                        out.println("<datalist id=\"selectEmplId\">\n");
                   out.println("<option value=\""+emrsn.getObject(1).toString()+"\">"+emrsn.getObject(1).toString()+"</option>\n"); 
                    out.println("</datalist>");
                        out.println("<datalist id=\"selectEmplName\">\n");
                   out.println("<option value=\""+emrsn.getObject(2).toString()+"\">"+emrsn.getObject(2).toString()+"</option>\n"); 
                    out.println("</datalist>");
                    out.println("<datalist id=\"selectEmplNum\">\n");
                   out.println("<option value=\""+emrsn.getObject(3).toString()+"\">"+emrsn.getObject(3).toString()+"</option>\n"); 
                    out.println("</datalist>");
                    out.println("<datalist id=\"selectEmplPass\">\n");
                   out.println("<option value=\""+emrsn.getObject(4).toString()+"\">"+emrsn.getObject(4).toString()+"</option>\n"); 
                    out.println("</datalist>");
                     out.println("<datalist id=\"selectEmplAdress\">\n");
                   out.println("<option value=\""+emrsn.getObject(5).toString()+"\">"+emrsn.getObject(5).toString()+"</option>\n"); 
                    out.println("</datalist>");
                     out.println("<datalist id=\"selectEmplUserName\">\n");
                   out.println("<option value=\""+emrsn.getObject(6).toString()+"\">"+emrsn.getObject(6).toString()+"</option>\n"); 
                    out.println("</datalist>");
                     out.println("<datalist id=\"selectEmplPassw\">\n");
                   out.println("<option value=\""+emrsn.getObject(7).toString()+"\">"+emrsn.getObject(7).toString()+"</option>\n"); 
                    out.println("</datalist>");
                    }

out.println("       <script>\n" +
"             function insertdata() {\n" +
"    var vname=document.getElementById(\"Txname\").value;\n" +
"   var vnumber=document.getElementById(\"TxTelephon\").value;\n" +
"    var vadress=document.getElementById(\"TxAdress\").value;\n" +
        "    var psIdC=document.getElementById(\"TxtPasId\").value;\n" +
"    var vinsurance=document.getElementById(\"TxInsurance\").value;\n" +
"     var usn=document.getElementById(\"txtuserName\").value;\n" +
"   var psw=document.getElementById(\"textpassWord\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"   var parmtrs=\"Txtname=\"+vname+\"&TxtTelephon=\"+vnumber+\"&TxtAdress=\"+vadress+\"\\\n" +
"&TxtPasId=\"+psIdC+\"&TxtInsurance=\"+vinsurance+\"&txtuserName=\"+usn+\"&textpassWord=\"+psw+\"\";" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"           // document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +

"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/insert\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"    \n" +
"}\n" +
        " function searchPaddRev(){\n" +

" document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var xhttp=new XMLHttpRequest();\n" +
"    var vname=document.getElementById(\"AddRevenguePIdnamTxt\").value;\n" +
"    \n" +
"    var parms=\"AddRevenguePIdnamTxt=\"+vname+\"\";\n" +
"    xhttp.onreadystatechange=function (){\n" +
"       if(this.readyState==4&&this.status==200){\n" +
"document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"   };\n" +
"   xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/searchptoaddrevengue\",false);\n" +
"   xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"  xhttp.send(parms);\n"+
        "}\n"+
"function search(){\n" +
" //   document.getElementById(\"Salespoint\").style.display=\"inline\";\n" +
"  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var xhttp=new XMLHttpRequest();\n" +
"    var vname=document.getElementById(\"STxtName\").value;\n" +
"    var vtelephon=document.getElementById(\"STxtTelephon\").value;\n" +
"    var parms=\"STxtName=\"+vname+\"&STxtTelephon=\"+vtelephon+\"\";\n" +
"    xhttp.onreadystatechange=function (){\n" +
"        if(this.readyState==4&&this.status==200){\n" +
"            document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"            \n" +
"            \n" +
"        }\n" +
"    };\n" +
"     xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/search\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parms);\n" +
"}\n" +
"function update(){\n" +
"    \n" +
"     var vname=document.getElementById(\"RsEdtNamTxt\").value;\n" +
"    var vnumber=document.getElementById(\"RsEdtTelTxt\").value;\n" +
"    var vSnumber=document.getElementById(\"EdtSrcTxt\").value;\n" +
"    var vadress=document.getElementById(\"RsEdtAdrTxt\").value;\n" +
"    var vinsurance=document.getElementById(\"RsEdtInsurTxt\").value;\n" +
"     var usn=document.getElementById(\"UsEdtTxt\").value;\n" +
"    var psw=document.getElementById(\"PswEdtTxt\").value;\n" +
"    var psIdC=document.getElementById(\"RsEdtPssIdTxt\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"RsEdtNamTxt=\"+vname+\"&RsEdtTelTxt=\"+vnumber+\"&RsEdtAdrTxt=\"+vadress+\n" +
"            \"&RsEdtInsurTxt=\"+vinsurance+\" &EdtSrcTxt=\"+vSnumber+\"&UsEdtTxt=\"+usn+\"&PswEdtTxt=\"+psw+\"&RsEdtPssIdTxt=\"+psIdC+\"  \";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"          //document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/update\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
        " function EdtPointDetail(){\n" +
"                \n" +
"     document.getElementById(\"divTest\").style.display=\"none\";\n" +
" var pId=document.getElementById(\"EdtSrcTxt\").value;\n" +
" \n" +
"\n" +
"//var vinsurance=document.getElementById(\\\"\\\").value;\\n\n" +
"   var xhttp= new XMLHttpRequest();\n" +
"var parmtrs=\"EdtSrcTxt=\"+pId+\"\";\n" +
"   xhttp.onreadystatechange = function (){\n" +
"if(this.readyState==4&& this.status==200){\n" +
"    document.getElementById(\"divTest\").style.display=\"inline\";\n" +
" document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"\n" +
"\n" +
"       }\n" +
"};\n" +
"xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/searchpoint2edt\",false);\n" +
"  xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"xhttp.send(parmtrs); \n" +
"                \n" +
"            }"+
"function delet(){\n" +
"   // var vname=document.getElementById(\"\").value;\n" +
"    //var vnumber=document.getElementById(\"\").value;\n" +
"    var dbynumber=document.getElementById(\"DelSrcTxt\").value;\n" +
"    //var vadress=document.getElementById(\"\").value;\n" +
"    //var vinsurance=document.getElementById(\"\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"DelSrcTxt=\"+dbynumber+\"\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"          //document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/delete\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
        "  function DelPointDetail(){\n" +
"                \n" +
"     document.getElementById(\"divTest\").style.display=\"none\";\n" +
" var pId=document.getElementById(\"DelSrcTxt\").value;\n" +
" \n" +
"\n" +
"//var vinsurance=document.getElementById(\\\"\\\").value;\\n\n" +
"   var xhttp= new XMLHttpRequest();\n" +
"var parmtrs=\"DelSrcTxt=\"+pId+\"\";" +
"   xhttp.onreadystatechange = function (){\n" +
"if(this.readyState==4&& this.status==200){\n" +
"    document.getElementById(\"divTest\").style.display=\"inline\";\n" +
" document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"\n" +
"\n" +
"       }\n" +
"};\n" +
"xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/searchpoint2del\",false);\n" +
"  xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"xhttp.send(parmtrs); \n" +
"                \n" +
"            }"+
"function ifPexist(){\n" +
"    \n" +
"    var ifExnumber=document.getElementById(\"TxTelephon\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"TxTelephon=\"+ifExnumber+\"\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"          //document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/exist\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
"\n" +
"\n" +
"function salessrchbyId(){\n" +
"    document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var xhttp=new XMLHttpRequest();\n" +
"    var vid=document.getElementById(\"SrcSalesTxt\").value;\n" +
"    //var vdate=document.getElementById(\"DatSrchField\").value;\n" +
"    var parms=\"SrcSalesTxt=\"+vid+\"\";\n" +
"    xhttp.onreadystatechange=function (){\n" +
"        if(this.readyState==4&&this.status==200){\n" +
"            document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            document.getElementById(\"SalesDiv\").style.display=\"inline\";\n" +
"            document.getElementById(\"divTest\").style.display=\"block\";\n" +
"            \n" +
"            \n" +
"        }\n" +
"    };\n" +
"     xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/srchsalesbyId\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parms);  \n" +
"}\n" +
"function salessrchbyDate(){\n" +
"    document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var xhttp=new XMLHttpRequest();\n" +
"    //var vname=document.getElementById(\"SrcSalesTxt\").value;\n" +
"    var vdate=document.getElementById(\"DatSrchField\").value;\n" +
"    var parms=\"DatSrchField=\"+vdate+\"\";\n" +
"    xhttp.onreadystatechange=function (){\n" +
"        if(this.readyState==4&&this.status==200){\n" +
"            document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            document.getElementById(\"SalesDiv\").style.display=\"inline\";\n" +
"            document.getElementById(\"divTest\").style.display=\"block\";\n" +
"            \n" +
"            \n" +
"        }\n" +
"    };\n" +
"     xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/srchsalesbydat\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parms); \n" +
"    \n" +
"}\n" +
"\n" +
"function salessortbydateFrTo(){\n" +
"    document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var xhttp=new XMLHttpRequest();\n" +
"    var vname=document.getElementById(\"SrcByDateFrom\").value;\n" +
"    var vtelephon=document.getElementById(\"SrcByDateTo\").value;\n" +
"    var parms=\"SrcByDateFrom=\"+vname+\"&SrcByDateTo=\"+vtelephon+\"\";\n" +
"    xhttp.onreadystatechange=function (){\n" +
"        if(this.readyState==4&&this.status==200){\n" +
"            document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            document.getElementById(\"SalesDiv\").style.display=\"inline\";\n" +
"            document.getElementById(\"divTest\").style.display=\"block\";\n" +
"            \n" +
"            \n" +
"        }\n" +
"    };\n" +
"     xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/sortsalesfrtodat\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parms); \n" +
"}\n" +
"                \n" +
"                function ShowAddPointsDiv(){\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"block\";\n" +
"                document.getElementById(\"dAdd\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"SearchDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"DelDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"EditDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"                function ShowSearcPointsBlock(){\n" +
"                  document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"DivPointsBlock\").style.display=\"block\";\n" +
"                  document.getElementById(\"SearchDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"dAdd\").style.display=\"none\";\n" +
"                 document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"DelDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"EditDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"               \n" +
"            }\n" +
"            function ShowEditPointBlock(){\n" +
"                 document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"DivPointsBlock\").style.display=\"block\";\n" +
"                 document.getElementById(\"EditDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"dAdd\").style.display=\"none\";\n" +
"                document.getElementById(\"SearchDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"DelDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                 \n" +
"               \n" +
"            }\n" +
"            function ShowDeletePointsBlock(){\n" +
"                 document.getElementById(\"DivPointsBlock\").style.display=\"block\";\n" +
"              document.getElementById(\"dAdd\").style.display=\"none\";\n" +
"                document.getElementById(\"SearchDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"DelDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"EditDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"            function ShowSrchSalesByIdBlock(){\n" +
"                \n" +
"                 document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SrcSaleByNuDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"SrcSaleByDateDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SrtSaleByDateDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"               document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"            document.getElementById(\"DatSrchField\").value=new Date().toJSON().slice(0,10);\n" +
"            document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"            function ShowSrchhSalesByDateBlock(){\n" +
"                \n" +
"                 document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SrcSaleByNuDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SrcSaleByDateDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"SrtSaleByDateDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"               document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"            document.getElementById(\"DatSrchField\").value=new Date().toJSON().slice(0,10);\n" +
"            document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"            function ShowSrtSalesByDateBlock(){\n" +
"                \n" +
"                 document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"SrcSaleByNuDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SrcSaleByDateDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SrtSaleByDateDiv\").style.display=\"inline-block\";\n" +
"                document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"            document.getElementById(\"DatSrchField\").value=new Date().toJSON().slice(0,10);\n" +
"            document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"            document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"            function ShowRevengueAddBlock(){\n" +
"                \n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"DatAddRevenguePnumTxt\").value=new Date().toJSON().slice(0,10);\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"inline-block\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"none\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"             function ShowRevengueSrchBlock(){\n" +
"                \n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"DatAddRevenguePnumTxt\").value=new Date().toJSON().slice(0,10);\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"none\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"inline-block\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"             function ShowRevengueDelBlock(){\n" +
"                \n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"DatAddRevenguePnumTxt\").value=new Date().toJSON().slice(0,10);\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"none\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"none\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"inline-block\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"            function showAddEployee(){\n" +
"                 document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"DatAddRevenguePnumTxt\").value=new Date().toJSON().slice(0,10);\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"none\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"none\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"inline-block\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"            }\n" +
"              function EdtEmDivshow(){\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"DatAddRevenguePnumTxt\").value=new Date().toJSON().slice(0,10);\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"none\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"none\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"employeesDeldiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesEditdiv\").style.display=\"inline-block\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                                   \n" +
"            }\n" +
"            function DelEmDivshow(){\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"DatAddRevenguePnumTxt\").value=new Date().toJSON().slice(0,10);\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"none\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"none\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"employeesEditdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesDeldiv\").style.display=\"inline-block\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                                   \n" +
"            }\n" +
"             function ShowcardsuploadDiv(){\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"DatAddRevenguePnumTxt\").value=new Date().toJSON().slice(0,10);\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"none\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"none\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"none\";\n" +
"                       document.getElementById(\"cardsuploadDiv\").style.display=\"inline-block\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesEditdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesDeldiv\").style.display=\"none\";\n" +
"                \n" +
"            }\n" +
"           function ShowemplactivDive(){\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"inline-block\";\n" +
"                  document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                   document.getElementById(\"ReportsDiv\").style.display=\"none\";\n" +
"                    document.getElementById(\"AddRevengue\").style.display=\"none\";\n" +
"                     document.getElementById(\"SrchRevengue\").style.display=\"none\";\n" +
"                      document.getElementById(\"DelRevengue\").style.display=\"none\";\n" +
"                       document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"inline-block\";\n" +
"                       document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"employeesEditdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesDeldiv\").style.display=\"none\";\n" +
"            } \n" +
"           \n" +
"        </script>\n" +
"        <script>\n" +
"            function ShowSummReport(){\n" +
"                 document.getElementById(\"ReportsDiv\").style.display=\"block\";\n" +
"                 document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SummaryReportDiv\").style.display=\"block\";\n" +
"                document.getElementById(\"ReportFromToDateDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"TodayReportDiv\").style.display=\"none\";\n" +
"                  document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"employeesEditdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesDeldiv\").style.display=\"none\";\n" +
"            }\n" +
"            function ShowTodayReport(){\n" +
"                 document.getElementById(\"ReportsDiv\").style.display=\"block\";\n" +
"                 document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SummaryReportDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportFromToDateDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"TodayReportDiv\").style.display=\"block\";\n" +
"                 document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"employeesEditdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesDeldiv\").style.display=\"none\";\n" +
"            }\n" +
"            function ShowReportByDateFrTo(){\n" +
"                 document.getElementById(\"ReportsDiv\").style.display=\"block\";\n" +
"                 document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                document.getElementById(\"RevengueDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"DivPointsBlock\").style.display=\"none\";\n" +
"                document.getElementById(\"SalesDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"SummaryReportDiv\").style.display=\"none\";\n" +
"                document.getElementById(\"ReportFromToDateDiv\").style.display=\"block\";\n" +
"                 document.getElementById(\"TodayReportDiv\").style.display=\"none\";\n" +
"                 document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"emactivitiesDiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesdiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"cardsuploadDiv\").style.display=\"none\";\n" +
"                        document.getElementById(\"employeesEditdiv\").style.display=\"none\";\n" +
"                       document.getElementById(\"employeesDeldiv\").style.display=\"none\";\n" +
"            }\n" +
"            function PointSumReport(){\n" +
"                document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var pId=document.getElementById(\"PIdSumReport\").value;\n" +
"    //var reveAmnt=document.getElementById(\"AddRevenguePinsurTxt\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"PIdSumReport=\"+pId+\"\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         \n" +
"             document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"             document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"             \n" +
"           \n" +
"           \n" +
"           // var alrt=this.responseText;\n" +
"           // alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/sumreport\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"            }\n" +
        "            function PointSumReportbyname() {\n" +
"                document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var pNam=document.getElementById(\"PNamSumReport\").value;\n" +
"    //var reveAmnt=document.getElementById(\"AddRevenguePinsurTxt\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"PNamSumReport=\"+pNam+\"\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         \n" +
"             document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"             document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"             \n" +
"           \n" +
"           \n" +
"           // var alrt=this.responseText;\n" +
"           // alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/sumreportbynam\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"            }\n" +
"            function PointFrToReport(){\n" +
"                document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var pDateFr=document.getElementById(\"DateFr\").value;\n" +
"     var pDateTo=document.getElementById(\"DateTo\").value;\n" +
"     var pID=document.getElementById(\"IdTxt\").value;\n" +
"    //var reveAmnt=document.getElementById(\"AddRevenguePinsurTxt\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"IdTxt=\"+pID+\"&DateFr=\"+pDateFr+\"&DateTo=\"+pDateTo+\"\";" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(xhttp.readyState==4&& xhttp.status==200){\n" +
"         \n" +
"             document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"             document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"             \n" +
"           \n" +
"           \n" +
"          //  var alrt=this.responseText;\n" +
"            //alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/fromtoreport\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"            }\n" +
"             function PointTodayReport(){\n" +
"                document.getElementById(\"divTest\").style.display=\"none\";\n" +
"                var pId=document.getElementById(\"IdSelect\").value;\n" +
"                \n" +
"                \n" +
"    //var reveAmnt=document.getElementById(\"AddRevenguePinsurTxt\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"IdSelect=\"+pId+\"\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         \n" +
"             document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"             document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"             \n" +
"           \n" +
"           \n" +
"           // var alrt=this.responseText;\n" +
"           // alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/todayreport\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"            }\n" +
"             function SetDefValToEdtPoint(){\n" +
"  \n" +
"    document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var xhttp=new XMLHttpRequest();\n" +
"    //var vname=document.getElementById(\"SrcSalesTxt\").value;\n" +
"    var vnum=document.getElementById(\"EdtSrcTxt\").value;\n" +
"    var parms=\"EdtSrcTxt=\"+vnum+\"\";\n" +
"    xhttp.onreadystatechange=function (){\n" +
"        if(this.readyState==4&&this.status==200){\n" +
"            document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"           \n" +
"            document.getElementById(\"divTest\").style.display=\"block\";\n" +
"            \n" +
"            \n" +
"        }\n" +
"    };\n" +
"     xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/setdefvaltoedpoint\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parms); \n" +
"    \n" +
"     }\n" +
"                  \n" +
"        </script>\n" +
"        <script>\n" +
"            function addrevengue(){\n" +
"    var pointId=document.getElementById(\"AddRevenguePIdnamTxt\").value;\n" +
"    var revenDat=document.getElementById(\"DatAddRevenguePnumTxt\").value;\n" +
"    var reveAmnt=document.getElementById(\"AddRevenguePinsurTxt\").value;\n" +
"    var reveNot=document.getElementById(\"AddRevengueNoteTxt\").value;\n" +         
"    var xhttp= new XMLHttpRequest();\n" +
"   var parmtrs=\"AddRevenguePIdnamTxt=\"+pointId+\"&DatAddRevenguePnumTxt=\""
 + "+revenDat+\"&AddRevenguePinsurTxt=\"+reveAmnt+\"&AddRevengueNoteTxt=\"+reveNot+\"\";  "+
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"          //document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            //document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/addrevengue\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
"function Searchrevengue(){\n" +
"   \n" +
"    document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var revenId=document.getElementById(\"SrchPRevengueById\").value;\n" +
"        var revenDat=document.getElementById(\"SrchPRevengueByDate\").value;\n" +
"        var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"SrchPRevengueById='\"+revenId+\"'&SrchPRevengueByDate='\"+revenDat+\"'\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         \n" +
"             document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"             document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"             \n" +
"           }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/srchpointrevengue\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
"function ReturnBalance(){\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"    var pidd=document.getElementById(\"TxTelephon\").value;\n" +
"     var parmtrs=\"pid=\"+pidd+\"\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         // document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"       xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/returnvalue\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"     xhttp.send(parmtrs);\n" +
"}\n" +
"function delrevengue()\n" +
"{\n" +
"    var dbydate=document.getElementById(\"DelAddRevenguePnumTxt\").value;\n" +
"    var pid=document.getElementById(\"DelRevenguePIdTxt\").value;\n" +
"    //var vinsurance=document.getElementById(\"\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
"   var parmtrs=\"DelAddRevenguePnumTxt=\"+dbydate+\"&DelRevenguePIdTxt=\"+pid+\"\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         // document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/deleterevengue\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
"function AddEmployee(){\n" +
"     var emnam=document.getElementById(\"name\").value;\n" +
"     var emnum=document.getElementById(\"telephon\").value;\n" +
"     var empasnum=document.getElementById(\"passport\").value;\n" +
"     var emunam=document.getElementById(\"username\").value;\n" +
"     var empsw=document.getElementById(\"psw\").value;\n" +
"    var vadress=document.getElementById(\"adress\").value;\n" +
"    //var vinsurance=document.getElementById(\"\").value;\n" +
"    var xhttp= new XMLHttpRequest();\n" +
" var parmtrs=\"name=\"+emnam+\"&telephon=\"+emnum+\"&passport=\"+empasnum+\" &adress=\"+vadress+\"&username=\"+emunam+\"&psw=\"+empsw+\"\";"+
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         // document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"            var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"            \n" +
"        }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/addemployee\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
" function EditEmployee(){\n" +
"  var eid=document.getElementById(\"idEd\").value;\n" +
" var emnam=document.getElementById(\"nameEd\").value;\n" +
"var emnum=document.getElementById(\"telephonEd\").value;\n" +
"var empasnum=document.getElementById(\"passportEd\").value;\n" +
"var emaddred=document.getElementById(\"adressEd\").value;\n" +
" var emunam=document.getElementById(\"usernameEd\").value;\n" +
" var empsw=document.getElementById(\"pswEd\").value;\n" +
"\n" +
"//var vinsurance=document.getElementById(\\\"\\\").value;\\n\n" +
"   var xhttp= new XMLHttpRequest();\n" +
"var parmtrs=\"idEd=\"+eid+\"&nameEd=\"+emnam+\"&telephonEd=\"+emnum+\"&passportEd=\"+empasnum+\" \\n\\\n" +
"&adressEd=\"+emaddred+\"&usernameEd=\"+emunam+\"&pswEd=\"+empsw+\"\";\n" +
"   xhttp.onreadystatechange = function (){\n" +
"if(this.readyState==4&& this.status==200){\n" +
"// document.getElementById(\\\"divTest\\\").innerHTML=this.responseText;\n" +
"var alrt=this.responseText;\n" +
"            alert(alrt);\n" +
"       }\n" +
"};\n" +
"xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/edtcentrempl\",false);\n" +
"  xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"xhttp.send(parmtrs);\n" +
"}"+
"function Searchemplactivities(){\n" +
"   \n" +
"    document.getElementById(\"divTest\").style.display=\"none\";\n" +
"    var actvtype=document.getElementById(\"typ\").value;\n" +
"        var activDat=document.getElementById(\"operdate\").value;\n" +
"        var xhttp= new XMLHttpRequest();\n" +
"    var parmtrs=\"typ='\"+actvtype+\"'&operdate='\"+activDat+\"'\";\n" +
"    xhttp.onreadystatechange = function (){\n" +
"        if(this.readyState==4&& this.status==200){\n" +
"         \n" +
"             document.getElementById(\"divTest\").innerHTML=this.responseText;\n" +
"             document.getElementById(\"divTest\").style.display=\"inline\";\n" +
"             \n" +
"           }\n" +
"    };\n" +
"    xhttp.open(\"Post\",\"http://yemwfsales-env.us-east-2.elasticbeanstalk.com/serchemplactiv\",false);\n" +
"    xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" +
"    xhttp.send(parmtrs);\n" +
"}\n" +
" \n" +
"        </script>\n" +
"       \n" +
"             \n" +
"        <div  id=\"headDiv\" style=\"width: 100%; height: 40px; background-color:\n" +
"              blue;display: block; text-align: center\">\n" +
"            <h1 style=\"font-family:verdana; color: white\">العاصمة وايفاي</h1>\n" +
"        </div>\n" +
"        <div style=\"min-height: 800px\">\n" +
"        <div id=\"pageDiv\" style=\"display: inline-block;float: left;width: 86%;\n" +
"             background: aliceblue; margin: auto\">\n" +
"       \n" +
"        \n" +
"        <div id=\"DivPointsBlock\" style=\"width: 100%;display: none\">\n" +
"         \n" +
"        <div id=\"dAdd\" style=\"border: 1px solid black;width: 100%; background: azure; display: none ;\"> \n" +
"            <h2>اضافة نقطة مبيعات</h2>\n" +
"            <form action=\"javascript:;\"onsubmit=\"insertdata(this)\" method=\"post\">\n" +
"                <table style=\"border-collapse: collapse\">\n" +
"                    \n" +
"                    <tr>\n" +
"                        <td> اسم النقطة:</td>  <td> <input type=\"text\" name=\"Txtname\" placeholder=\"ادخل اسم النقطة\" id=\"Txname\" required> <br> </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td> رقم الهاتف :</td> <td><input type=\"text\" name=\n" +
"                                                          \"TxtTelephon\" placeholder=\"ادخل رقم الهاتف\" id=\"TxTelephon\"\n" +
"                                                         onchange=\"\" required> <br></td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>  العنوان:</td>   <td> <input type=\"text\" name= \n" +
"                                    \"TxtAdress\" placeholder=\"العنوان\" id=\"TxAdress\" required> <br> </td> </tr>\n" +
"                        <td>  رقم الهوية:</td>   <td> <input type=\"text\" name= \n" +
"                                    \"TxtPasId\" placeholder=\"رقم الهوية\" id=\"TxtPasId\" required> <br> </td> </tr>\n" +
"                    <tr>\n" +
"                        <td> سقف التامين:</td> <td><input type=\"text\" name=\n" +
"                                        \"TxtInsurance\" placeholder=\"سقف التامين لاتينية\" id=\"TxInsurance\" required> <br> </td></tr>\n" +
"                      <tr>\n" +
"                        <td> اسم المستخدم حروف لاتينية:</td> <td><input type=\"text\" name=\n" +
"                                        \"TxtInsurance\" placeholder=\"اسم المستخدم حروف لاتينية\"  id=\"txtuserName\" required> <br> </td></tr>\n" +
"                        <tr>\n" +
"                        <td> الرمز السري حروف لاتينية:</td> <td><input type=\"text\" name=\n" +
"                                                    \"TxtInsurance\" placeholder=\"الرمز السري حروف لاتينية\" id=\"textpassWord\" required> <br> </td></tr>\n" +
"\n" +
"                </table>\n" +
        " <tr>\n" +
"             <td>\n" +
"      <input type=\"submit\" style=\"width: 300px;height: 50px;\" value=\"اضف\">\n" +
"         </tr>"+
"                       </form>\n" +
"            </div>\n" +
"          \n" +
"            <div id=\"SearchDiv\" style=\"width: 100%;border: 1px solid black; background: antiquewhite; display: none;\">\n" +
 "<p><span style=\"color:blue;font-weight:bold\">بحث نقطة</span></p>"+
"                    <table>\n" +
"                    <tr>\n" +
"                        <td> <input type=\"text\" name=\"STxtNam\" list=\"selectClientname\" "
        + "value=\"\" placeholder=\"ادخل الاسم هنا\" id=\"STxtName\" onkeyup=\"search()\"> </td><td> ادخل الاسم هنا</td>\n"); 
                  
                    while(rs1.next()){
                     out.println("<datalist id=\"selectClientId\">");
                    out.println("<option value=\""+rs1.getObject(1).toString()+"\">"+rs1.getObject(1).toString()+"</option>\n");
                    out.println("</datalist>");
                    out.println("<datalist id=\"selectClientname\">");
                    out.println("<option value=\""+rs1.getObject(2).toString()+"\">"+rs1.getObject(2).toString()+"</option>\n");
                    out.println("</datalist>");
                     out.println("<datalist id=\"selectClientTel\">");
                    out.println("<option value=\""+rs1.getObject(3).toString()+"\">"+rs1.getObject(3).toString()+"</option>\n");
                    out.println("</datalist>");
                         out.println("<datalist id=\"selectClientAdress\">");
                    out.println("<option value=\""+rs1.getObject(4).toString()+"\">"+rs1.getObject(4).toString()+"</option>\n");
                    out.println("</datalist>");   
                      out.println("<datalist id=\"selectClientPass\">");
                    out.println("<option value=\""+rs1.getObject(5).toString()+"\">"+rs1.getObject(5).toString()+"</option>\n");
                    out.println("</datalist>");
                      out.println("<datalist id=\"selectClientInsurR\">");
                    out.println("<option value=\""+rs1.getObject(7).toString()+"\">"+rs1.getObject(7).toString()+"</option>\n");
                    out.println("</datalist>");
                    }
                     while(rs2.next()){
                     out.println("<datalist id=\"selectEmplUsrName\">");
                    out.println("<option value=\""+rs2.getObject(1).toString()+"\">"+rs2.getObject(1).toString()+"</option>\n");
                    out.println("</datalist>");
                     }
out.println("          </tr> \n" +
"                     <tr>\n" +
"                        <td> <input type=\"text\" name=\"STxtTelphon\" list=\"selectClientId\" value=\"\" placeholder=\"ادخل رقم النقطة هنا\" "
        + "id=\"STxtTelephon\" onkeyup=\"search()\"></td> </td><td> ادخل رقم النقطة هنا</td>\n");
       
      
out.println(" </tr>\n" +
"                    </table>\n" +
"                     \n" +
"            </div>\n" +
"              <div id=\"EditDiv\" style=\"width: 100%;border: 1px solid black;\n" +
"                 background: antiquewhite; display: none;\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">تعديل بيانات نقطة</span></p>"+
        "<form action=\"javascript:;\"onsubmit=\"update(this)\" method=\"post\">"+
"                  <table>    \n" +
"                  <tr>\n" +
"                      <td> \n" +
"  <input type=\"text\" id=\"EdtSrcTxt\" list=\"selectClientId\" "
        + " placeholder=\"ادخل رقم النقطة كاملا هنا\" onchange=\"EdtPointDetail()\" required>\n" );
         

                   out.println("</td> </tr>   \n"); 
out.println(" <tr><td> <input type=\"text\" list=\"selectClientname\" "
        + "placeholder=\"ادخل الاسم\" id=\"RsEdtNamTxt\" required>");
        
         out.println("</td> </tr>\n"+
"                          <tr><td><input type=\"text\" placeholder=\"ادخل رقم الهاتف\" "
                 + "id=\"RsEdtTelTxt\" list=\"selectClientTel\" required></td></tr>\n" +
"                          <tr><td><input type=\"text\" placeholder=\"ادخل العنوان\" "
                 + "id=\"RsEdtAdrTxt\" list=\"selectClientAdress\" required></td></tr>\n" +
"                           <tr><td><input type=\"text\" "
                 + "placeholder=\"رقم الهوية\" id=\"RsEdtPssIdTxt\" list=\"selectClientPass\" required></td></tr>\n" +
"                          <tr><td> <input type=\"text\" placeholder=\"سقف التامين لاتينية\" "
                 + "id=\"RsEdtInsurTxt\" list=\"selectClientInsurR\" required>  </td>\n" +
"                      </tr>\n" +
"                     </table>\n" +
"                  <table>\n" +
"                       <tr>\n" +
"                          <td><input type=\"text\" placeholder=\"اسم المستخدم لاتينية\" "
                 + "id=\"UsEdtTxt\" list=\"selectEmplUsrName\" required></td> </tr>\n" +
"                          <tr><td><input type=\"text\" placeholder=\"كلمة السر لاتينية\" "
                 + "id=\"PswEdtTxt\" required></td></tr>\n" +

"                  </table>\n" +
"                  <table>\n" +
                 
"                      <tr><td> <input type=\"submit\" style=\"width: 100px;height: 50px;\"  value=\"عدل\"></td></tr>\n" +
"                  </table>\n" +
                 "</form>"+
"                  \n" +
"            </div>\n" +
"              <div id=\"DelDiv\" style=\"width: 100%;border: 1px solid black;\n" +
"                 background: antiquewhite; display: none;\">\n" +
       "<p><span style=\"color:blue;font-weight:bold\">حذف نقطة</span></p>"+
 "<form action=\"javascript:;\"onsubmit=\"delet(this)\" method=\"post\">"+
"                <table>\n" +
"                      <tr>\n" +
"                          <td> \n" +
"                              \n" +
"<input type=\"text\" id=\"DelSrcTxt\" list=\"selectClientId\" "
                 + "placeholder=\"ادخل رقم النقطة كاملا\" onchange=\"DelPointDetail()\" required>\n" +
"                              \n" +
"                          </td>\n" +
"                      </tr>\n" +
"                </table>\n" +
"                     \n" +
"                      <table>\n" +
"                      <tr><td> <input type=\"submit\"  style=\"width: 100px;height: 50px;\"  value=\"احذف\"></td></tr>\n" +
"                  </table>\n" +
                 "</form>"+
"            </div>\n" +
"        </div>\n" +
"        <div id=\"SalesDiv\" class=\"dtable\">\n" +
"            <div id=\"SrcSaleByNuDiv\" style=\"display: none\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">بحث مبيعات برقم النقطة</span></p>"+
"                <table class=\"t1\" style=\"width:100%\">\n" +
"                <tr>\n" +
"                    \n" +
"<td><form method=\"post\"> <input type=\"text\" list=\"selectClientId\" placeholder=\"ادخل رقم النقطة كاملا\"" +
"                         id=\"SrcSalesTxt\"\n" +
"                                                     onchange=\"salessrchbyId()\"> </form></td>\n" +
"                                            \n" +
"                   \n" +
"                </tr>\n" +
"                                              \n" +
"            </table>\n" +
"            </div>\n" +
"            <div id=\"SrcSaleByDateDiv\" style=\"display: none\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">البحث بالتاريخ</span></p>"+
"               <table class=\"t1\" style=\"width:100%\">\n" +
"                <tr>\n" +
"                    \n" +
"                    <td><form method=\"post\"> ابحث بالتاريخ:\n" +
"                            <input id=\"DatSrchField\" type=\"date\" \n" +
"                                   onchange=\"salessrchbyDate()\"  > </form></td>\n" +
"                               \n" +
"                   \n" +
"                </tr>\n" +
"                                              \n" +
"            </table> \n" +
"            </div>\n" +
"            <div id=\"SrtSaleByDateDiv\" style=\"display: none\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">فرز حسب التارخ من والى</span></p>"+
"                 <table class=\"t1\" style=\"width:100%\">\n" +
"                <tr>\n" +
"                    \n" +
"                  <td> <form method=\"post\"> فرز حسب التاريخ :\n" +
"                          <input type=\"date\" id=\"SrcByDateFrom\" onchange=\"salessortbydateFrTo()\">\n" +
"                  <input type=\"date\" id=\"SrcByDateTo\" onchange=\"salessortbydateFrTo()\"> </form></td>\n" +
"                        \n" +
"                   \n" +
"                </tr>\n" +
"                                              \n" +
"            </table>\n" +
"            </div>\n" +
"                   \n" +
"        </div>\n" +
"        <div id=\"RevengueDiv\" style=\"display: none;\">\n" +
                
"            <div id=\"AddRevengue\" style=\"display:none\">\n" +
 "<p><span style=\"color:blue;font-weight:bold\">اضافة ايراد</span></p>"+
  "<form action=\"javascript:;\"onsubmit=\"addrevengue(this)\" method=\"POST\">"+
"                        <table class=\"t1\" style=\"width:100%;\">\n" +
"                <tr> \n" +
"<td> <input type=\"text\" list=\"selectClientId\" id=\"AddRevenguePIdnamTxt\" "
                 + "placeholder=\"رقم النقطة\" onchange=\"searchPaddRev()\" required></td>\n" +
"                    <td><input type=\"date\" id=\"DatAddRevenguePnumTxt\"></td>\n" +
"                    <td><input type=\"text\" id=\"AddRevenguePinsurTxt\" "
                 + "placeholder=\"المبلغ المورد\" required></td>\n" +
"                    <td><input type=\"text\" id=\"AddRevengueNoteTxt\" placeholder=\"اضف ملاحظة عن الايراد\"></td>\n" +
"               \n" +
"                </tr>" +
"                </table>\n" +
"            <input type=\"submit\" id=\"BtAddNewRevengue\" style=\"width: 100px;height: 50px;\" value=\"اضف\">\n" +
"             \n" +
                 "</form>"+
"            </div>\n" +
"            <div id=\"SrchRevengue\" style=\"display:none\">\n" +
 "<p><span style=\"color:blue;font-weight:bold\">بحث الايرادات</span></p>"+
                 "<form action=\"javascript:;\"onsubmit=\"Searchrevengue(this)\" method=\"POST\">"+
"                        <table class=\"t1\" style=\"width:100%;\">\n" +
"                <tr> \n" +
"                    <td>  <input type=\"text\" list=\"selectClientId\" "
                 + "id=\"SrchPRevengueById\" placeholder=\"ادخل رقم النقطة كاملا\" "
                 + " onchange=\"Searchrevengue()\" required></td>\n" +
"                    <td> <input type=\"date\" id=\"SrchPRevengueByDate\" "
                 + "onchange=\"Searchrevengue()\" required> </td>\n" +
"                    </tr>\n" +
"                </table>\n" +
                 "<table>"
                 + "<tr><td>"
                 + "<input type=\"submit\" style=\"width: 100px;height: 50px; \">"
                 + "</td></tr>"
                 + "</table>"+
                 "</form>"+

"                 </div>\n" +
"            <div id=\"DelRevengue\" style=\"display:none\">\n" +
 "<p><span style=\"color:blue;font-weight:bold\">حذف ايرادات</span></p>"+
                 "<form action=\"javascript:;\"onsubmit=\"delrevengue(this)\" method=\"POST\">"+
"                        <table class=\"t1\" style=\"width:100%;\">\n" +
"                <tr> \n" +
"                    \n" +
"                    <td><input type=\"date\" id=\"DelAddRevenguePnumTxt\" required></td>\n" +
                 "<td><input type=\"text\" id=\"DelRevenguePIdTxt\" list=\"selectClientId\" required></td>\n" +
"                   \n" +
"               \n" +
"                </tr>\n" +
"                </table>\n" +
                 "<table>"
                 + "<tr><td> <input type=\"submit\" style=\"width: 100px;height: 50px; \"></td></tr>"
                 + "</table>"+
                 "</form>"+

"            </div>\n" +
"                        \n" +
"        \n" +
"        </div>\n" +
"            <div id=\"ReportsDiv\" style=\"display:none;\">\n" +
"                <div id=\"SummaryReportDiv\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">التقرير الاجمالي</span></p>"+
"                <table>\n" +
"                      <tr>\n" +
"                          <td>اختر الرقم من هنا</td> <td> \n" +
"                             <form method=\"post\">\n" +
 "   <input type=\"text\" list=\"selectClientId\" id=\"PIdSumReport\""
                 + " placeholder=\"ادخل رقم النقطة كاملا\"  onchange=\"PointSumReport()\">"+     
"                              </form>\n" +
"                          </td>\n" +
"                          <td>اختر الاسم من هنا</td><td> \n" +
"                              <form method=\"post\">\n" +
"<input type=\"text\" list=\"selectClientname\" id=\"PNamSumReport\" "
                 + "placeholder=\"ادخل اسم النقطة كاملا\"  onchange=\"PointSumReportbyname()\">"+
"                              </form>\n" +
"                          </td>\n" +
"                      </tr>\n" +
"              </table>\n" +
"                </div>\n" +
"                 <div id=\"TodayReportDiv\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">التقرير اليومي</span></p>"+
"                <table>\n" +
"                      <tr>\n" +
"                           <td>اختر الرقم من هنا</td> <td> \n" +
"                             <form method=\"post\">\n" +
"<input type=\"text\" list=\"selectClientId\" id=\"IdSelect\" placeholder=\"ادخل رقم النقطة كاملا\"  onchange=\"PointTodayReport()\">"+
"                              </form>\n" +
"                          </td>\n" +
"                             </tr>\n" +
"              </table>\n" +
"                </div>\n" +
"                 <div id=\"ReportFromToDateDiv\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">التقرير من والى</span></p>"+
"                <table>\n" +
"                      <tr>\n" +
"                           <td>ادخل رقم النقطة هنا</td> <td style=\"width: 200px\"> \n" +
"                             <form method=\"post\">\n" +
"                                 \n" +
"<input id=\"IdTxt\"type=\"text\" list=\"selectClientId\" onchange=\"PointFrToReport()\">\n" +
"                              </form>\n" +
"                          </td>\n" +
"                           <td>اختر التاريخ من هنا</td> <td style=\"width: 200px\"> \n" +
"                             <form method=\"post\" >\n" +
"                               \n" +
"                                 <input id=\"DateFr\"  type=\"date\" onchange=\"PointFrToReport()\">\n" +
"                              </form>\n" +
"                          </td>\n" +
"                          <td>اختر التاريخ من هنا</td><td style=\"width: 200px\"> \n" +
"                              <form method=\"post\">\n" +
"                                  <input id=\"DateTo\" type=\"date\" onchange=\"PointFrToReport()\">\n" +
"                              </form>\n" +
"                          </td>\n" +
"                      </tr>\n" +
"              </table>\n" +
"                </div>\n" +
"            </div>\n" +
"            <div id=\"employeesdiv\" style=\"display:none; width: 86%\">\n" +
"                <h2>اضافة موظف</h2>\n" +
"                <form action=\"javascript:;\"onsubmit=\"AddEmployee(this)\" method=\"POST\" style=\"border:1px solid #ccc\">\n" +
"  <div class=\"container\">\n" +
"    <label><b>الاسم</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل اسم الموظف الرباعي\" name=\"name\" id=\"name\" required>\n" +
"     <label><b>رقم الهاتف</b></label>\n" +
"     <input type=\"text\" placeholder=\"ادخل رقم هاتف الموظف\" name=\"telephon\" id=\"telephon\" required>\n" +
"    <label><b>رقم البطاقة او الجواز</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل رقم البطاقة او الجواز\" name=\"passport\" id=\"passport\" required>\n" +
"    <label><b>العنوان</b></label>\n" +
"    <input type=\"text\" placeholder=\"العنوان\" name=\"adress\" id=\"adress\" required>\n" +
"     <label><b>اسم المستخدم</b></label>\n" +
"     <input type=\"text\" placeholder=\"ادخل اسم المستخدم حروف لاتينية\" name=\"username\" id=\"username\" required>\n" +
"     <label><b>كلمة السر</b></label>\n" +
"     <input type=\"password\" placeholder=\"ادخل كلمة السر حروف لاتينية\" name=\"psw\" id=\"psw\" required>\n" +
"     \n" +
"    \n" +
"        <input type=\"submit\"  class=\"signupbtn\"  value=\"اضف\">\n" +
"   \n" +
"  </div>\n" +
"</form>\n" +
"                \n" +
"            </div>\n" +
"                <div id=\"employeesEditdiv\" style=\"display:none; width: 86%\">\n" +
"                <h2>تعديل موظف</h2>\n" +
"                <form action=\"javascript:;\"onsubmit=\"EditEmployee(this)\" method=\"POST\" style=\"border:1px solid #ccc\">\n" +
"  <div class=\"container\">\n" +
"      <label><b>رقم الموظف</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل رقم الموظف id\" "
                 + "list=\"selectEmplId\" name=\"idEd\" id=\"idEd\" required>\n" +
"    <label><b>الاسم</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل اسم الموظف الرباعي\" name=\"nameEd\" "
                 + "id=\"nameEd\" list=\"selectEmplName\" required>\n" +
"     <label><b>رقم الهاتف</b></label>\n" +
"     <input type=\"text\" placeholder=\"ادخل رقم هاتف الموظف\" "
                 + "name=\"telephonEd\" id=\"telephonEd\" list=\"selectEmplNum\" required>\n" +
"    <label><b>رقم البطاقة او الجواز</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل رقم البطاقة او الجواز\" "
                 + "name=\"passportEd\" id=\"passportEd\" list=\"selectEmplPass\" required>\n" +
                 "    <label><b>العنوان</b></label>\n" +
"    <input type=\"text\" placeholder=\"العنوان\" "
                 + "name=\"adressEd\" id=\"adressEd\" list=\"selectEmplName\""
                 + " list=\"selectEmplAdress\" required>\n" +
"    \n" +
"     <label><b>اسم المستخدم</b></label>\n" +
"     <input type=\"text\" placeholder=\"ادخل اسم المستخدم حروف لاتينية\" "
                 + "name=\"usernameEd\" id=\"usernameEd\" list=\"selectEmplUserName\" required>\n" +
"     <label><b>كلمة السر</b></label>\n" +
"     <input type=\"password\" placeholder=\"ادخل كلمة السر حروف لاتينية\" "
                 + "name=\"pswEd\" id=\"pswEd\" list=\"selectEmplPassw\" required>\n" +
"     \n" +
"    \n" +
"     <input type=\"submit\"   value=\"عدل\" style=\"width: 150px; height: 30px;\">\n" +
"     </div>\n" +
"</form>\n" +
"                \n" +
"            </div>\n" +
"              <div id=\"employeesDeldiv\" style=\"display:none; width: 86%\">\n" +
"                <h2>حذف موظف</h2>\n" +
"                <form action=\"delcentrempl\" method=\"POST\" style=\"border:1px solid #ccc\">\n" +
"  <div class=\"container\">\n" +
"      <label><b>رقم الموظف</b></label>\n" +
"    <input type=\"text\" placeholder=\"ادخل رقم الموظف id\" "
                 + "list=\"selectEmplId\" name=\"idDel\" id=\"idDel\" required>\n" +
"         <input type=\"submit\"   value=\"حذف\" style=\"width: 150px; height: 30px;\">\n" +
"     </div>\n" +
"</form>\n" +
"                \n" +
"            </div>\n" +
"            <div id=\"emactivitiesDiv\" style=\"display: none\" onchange=\"Searchemplactivities()\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">انشطة الموظفين</span></p>"+
"                <table>\n" +
"                    <tr>\n" +
"                        <td>  <form  method=\"post\">\n" +
"                                \n" +
"                                  <input type=\"text\" name=\"cid\" list=\"typop\" id=\"typ\"/>\n" +
"                                  <datalist id=\"typop\">\n" +
"                                   <option value=\"ايراد\">ايراد</option>\n" +
"                                    <option value=\"حذف ايراد\">حذف ايراد</option>\n" +
"                                     <option value=\"اضافة نقطة\">اضافة نقطة</option>\n" +
"                                     <option value=\"تعديل نقطة\">تعديل نقطة</option>\n" +
"                                     <option value=\"حذف نقطة\">حذف نقطة</option>\n" +
"                                      </datalist>\n" +
"                                    \n" +
"                              </form></td>\n" +
"                              <td>:اختر نوع العملية من هنا</td>\n" +
"                              <td><form>\n" +
"                                      <input id=\"operdate\" type=\"date\" style=\"width: 200px\" onchange=\"Searchemplactivities()\">\n" +
"                                  </form></td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </div>\n" +
"             <div id=\"cardsuploadDiv\" style=\"display: none;\">\n" +
                     "<p><span style=\"color:blue;font-weight:bold\">تحميل الكروت الى النظام</span></p>"+
"                <form action=\"upload\" method=\"post\" enctype=\"multipart/form-data\">\n" +
"   \n" +
"    <input type=\"file\" name=\"file\" />\n" +
"    <input type=\"submit\" value=\"ارفع\" style=\"width:200px\" />\n" +
"</form>\n" +
"            </div>\n" +
"        <div class=\"dtable\" id=\"divTest\" style=\"width: 100%\"></div>\n" +
"        </div>\n" +
"        <div id=\"menueDiv\" class=\"menu\" style=\"display:inline-block;float: right;width: 14%;\n" +
"             height: 100%;background-color: deeppink;margin: auto; text-align: center\" >\n" +
"            <h4>:القائمة</h4>\n" +
"              <div class=\"dropdown\"> \n" +
"            <button id=\"btShowPointsBlock\" class=\"btn btn-default\" style=\"width: 170px; height: 50px\">نقاط البيع</button>\n" +
"             <div class=\"dropdown-content\">\n" +
"                 <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowAddPointsDiv()\">اضافة</button>\n" +
"                 <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowEditPointBlock()\">تعديل</button>\n" +
"                   <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowSearcPointsBlock()\">بحث</button>\n" +
"                    <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowDeletePointsBlock()\">حذف</button>\n" +
"              </div>\n" +
"            </div>\n" +
"             <div class=\"dropdown\"> \n" +
"            <button id=\"btShowRevengueBlock\" class=\"btn btn-default\" style=\"width: 170px;height: 50px\" onclick=\"ShowRevengueBlock()\">الايرادات</button>\n" +
"            <div class=\"dropdown-content\">\n" +
"                <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowRevengueAddBlock()\">اضافة</button>\n" +
"                <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowRevengueSrchBlock()\">بحث</button>\n" +
"                <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowRevengueDelBlock()\">حذف</button>\n" +
"              </div>\n" +
"             </div>\n" +
"             <div class=\"dropdown\"> \n" +
"              <button id=\"btShowSalesBlock\" class=\"btn btn-default\" style=\"width:\n" +
"                      170px;height: 50px\" >المبيعات</button>\n" +
"              <div class=\"dropdown-content\">\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowSrchSalesByIdBlock()\">البحث بالرقم</button>\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowSrchhSalesByDateBlock()\">بحث بالتاريخ</button>\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowSrtSalesByDateBlock()\">فرز حسب التاريخ</button>\n" +
"              </div>\n" +
"              </div>\n" +
"             <!-- <a class=\"b1\" href=\"default.asp\" target=\"_blank\"><span style=\"color:blue;font-weight:bold\">اضغط هنا</span></a>-->\n" +
"             <!--<button class=\"btn btn-default\" style=\"width:200px;height: 50px;\">للتجريب</button>-->\n" +
"          \n" +
"              <div class=\"dropdown\">    \n" +
"                  <button id=\"Reportsbtn\" class=\"btn btn-default\" style=\"width: 170px;height: 50px\">التقارير</button>\n" +
"              <div class=\"dropdown-content\">\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowTodayReport()\">التقرير اليومي</button>\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowReportByDateFrTo()\">التقرير من والى</button>\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowSummReport()\">التقرير الاجمالي</button>\n" +
"              </div>\n" +
"              </div>\n" +
"             <div class=\"dropdown\">    \n" +
"                  <button id=\"Reportsbtn\" class=\"btn btn-default\" style=\"width: 170px;height: 50px\">ادارة الموظفين</button>\n" +
"              <div class=\"dropdown-content\">\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"showAddEployee()\">اضافة موظف</button>\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"EdtEmDivshow()\">تعديل موظف</button>\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"DelEmDivshow()\">حذف موظف</button>\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowemplactivDive()\">انشطة الموظفين</button>\n" +
"              </div>\n" +
"              </div>\n" +
"               <div class=\"dropdown\">    \n" +
"                  <button id=\"AddCardsDiv\" class=\"btn btn-default\" style=\"width: 170px;height: 50px\">قسايم الاشتراك</button>\n" +
"              <div class=\"dropdown-content\">\n" +
"                  <button class=\"b1\" style=\"width: 150px;height: 50px\" onclick=\"ShowcardsuploadDiv()\">رفع قسايم</button>\n" +
"                  </div>\n" +
"              </div>\n" +
"        </div>\n" +
"        </div>\n" +
"         <center><footer style=\"margin: 0 auto\">\n" +
"            <table>\n" +
"                 <tr>\n" +
"                     <td><h3>736108228/+79376282411</h3></td>\n" +
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
            out.print("يجب عليك الدخول اولا "+"<a href='LoginCenter.html'>تسجيل الدخول </a>");
            }
           
                   } catch (SQLException ex) { 
                   out.print(ex);
                   
                   } 
                    catch (Exception ex) { 
                   out.print(ex);
                   
                   } 
               }    }

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
