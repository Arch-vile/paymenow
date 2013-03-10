<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  	<g:form>
  	<g:textField name="email" value="${email}"/>
  	<g:textField name="login" value="${login}"/>
  	<g:textField name="confirmationCode"/>
  	<g:actionSubmit value="Confirm" action="confirmEmailAccount"/>
  	</g:form>
  </div>
</body>
</html>