package paymenow.webapp.service



import grails.test.mixin.*
import org.junit.*
import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.User
import grails.test.mixin.domain.DomainClassUnitTestMixin

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CurrentUserService)
@Mock([UserManagementService, User, EmailAccount])
@TestMixin(DomainClassUnitTestMixin)
class CurrentUserServiceTests {

    void testGetUser() {
		
    }
	
	void testGetUserMasterEmailFromMultipleFirst(){
		/*mockDomain(EmailAccount)
		mockDomain(User, [ 
			[login: "mikko", 
				emails: [
					[isMaster: true, email: "email1@emails.com"]
					]
			]
		])
		
		def looseControl = mockFor(UserManagementService)
		looseControl.demand.getUser { User.get(0) }
		service.userManagementService = looseControl.createMock()
		assert service.getUserMasterEmail().email == "email1@emails.com"
		*/		
	}
	
}
