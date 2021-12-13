<%@ page contentType="text/html; charset=utf-8" language="java"%>

 <!doctype html>
 <html lang="en">
 <head>
     <meta charset="utf-8">
     <meta name="viewport" content="width=device-width, initial-scale=1">
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

         <meta http-equiv="Content-Type"
            content="text/html; UTF-8">
         <title>   Error page   </title>
      </head>
      <body>

     <h1>Error Page</h1>
       <p>Application has encountered an error.</p>
         Failed URL: ${url}
         Exception:  ${exception.message}
             <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
         </c:forEach>

      </body>
   </html>

