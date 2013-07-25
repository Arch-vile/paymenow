package paymenow.webapp.tests.unit



import grails.test.mixin.*
import org.junit.*
import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.User
import paymenow.webapp.service.CurrentUserService;
import paymenow.webapp.service.DomainService;
import paymenow.webapp.service.UserManagementService;
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CurrentUserService)
@Mock([UserManagementService, DomainService])
@TestMixin(DomainClassUnitTestMixin)
class CurrentUserServiceUnitTests {
	
	def confirmCode1 = "ddgjajds8843hsf83rhlsflkdsjf09w4hjwflidsfwhlfds939f1"
	def confirmCode2 = "ddgjajds8843hsf83rhlsflkdsjf09w4hjwflidsfwhlfds939f2"
	def confirmCode3 = "ddgjajds8843hsf83rhlsflkdsjf09w4hjwflidsfwhlfds939f3"
	def confirmCode4 = "ddgjajds8843hsf83rhlsflkdsjf09w4hjwflidsfwhlfds939f4"
	def email1 = new EmailAccount(email: "email1@emails.com", isMaster: true, confirmationCode: confirmCode1, confirmationDate: new Date())
	def email2 = new EmailAccount(email: "email2@emails.com", isMaster: false, confirmationCode: confirmCode2, confirmationDate: new Date())
	def email3 = new EmailAccount(email: "email3@emails.com", isMaster: false, confirmationCode: confirmCode3, confirmationDate: new Date())
	def email4 = new EmailAccount(email: "email4@emails.com", isMaster: false, confirmationCode: confirmCode4)
	
	void testGetUserMasterEmailFromMultipleFirst(){
		def user = new User(login: "mikko")
		user.emails = [email1,email2,email3]
		def looseControl = mockFor(UserManagementService)
		looseControl.demand.getUser { -> return user }
		service.userManagementService = looseControl.createMock()
		assert service.getUserMasterEmail().email == "email1@emails.com"
	}
	
	void testGetUserMasterEmailFromMultipleLast(){
		def user = new User(login: "mikko")
		user.emails = [email2,email3,email1]
		def looseControl = mockFor(UserManagementService)
		looseControl.demand.getUser { -> return user }
		service.userManagementService = looseControl.createMock()
		assert service.getUserMasterEmail().email == "email1@emails.com"
	}
	
	void testGetUserMasterEmailFromSingle(){
		def user = new User(login: "mikko")
		user.emails = [email1]
		def looseControl = mockFor(UserManagementService)
		looseControl.demand.getUser { -> return user }
		service.userManagementService = looseControl.createMock()
		assert service.getUserMasterEmail().email == "email1@emails.com"
	}
	
	void testGetUserMasterEmailNone(){
		def user = new User(login: "mikko")
		user.emails = [email2]
		def looseControl = mockFor(UserManagementService)
		looseControl.demand.getUser { -> return user }
		service.userManagementService = looseControl.createMock()
		assert service.getUserMasterEmail() == null
	}
	
	void testGetOwnedInvoiceFindsOnlyByVerifiedEmails(){
		def user = new User(login: "ville")
		user.emails = [email4, email1]
		
		def looseControl = mockFor(UserManagementService)
		looseControl.demand.getUser { -> return user }
		service.userManagementService = looseControl.createMock()
		
		def domainMock = mockFor(DomainService)
		domainMock.demand.getInvoicesByOwners { listOfEmails -> 
			listOfEmails.size() == 1 && listOfEmails.get(0) == "email1@emails.com" }
		service.domainService = domainMock.createMock()
		
		assert service.getOwnedInvoices()
	}
	
}
