package paymenowwebapp

class LoginController {
	
	def authenticationService

    def index() { }
	
	
	def register() { }
	
	def newUser() { 
		log.info("Creating new user! hop!")
		redirect (controller: "account")
	}
	
	
	def confirmAccount() {
		log.info("Confirming account by confirmation code ${params.confirmationCode}")
		
		def confirmedEmailAccount = EmailAccount.findByConfirmationCodeAndIsMaster(params.confirmationCode,true)
		log.info("found email account: " + confirmedEmailAccount)
		def userId = confirmedEmailAccount.userId;
		log.info("userId: " + userId)
		def user = User.get(userId)
		log.info("User login: " + user.authenticationUserLink)
		authenticationService.confirmUser(user.authenticationUserLink)
	}
	
}
