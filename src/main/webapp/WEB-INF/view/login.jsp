<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Login page</title>

</head>
<body>
<sec:authorize access="isAuthenticated()">
  <% response.sendRedirect("/"); %>
</sec:authorize>
<body onload='document.loginForm.username.focus();'>
<center>
 <c:if test="${errorMessage != null}"><div style="color:red; font-weight: bold; margin: 30px 0px;">${errorMessage}</div></c:if>
</center>
<div class="container">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="login-form bg-light mt-4 p-4">
                <form action="/login" method="POST" class="row g-3">
                    <h4>Welcome to our cozy page!</h4>
<c:choose>
<c:when test="${!isWrongLoginOrPassword}">
    <div class="col-12">
       <label for="username" class="form-label">Login</label>
       <input type="text" class="form-control" id="username" name="username" aria-describedby="username" placeholder="Login" required>
    </div>
  </c:when>
  <c:otherwise>
    <div class="col-12">
       <label for="username" class="form-label">Login</label>
       <input type="text" class="form-control is-invalid" id="username" name="username" aria-describedby="username" placeholder="Login" required>
    </div>
  </c:otherwise>
</c:choose>

<c:choose>
<c:when test="${!isWrongLoginOrPassword}">
    <div class="col-12">
       <label for="password" class="form-label">Password</label>
       <input type="password" class="form-control" id="password" name="password" aria-describedby="password" placeholder="Password" required>
    </div>
  </c:when>
  <c:otherwise>
    <div class="col-12">
       <label for="password" class="form-label">Password</label>
       <input type="password" class="form-control is-invalid" id="password" name="password" aria-describedby="password" placeholder="Password" required>
    </div>
  </c:otherwise>
</c:choose>


 <button type="submit" class="btn btn-info float-end">Sign in</button>
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
 </div>
 </form>
 <hr class="mt-4">
 </div>
 </div>
 </div>
</div>

<center><h3> Click <a href="registration">here</a> to registration.</h3></center>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>