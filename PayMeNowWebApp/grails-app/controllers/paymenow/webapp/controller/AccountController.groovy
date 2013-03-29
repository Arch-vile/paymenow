package paymenow.webapp.controller

import paymenow.webapp.domain.EmailAccount;
import paymenow.webapp.domain.Invoice;

import com.grailsrocks.authentication.LoginForm

class AccountController {

	def verificationCodeGenerator
	def userManagementService
	def currentUserService
	
    def index() { 
		if( !userManagementService.isLoggedIn(request) )
			chain(controller: "home")
			
		def invoices = currentUserService.getOwnedInvoices()
		def emailAccounts = currentUserService.getEmails()
		render(view: "accountIndex", model: [invoices: invoices, emailAccounts: emailAccounts, newAccount: chainModel?.newAccount])	
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
	def confirmEmailAccount(){
		def emailToConfirm = params.email
		def confirmationCode = params.confirmationCode
		def login = params.login
		log.info("Confirming emailaccount for login[${login}] email[${emailToConfirm}] and confirmationCode[${confirmationCode}]")
		
		// Needs to be logged in 
		if(!userManagementService.isLoggedIn(request)){
			// To preset the login name on the login form
			def loginForm = new LoginForm(login: login) //TODO: not clean. find better way to resupply the login for the login form without creating coupling with authentication moduele
			flash.loginForm = loginForm
			//commands the authentication plugin
			// TODO: will not work if user fails to login on first try. need propably fix on the authentication plugin
			flash.authSuccessURL = [controller: 'account', action: 'confirmEmailAccount', params: [login: login, confirmationCode: confirmationCode, email: emailToConfirm]] 
			
			flash.message = 'You need to login to confirm new email account'
			forward controller: 'login' // Need to forward for the flash to survive for the authentication plugin controller
			return
		}
		
		
		if(!login || !confirmationCode){
			throw new SecurityException("Not nice: 9")
		}
		
		def forUser = userManagementService.getUser()
		if(forUser.login != login){
			throw new SecurityException("Not nice: 8")
		}
		
		def emailAccountToConfirm = EmailAccount.findByConfirmationCodeAndUserAndEmailAndConfirmationDate(confirmationCode,forUser,emailToConfirm,null)

		if(!emailAccountToConfirm){
			throw new SecurityException("Not nice: 8s")
		}

		emailAccountToConfirm.confirmationDate = new Date();
		if(emailAccountToConfirm.save()){
			log.info("Email account verified")
			flash.message = "New email account verified"
			chain(action: "index")
			
		}else {
			//TODO: error handling
		}	
	}
	
	def emailAccountConfirmForm(){
		[login: userManagementService.getUser().login, email: params.email]
	}
	
}
