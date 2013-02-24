package paymenowwebapp

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
	
	def confirm(confirmationCode, forLogin){
		log.info("Confirming account by confirmation code: ${confirmationCode} for login: ${forLogin}")
		
		def confirmedEmailAccount = EmailAccount.findByConfirmationCodeAndIsMaster(confirmationCode,true)
		if(!confirmedEmailAccount){
			throw new SecurityException("That is not nice! Stop it. 8")
		}
		
		log.info("found email account: " + confirmedEmailAccount)
		
		if(confirmedEmailAccount.confirmationDate != null)
			throw new SecurityException("That is not nice! Stop it. 2")
		
		def user = confirmedEmailAccount.user
			
		if(user.login != forLogin)
			throw new SecurityException("That is not nice! Stop it. 6")
		
		confirmedEmailAccount.setConfirmationDate(new Date())
		authenticationService.confirmUser(user.login)
	}
}
