package paymenowwebapp

import grails.validation.ValidationException

class UserManagementService {

    def serviceMethod() {

    }
	
	
	def onNewUser(loginId, email){
		log.info(loginId)
		log.info("Processing registration of new user with login[$loginId] and email[$email]")
		def confirmationCode = (new Random()).nextInt();
		def newUser = new User(authenticationUserLink: loginId);
		def masterEmail = new EmailAccount(isMaster: true, email: email, confirmationCode: confirmationCode)
		newUser.addToEmailAccounts(masterEmail);
		if(newUser.save(failOnError: true)){ // remove failOnError and write the error handling code
			log.info("New user created with master email and confirmation code: " + confirmationCode);
			log.info("Browse to /PayMeNowWebApp/login/confirmAccount?confirmationCode="+confirmationCode)
		}else {
			log.error("Error on registration processing. What to do!") // TODO
			
		}
		
	}
}
