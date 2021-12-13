<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
         <meta name="viewport" content="width=device-width, initial-scale=1">
         <%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
         <%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>

         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
         <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

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
         label.required:after
         {
             color: red;
             content: " *";
         }
        </style>
    <title>Registration page</title>
     <script src="https://www.google.com/recaptcha/api.js"></script>

</head>

<body>
<center><h3>Dear user, please enter your details!</h3></center>
<div class="container">

    <form:form id="basic-form" action="/registration/addNewUser" modelAttribute="newUser">
                      <form:label path="login" class="control-label required">Login</form:label>
                      <form:input path="login" class="form-control" id="login" name="login" placeholder="Login" autofocus="true" />
                      <div style="color:red; font-weight: bold; margin: 30px 0px;"><form:errors path="login" cssClass="error" /></div>

                      <form:label path="password" class="control-label required">Password</form:label>
                      <form:password path="password" class="form-control" id="password" name="password" placeholder="Password" autofocus="true" />
                      <div style="color:red; font-weight: bold; margin: 30px 0px;"><form:errors path="password" cssClass="error" /></div>


                      <form:label path="confirmPassword" class="control-label required">Password again</form:label>
                      <form:password path="confirmPassword" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Password again" autofocus="true" />
                      <div style="color:red; font-weight: bold; margin: 30px 0px;"><form:errors path="confirmPassword" cssClass="error" /></div>


                      <form:label path="email" class="control-label required">Email</form:label>
                      <form:input path="email" class="form-control" id="email" name="email" placeholder="Email" autofocus="true" />
                      <div style="color:red; font-weight: bold; margin: 30px 0px;"><form:errors path="email" cssClass="error" /></div>

                      <form:label path="firstName" class="control-label required">First name</form:label>
                      <form:input path="firstName" class="form-control" id="firstName" name="firstName" placeholder="First name" autofocus="true" />
                      <div style="color:red; font-weight: bold; margin: 30px 0px;"><form:errors path="firstName" cssClass="error" /></div>

                      <form:label path="lastName" class="control-label required">Last name</form:label>
                      <form:input path="lastName" class="form-control" id="lastName" name="lastName" placeholder="Last name" autofocus="true" />
                      <div style="color:red; font-weight: bold; margin: 30px 0px;"><form:errors path="lastName" cssClass="error" /></div>

                      <form:label path="birthday" class="control-label required">Birthday</form:label>
                      <form:input path="birthday" class="form-control" id="birthday" name="birthday" placeholder="Birthday" autofocus="true" />
                      <div style="color:red; font-weight: bold; margin: 30px 0px;"><form:errors path="birthday" cssClass="error" /></div>

                      <div class="g-recaptcha" data-sitekey="6LdPZoAdAAAAAA57iWMOXV9-BvvqzYvKfobdLTJb"></div>
                      <ul class="line-legend">
                           <span class="error text-danger">${errorCaptchaMessage}</span>
                      </ul>

                    <button type="submit" class="btn btn-primary" immediate="true" >Ok</button>
                    <a href="/login" class="btn btn-secondary">Cancel</a>

    </form:form>


</div>
<script>
$.validator.addMethod("regx", function(value, element, regexpr) {
    return regexpr.test(value);
}, "Please enter a valid email.");

$(document).ready(function() {

$("#basic-form").validate({
errorClass: "error fail-alert",
validClass: "valid success-alert",

rules: {

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
}

},
messages : {

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
    },
});
});
</script>

</body>
</html>