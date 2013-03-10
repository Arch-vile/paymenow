<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Account</title>
</head>
<body>
  <div class="body">

	<g:if test="${flash.message != null}">
  	<div>
  		${flash.message}
  	</div>
  	</g:if>
  
  <div style="background-color: #ee5555">
  	Your email accounts:<br/>
  	<g:each in="${emailAccounts}" var="email">
  		${email.email} [${email.confirmationDate ? 'CONFIRMED' : 'WAITING CONFIRMATION'}]
  		<g:if test="${!email.confirmationDate}">
  			<g:form action="emailAccountConfirmForm">
			<g:hiddenField name="email" value="${email.email}"/>  			
  			<g:submitButton name="Provide confirmation code"/>
  			</g:form>
  			
  		</g:if>
  		<br/>
  	</g:each>
  	<g:form action="addEmailAccount">
  		Add email account: <g:textField name="emailAddress"/>
		<g:submitButton name="Add"/>  		
  	</g:form>
  </div>
  
  <div style="background-color: #55ee55">
  Your invoices:<br/>
  <g:each in="${invoices}" var="invoice">
  	Bank: ${invoice.bankAccount}<br/>
  </g:each>
	</div>


  </div>
</body>
</html>