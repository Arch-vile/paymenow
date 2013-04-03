package paymenow.webapp.controller

import paymenow.webapp.domain.DomainViolationException
import paymenow.webapp.domain.EmailAccount;
import paymenow.webapp.domain.Invoice;

import com.grailsrocks.authentication.LoginForm

class AccountController {

	def verificationCodeGenerator
	def userManagementService
	def currentUserService
	
    def index() { 
		if( !userManagementService.isLoggedIn(request) ){
			chain(controller: "home")
		}else {
			def invoices = currentUserService.getOwnedInvoices()
			def emailAccounts = currentUserService.getEmails()
			render(view: "accountIndex", model: [invoices: invoices, emailAccounts: emailAccounts, newAccount: chainModel?.newAccount])
		}	
		
	}
	
	
	//TODO: check all methods requiring authentication -> redirect login if needed
	//TODO: check all methods using authenticated user -> handle  session timeout
	def addEmailAccount(String email){
		log.info("Adding email address: ${email}")
		def newAccount = currentUserService.addEmailAccount(new EmailAccount(email: email))
		if(newAccount.hasErrors()){
			log.info("Error adding email account: ${newAccount.email}")
			chain(action: 'index', model: [newAccount: newAccount])
		} else {
			flash.confirmUrl = "http://localhost:8080/PayMeNowWebApp/account/confirmEmailAccount?confirmationCode=${newAccount.confirmationCode}&email=${newAccount.email}&login=${newAccount.user.login}"
			flash.message = "Verification link for new email account has been sent by email."
			chain(action: 'index')
		} 
	}
	
	
	// TODO: are you allowed to confirm if not logged in. not. but then this needs to redirect to authenticate and then back.
	// TODO: too much logic in controller! move to service
	def confirmEmailAccount(String email, String confirmationCode, String login){
		log.info("Confirming emailaccount for login[${login}] email[${email}] and confirmationCode[${confirmationCode}]")
		
		if(!userManagementService.isLoggedIn(request)){
			def loginSuccessURL = [controller: 'account', action: 'confirmEmailAccount', params: [login: login, confirmationCode: confirmationCode, email: email]]
			chain(controller: 'login', action: 'viaLogin', model: [loginSuccessURL: loginSuccessURL, loginMessage: 'You need to login to confirm email account'])
			return
		}
		
		def confirmedEmailAccount = currentUserService.confirmEmailAccount(email, confirmationCode, login)
		if(confirmedEmailAccount.hasErrors()){
			throw new DomainViolationException("Cannot confirm email account")
		}else {
			log.info("Email account verified")
			flash.message = "Email account verified"
			chain(action: "index")
		}	
	}
	
	def emailAccountConfirmForm(){
		[login: userManagementService.getUser().login, email: params.email]
	}
	
}
