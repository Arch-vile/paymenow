package paymenow.webapp.tests.unit



import grails.test.mixin.*
import org.junit.*

import paymenow.webapp.domain.EmailAccount;

/**
 * It seems that all constraints tests should be done as integration tests. Trying to run them
 * as unit tests is hard and in some cases (e.g. unique by some other fields)
 */
@TestFor(EmailAccount)
class EmailAccountUnitTests {
	
	def validCode = "djsahud87sadsad09sad09sadisadsadgsahd9sadsdisdsdsddd"
	def validCode2 = "ldodiud864iunhfhyfi8f9rjujfig9gjgkg09gjgog08udjudodd"
	
	void testFailUniqueConfirmationCode(){
		def code = "dfghjkoi8u7y6t5456yhnmkokju87y6trtuijhgtfdghyujkhui2"
		def first = new EmailAccount(email: "john.doe@gmail.com", confirmationCode: code, isMaster: true, user: 1)
		def second = new EmailAccount(email: "john.doe2@gmail.com", confirmationCode: code, isMaster: true, user: 1)
		mockForConstraintsTests(EmailAccount, [first, second])
		assert !second.validate()
		assert 1 == second.errors.errorCount
		assert "unique"== second.errors.getFieldError("confirmationCode").code
	}
	
	void testFailConfirmationCodeLenght(){
		// Test for too short
		def shortCode = "tooShortToBeSecure"
		def emailAcc = new EmailAccount(email: "john.doe@gmail.com", confirmationCode: shortCode, isMaster: true, user: 1)
		mockForConstraintsTests(EmailAccount, [emailAcc])
		assert !emailAcc.validate()
		assert 1 == emailAcc.errors.errorCount
		assert "size.toosmall"== emailAcc.errors.getFieldError("confirmationCode").code
		
		// Test for too long
		def longCode = "a"
		for(i in 0..120){
			longCode += "i"
		}
		emailAcc = new EmailAccount(email: "john.doe@gmail.com", confirmationCode: longCode, isMaster: true, user: 1)
		assert !emailAcc.validate()
		assert 1 == emailAcc.errors.errorCount
		assert "size.toobig"== emailAcc.errors.getFieldError("confirmationCode").code
	}
	
	void testFailEmailFormat(){
		def emailAcc = new EmailAccount(email: "gmail.com", confirmationCode: validCode, isMaster: true, user: 1)
		mockForConstraintsTests(EmailAccount, [emailAcc])
		assert !emailAcc.validate()
		assert 1 == emailAcc.errors.errorCount
		assert "email.invalid"== emailAcc.errors.getFieldError("email").code
	}
	
	void testSuccessEmailForcedLowerCase(){
		def emailAcc = new EmailAccount(email: "JOHN@gmail.com", confirmationCode: validCode, isMaster: true, user: 1)
		mockForConstraintsTests(EmailAccount, [emailAcc])
		assert emailAcc.save()
		assert "john@gmail.com" == emailAcc.email
	}
	
	void testFailEmptyEmail(){
		def emailAcc = new EmailAccount(email: "", confirmationCode: validCode, isMaster: true, user: 1)
		mockForConstraintsTests(EmailAccount, [emailAcc])
		assert !emailAcc.validate()
		assert 1 == emailAcc.errors.errorCount
		assert "blank"== emailAcc.errors.getFieldError("email").code
	}
	
	
	
}
