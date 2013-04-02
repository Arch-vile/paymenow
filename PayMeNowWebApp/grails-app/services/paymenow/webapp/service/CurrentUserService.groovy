package paymenow.webapp.service

import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.Invoice

class CurrentUserService {

	def userManagementService
	def verificationCodeGenerator
	
	def getUser(){
		return userManagementService.getUser()
	}
	
	def getUserMasterEmail(){
		return getUserVerifiedEmails().find(){ it.isMaster }
	}
	
    def getOwnedInvoices() {
		return Invoice.findAllByOwnerInList(getUserVerifiedEmails().email)
    }
	
	def getEmails(){
		return getUser().emails
	}
	
	def getUserVerifiedEmails(){
		return getEmails()?.grep {
			it.confirmationDate != null
		}
	}
	
	def addEmailAccount(EmailAccount newAccount){
		
		def verificationCode = verificationCodeGenerator.createCode()
		newAccount.properties = [
			confirmationCode: verificationCode,
			isMaster: false,
			user: getUser()]
		
		if(newAccount.save()){
			// TODO: send confirmation email
			
		}
		
		return newAccount

	}
	
	def confirmEmailAccount(String email, String confirmationCode, String login){
		if(!login || !confirmationCode){
			throw new SecurityException("Not nice: 9")
		}
		
		def forUser = getUser()
		if(forUser.login != login){
			throw new SecurityException("Not nice: 8")
		}
		
		def emailAccountToConfirm = EmailAccount.findByConfirmationCodeAndUserAndEmailAndConfirmationDate(confirmationCode,forUser,email,null)

		if(!emailAccountToConfirm){
			throw new SecurityException("Not nice: 8s")
		}

		emailAccountToConfirm.confirmationDate = new Date();
		emailAccountToConfirm.save()
		return emailAccountToConfirm
	}
	
}
