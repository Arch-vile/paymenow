<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  
  	Create new payment:<br/>
  	
  	<g:form>
  	
  	Description: <g:textField name="description" value="${invoice.description}"/><br/>
  	Bank account: <g:textField name="bankAccount" value="${invoice.bankAccount}"/><br/>
  	Password: <g:textField name="password" value="${invoice.password}"/><br/>
  	Alias: <g:textField name="alias" value="${invoice.alias}"/><br/><br/><br/>
  	Payments:<br/>
  	<g:each in="${invoice.payments}" var="payment" status="index">
  		To: <g:textField name="payments[${index}].assignedTo" value="${payment.assignedTo}"/><br/>
  		Amount: <g:textField name="payments[${index}].amount" value="${payment.amount}"/><br/>
  		Description: <g:textField name="payments[${index}].description" value="${payment.description}"/><br/>
  		<br/>
  	</g:each>
  	<g:actionSubmit value="Save me" action="save"/>
  	
  	</g:form>
  
  
  </div>
</body>
</html>