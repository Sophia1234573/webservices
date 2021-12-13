<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

   <html>
      <head>
      <%@page import="javax.servlet.http.HttpServlet"%>
      <%@page import="javax.servlet.http.HttpServletRequest"%>
      <%@page import="javax.servlet.http.HttpServletResponse"%>

         <meta charset="utf-8">
             <meta name="viewport" content="width=device-width, initial-scale=1">
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
         <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>

         <title>   Admin Home Page   </title>
      </head>
      <body>
         <div class="container">
         <jsp:include page="headerAdmin.jsp"/>
         <p align="left">
                        <a href="/admin/showUserForm">Add new user</a>
         </p>
         <c:if test="${not empty param.canNotDelete}" >
         <center><h5>Sorry, but you cannot delete yourself!</h5></center>
         </c:if>

         <table class="table table-striped" border="1" cellpadding="5">
                 <thead>
                   <tr>
                     <th scope="col">Login</th>
                     <th scope="col">First Name</th>
                     <th scope="col">Last Name</th>
                     <th scope="col">Age</th>
                     <th scope="col">Role</th>
                     <th scope="col">Actions</th>
                   </tr>
                 </thead>
                 <tbody>
                 <c:forEach var="entry" items="${users}">

                 <tr>
                     <td>${entry.key.login}</td>
                     <td>${entry.key.firstName}</td>
                     <td>${entry.key.lastName}</td>
                     <td>${entry.value}</td>
                     <td>${entry.key.role.name}</td>
                     <td><a href="/admin/showUpdateUser?userId=${entry.key.id}" class="btn btn-primary"><b>Edit</b></a>

                     <c:if test="${entry.key.id == principalId}">
                     <button class="btn btn-danger" disabled><b>Delete</b></button>
                     </c:if>

                      <c:if test="${entry.key.id != principalId}">
                      <button data-href="/admin/deleteUser?userId=${entry.key.id}" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#confirmDelete"><b>Delete</b></button>
                      </c:if>

                     </td>
                 </tr>
                 </c:forEach>
                 </tbody>
             </table>

         <div class="modal fade" id="confirmDelete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
           <div class="modal-dialog modal-dialog-centered">
             <div class="modal-content">
               <div class="modal-header">
                 <h5 class="modal-title" id="exampleModalLabel">Confirm</h5>
                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
               Are you sure?
               </div>
               <div class="modal-footer">
                 <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                 <a class="btn btn-danger btn-ok">Yes</a>
               </div>
             </div>
           </div>
         </div>
<script>
          $('#confirmDelete').on('show.bs.modal', function(e) {
              $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
          });
          </script>

          <%
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Cache-Control", "no-store");
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
           %>
      </body>
   </html>

