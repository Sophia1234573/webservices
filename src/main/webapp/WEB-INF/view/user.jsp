<%@ page contentType="text/html; charset=utf-8" language="java"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <!doctype html>
 <html lang="en">
 <head>
     <meta charset="utf-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

       <%@page import="javax.servlet.http.HttpServlet"%>
       <%@page import="javax.servlet.http.HttpServletRequest"%>
       <%@page import="javax.servlet.http.HttpServletResponse"%>


         <meta http-equiv="Content-Type"
            content="text/html; UTF-8">
         <title>   User Logged Successfully   </title>
      </head>
      <body>

      <center>
      <h1> Hello, ${userFirstName}! </h1>
      </center>

      <p>
        <center> </br> </br>
        <h3> Click <a href="logout">here</a> to logout </h3>
        </center>
      </p>
  <%
  response.setHeader("Cache-Control", "no-cache");
  response.setHeader("Cache-Control", "no-store");
  response.setDateHeader("Expires", 0);
  response.setHeader("Pragma", "no-cache");
  %>
      </body>
   </html>