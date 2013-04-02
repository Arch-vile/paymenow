package paymenow.webapp.controller

import com.grailsrocks.authentication.LoginForm
import com.grailsrocks.authentication.SignupForm

class LoginController {
	
	def userManagementService

    def index() { 
		render(view: "login")
	}
	
	def login() {
		render(view: "login")
	}
	
	def logout(){
		def message = "You have successfully logged out"
		render(view: "/messageViewer", model: [message: message])
	}
	
	def register() {
		
		
	}
	def errorOnRegister() { 
		log.info("ERROR ON REGISTER")
		
	}
	
	def newUser() { 
		log.info("Created new user!")
		def message = "Verification link has been sent to your email."
		render(view: "/messageViewer", model: [message: message])
	}
	
	
	def confirmAccount() {
		userManagementService.confirm(params.confirmationCode, params["login"])
		render(view: "accountVerified")
	}

	
	def viaLogin(){
		
		//[controller: 'account', action: 'confirmEmailAccount', params: [login: login, confirmationCode: confirmationCode, email: email]],'You need to login to confirm email account')){
		
		// To preset the login name on the login form
		def loginForm = new LoginForm(login: chainModel.loginSuccessURL.params.login) //TODO: not clean. find better way to resupply the login for the login form without creating coupling with authentication moduele
		flash.loginForm = loginForm
		//commands the authentication plugin
		// TODO: will not work if user fails to login on first try. need propably fix on the authentication plugin
		flash.authSuccessURL = chainModel.loginSuccessURL
		flash.message = chainModel.loginMessage
		forward controller: 'login' // Need to forward for the flash to survive for the authentication plugin controller
		return true
	
		
	}
		
}
