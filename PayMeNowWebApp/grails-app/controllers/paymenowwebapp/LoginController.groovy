package paymenowwebapp

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
	
	
	def register() { }
	
	def newUser() { 
		log.info("Created new user!")
		def message = "Verification link has been sent to your email."
		render(view: "/messageViewer", model: [message: message])
	}
	
	
	def confirmAccount() {
		userManagementService.confirm(params.confirmationCode, params["login"])
		redirect (controller: "account")
	}
	
}
