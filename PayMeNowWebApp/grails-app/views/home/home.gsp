<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>HOME</title>
</head>
<body>
	<div class="body">
		This is home.
  
		<g:if test="${flash.authenticationFailure}">
			Login failed: ${message(code:"authentication.failure."+flash.authenticationFailure.result).encodeAsHTML()}
		</g:if>
		<auth:form authAction="login" success="[controller:'account']">
		    User: <g:textField name="login"/><br/>
		    Password: <input type="password" name="password"/><br/>
		    <input type="submit" value="Log in"/>
		</auth:form>
  </div>
</body>
</html>