<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>


<!DOCTYPE html>
<html lang="en">

   <html>
      <head>
      <%@page import="javax.servlet.http.HttpServlet"%>
      <%@page import="javax.servlet.http.HttpServletRequest"%>
      <%@page import="javax.servlet.http.HttpServletResponse"%>

         <meta charset="utf-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">

         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
         <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

         <title>   ${titleName}   </title>

         <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/css/bootstrap-datepicker.css" type="text/css" />
         <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker.js" type="text/javascript"></script>

         <script type="text/javascript">
                 $(function () {
                     $('#birthday').datepicker({
                         format: "dd/mm/yyyy"
                     });
                 });
         </script>

         <style>
                 label.error.fail-alert {
         border: 2px solid red;
         border-radius: 4px;
         line-height: 1;
         padding: 2px 0 6px 6px;
         background: #ffe6eb;
         }
         input.valid.success-alert {
         border: 2px solid #4CAF50;
         color: green;
         }
         label.required:after
         {
             color: red;
             content: " *";
         }
        </style>
      </head>

      <body>
        <div class="container">
        <jsp:include page="headerAdmin.jsp"/>

        <c:if test="${isEditUser}">


            <form:form id="basic-form" action="editUser" method="post" modelAttribute="user">

            <div class="mb-3">
                <form:hidden class="form-control" id="id" name="id" path="id" />
            </div>
           <div class="mb-3">
                <form:label path="login" class="control-label">Login</form:label>
                <form:input readonly="true" class="form-control" path="login" />
            </div>

           <div class="mb-3">
                <form:label path="password" class="control-label">Password</form:label>
                <form:password  class="form-control" id="password" name="password" path="password" />
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="confirmPassword" class="control-label">Password</form:label>
                <form:password  class="form-control" id="confirmPassword" name="confirmPassword" path="confirmPassword" />
                <form:errors path="confirmPassword" cssClass="error" />
            </div>
            <form:hidden class="form-control" id="password" name="password" path="previousPassword" />

            <div class="mb-3">
                <form:label path="email" class="control-label required">Email</form:label>
                <form:input required="required" class="form-control" id="email" name="email" path="email" />
                <form:errors path="email" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="firstName" class="control-label required">First Name</form:label>
                <form:input required="required" class="form-control" id="firstName" name="firstName" path="firstName" />
                <form:errors path="firstName" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="lastName" class="control-label required">Last Name</form:label>
                <form:input required="required" class="form-control" id="lastName" name="lastName" path="lastName" />
                <form:errors path="lastName" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="birthday" class="control-label required">Birthday</form:label>
                <form:input required="required" class="form-control" id="birthday" name="birthday" path="birthday" />
                <form:errors path="birthday" cssClass="error" />
            </div>

            <c:if test="${isPrincipal}">
            <div class="mb-3">
                <form:label path="role.name" class="control-label required">Role</form:label>
                <form:select class="form-select" id="role.name" name="role.name" path="role.name">
                <form:option value="admin" label="Admin" />
                </form:select>
            </div>
            </c:if>

            <c:if test="${!isPrincipal}">
            <div class="mb-3">
                <form:label path="role.name" class="control-label required">Role</form:label>
                <form:select class="form-select" id="role.name" name="role.name" path="role.name">
                <form:option value="user" label="User" />
                <form:option value="admin" label="Admin" />
                </form:select>
            </div>
            </c:if>

            <button type="submit" class="btn btn-primary">Ok</button>
            <a href="/admin" class="btn btn-secondary">Cancel</a>
        </form:form>
        </c:if>

        <c:if test="${isAddUser}"><form:form id="basic-form" action="addUser" method="post" modelAttribute="user">

            <div class="mb-3">
                <form:hidden class="form-control" id="id" name="id" path="id" />
            </div>

            <div class="mb-3">
                <form:label path="login" class="control-label required">Login</form:label>
                <form:input required="required" class="form-control" id="login" name="login" path="login" />
                <form:errors path="login" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="password" class="control-label required">Password</form:label>
                <form:password required="required" class="form-control" id="password" name="password" path="password" />
                <form:errors path="password" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="confirmPassword" class="control-label required">Password</form:label>
                <form:password required="required" class="form-control" id="confirmPassword" name="confirmPassword" path="confirmPassword" />
                <form:errors path="confirmPassword" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="email" class="control-label required">Email</form:label>
                <form:input required="required" class="form-control" id="email" name="email" path="email" />
                <form:errors path="email" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="firstName" class="control-label required">First Name</form:label>
                <form:input required="required" class="form-control" id="firstName" name="firstName" path="firstName" />
                <form:errors path="firstName" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="lastName" class="control-label required">Last Name</form:label>
                <form:input required="required" class="form-control" id="lastName" name="lastName" path="lastName" />
                <form:errors path="lastName" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="birthday" class="control-label required">Birthday</form:label>
                <form:input required="required" class="form-control" id="birthday" name="birthday" path="birthday" />
                <form:errors path="birthday" cssClass="error" />
            </div>

            <div class="mb-3">
                <form:label path="role.name" class="control-label required">Role</form:label>
                <form:select class="form-select" id="role.name" name="role.name" path="role.name">
                <form:option value="user" label="User" />
                <form:option value="admin" label="Admin" />
                </form:select>
            </div>

            <button type="submit" class="btn btn-primary">Ok</button>
            <a href="/admin" class="btn btn-secondary">Cancel</a>
        </form:form>
        </c:if>
 <script>
$.validator.addMethod("regx", function(value, element, regexpr) {
    return regexpr.test(value);
}, "Please enter a valid email.");

$(document).ready(function() {

$("#basic-form").validate({
errorClass: "error fail-alert",
validClass: "valid success-alert",

rules: {
login : {
required: true,
minlength: 3
},
password: {
minlength: 4
},
confirmPassword: {
minlength: 4,
equalTo: "#password"
},
email: {
required: true,
regx: /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/
},
firstName: {
required: true
},
lastName: {
required: true
},
birthday: {
required: true
},
role: {
required: true
}
},
messages : {
    login: {
    required: "Please, enter your login.",
    minlength: "Length of your login is less then 3 characters."
    },
    password: {
    minlength: "Length of your password is less then 4 characters."
    },
    confirmPassword: {
    minlength: "Length of your password is less then 4 characters.",
    equalTo: "The password does not match the entered value."
    },
    email: {
    required: "Please, enter your email.",
    email: "Entered email is not valid. The email should be in the format: abc@domain.tld"
    },
    firstName: {
    required: "Please, enter your first name."
    },
    lastName: {
    required: "Please, enter your last name."
    },
    birthday: {
    required: "Please, enter your birthday in the format dd/mm/yyyy."
    },
    role: {
    required: "Please, select the role."
    },
    },
});
});

</script>

  </div>
  <%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma", "no-cache");
    %>
 </body>
</html>
