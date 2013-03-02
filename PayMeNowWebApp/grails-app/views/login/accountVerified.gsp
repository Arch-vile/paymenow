<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  
  ${params.login}, your account has been verified.<br>
  <auth:ifLoggedIn>
  <g:link controller="account">Go to your account</g:link>
  </auth:ifLoggedIn>
  <auth:ifNotLoggedIn>
  <g:link controller="login">Login to access your account</g:link>
  </auth:ifNotLoggedIn>
  
  </div>
</body>
</html>