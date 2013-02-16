package paymenowwebapp

class LoginController {

    def index() { }
	
	
	def register() { }
	
	def newUser() { 
		log.info("Creating new user! hop!")
		redirect (controller: "account")
	}
}
