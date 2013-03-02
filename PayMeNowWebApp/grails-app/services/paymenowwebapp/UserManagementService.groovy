package paymenowwebapp

import com.grailsrocks.authentication.AuthenticatedUser
import grails.validation.ValidationException
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.web.util.WebUtils


class UserManagementService {

	def verificationCodeGenerator
	def authenticationService
	
    def serviceMethod() {

    }
	
	
	def onNewUser(login, email){
		log.info(login)
		log.info("Processing registration of new user with login[$login] and email[$email]")
		def confirmationCode = verificationCodeGenerator.createCode();
		
		if(confirmationCode.length() < 10){
			throw new SecurityException("Too easy code generated")
		}
		
		def newUser = new User(login: login);
		//TODO: add confirmation expires date.
		def masterEmail = new EmailAccount(isMaster: true, email: email, confirmationCode: confirmationCode)
		newUser.addToEmailAccounts(masterEmail);
		if(newUser.save(failOnError: true)){ // remove failOnError and write the error handling code
			log.info("New user created with master email and confirmation code: " + confirmationCode);
			def confirmUrl = "http://localhost:8080/PayMeNowWebApp/login/confirmAccount?confirmationCode="+confirmationCode+"&login="+login
			//TODO: send email 
			if(GrailsUtil.isDevelopmentEnv()){
				log.info("Storing confirmation url to request for testing purposes")
				def request = WebUtils.retrieveGrailsWebRequest().getCurrentRequest()
				def flash = WebUtils.retrieveGrailsWebRequest().attributes.getFlashScope(request)
				flash.put("confirmUrl", confirmUrl)
			}
		}else {
			log.error("Error on registration processing. What to do!") // TODO
			
		}
		
	}
	
	// TODO: authentication plugin actually is not very secure as it is based only on session id.
	// bruteforce attack on session id is possible
	def confirm(confirmationCode, forLogin){
		log.info("Confirming account by confirmation code: ${confirmationCode} for login: ${forLogin}")
		
		if(!confirmationCode || !forLogin){
			throw new SecurityException("That is not nice! Stop it. 1")
		}
		
		def confirmedEmailAccount = EmailAccount.findByConfirmationCodeAndIsMaster(confirmationCode,true)
		if(!confirmedEmailAccount){
			throw new SecurityException("That is not nice! Stop it. 2")
		}
		
		log.info("found email account: " + confirmedEmailAccount)
		
		if(confirmedEmailAccount.confirmationDate != null)
			throw new SecurityException("That is not nice! Stop it. 3")
		
		def user = confirmedEmailAccount.user
			
		if(user.login != forLogin)
			throw new SecurityException("That is not nice! Stop it. 4")
		
		confirmedEmailAccount.setConfirmationDate(new Date())
		authenticationService.confirmUser(user.login)
		
		//TODO: send email to user that the account is verified
		
		// Lets login in the user
		def authenticationUser = authenticationService.getUser(user.login)
		authenticationService.logIn(authenticationUser)
		
	}
	
	
}
