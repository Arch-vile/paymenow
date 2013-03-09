<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  <p>Register here!!</p>
  <g:if test="${flash.authenticationFailure}">
	Login failed: ${message(code:"authentication.failure."+flash.authenticationFailure.result).encodeAsHTML()}
</g:if>
<g:renderErrors bean="${flash.signupForm}">
    <li>${it}</li>
</g:renderErrors>
<auth:form authAction="signup" success="[action:'newUser']">
    User: <g:textField name="login"/>
    
    <br/>
    Email: <input name="email"/><br/>
    Password: <input type="password" name="password"/><br/>
    Confirm Password: <input type="password" name="passwordConfirm"/><br/>
    <input type="submit" value="Create account"/>
</auth:form>
  </div>
</body>
</html>