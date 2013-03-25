package paymenow.security

import java.security.SecureRandom

class VerificationCodeGenerator {

	def grailsApplication
	def allowedCodeChars = "abcdefghijklmnopqrstuvwxyz0123456789"
	
	def createCode(){
		def random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		random.nextBytes(new byte[512]); // preferred to seed the random securely
		
		def code = ""
		for(i in 1..grailsApplication.config.paymenow.security.confirmationcode.length){
			code += allowedCodeChars.charAt(random.nextInt(allowedCodeChars.length()))
		}
		
		return code
	}
	
	
}
