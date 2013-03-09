<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  
  <g:if test="${flash.message != null}">
  	<div>
  		${flash.message}
  	</div>
  	</g:if>
  <g:if test="${flash.authenticationFailure}">
			Login failed: ${message(code:"authentication.failure."+flash.authenticationFailure.result).encodeAsHTML()}
</g:if>
		<auth:form authAction="login" success="[controller:'account']">
		    User: <g:textField name="login" value="${flash.loginForm?.login}"/><br/>
		    Password: <input type="password" name="password"/><br/>
		    <g:each in="${flash.forwardParams}" var="param">
		    	<g:textField name="${param.key}" value="${param.value}"/>
		    </g:each>
		    <input type="submit" value="Log in"/>
		</auth:form>
  
  </div>
</body>
</html>