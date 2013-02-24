<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  
  ${message}<br/>
  <g:if test="${flash.confirmUrl != null}">
  	<a href="${flash.confirmUrl }">Actually as we are in Development mode you can just click me!</a>
  </g:if>
  
  </div>
</body>
</html>