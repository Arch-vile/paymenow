package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import org.junit.*
import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.User
import paymenow.webapp.domain.DomainViolationException

class EmailAccountIntegrationTests {
	
    @Before
    void setUp() {
		// John has multiple confirmed emails
		def john = new User(login: 'johnDoe')
		john.addToEmails(new EmailAccount(email: "john.doe1@gmail.com", confirmationCode: "dfghjkoi8u7y6t5456yhnmkokju87y6trtuijhgtfdghyujkhui1", isMaster: true, confirmationDate: new Date()))
		john.addToEmails(new EmailAccount(email: "john.doe2@gmail.com", confirmationCode: "dfghjkoi8u7y6t5456yhnmkokju87y6trtuijhgtfdghyujkhui2", isMaster: false, confirmationDate: new Date()))
		john.addToEmails(new EmailAccount(email: "does.family@gmail.com", confirmationCode: "dfghjkoi8u7y6t5456yhnmkokju87y6trtuijhgtfdghyujkhui3", isMaster: false, confirmationDate: new Date()))
		john.save(failOnError: true)
				
		// Jane only has one email
		def jane = new User(login: 'janeDoe')
		jane.addToEmails(new EmailAccount(email: "jane.doe1@gmail.com", confirmationCode: "d456789okjhgre234r5tfghjiooijhgryuiokjhghjkdgfgyujd1", isMaster: true, confirmationDate: new Date()))
		jane.save(failOnError: true)

		// Bob has shared email with John
		def bob = new User(login: 'bobDoe')
		bob.addToEmails(new EmailAccount(email: "does.family@gmail.com", confirmationCode: "fghjklkjhghjkjhgjkljhghjkjhghjkjhghjkjhghjkjdfdfdfd1", isMaster: true, confirmationDate: new Date()))
		bob.save(failOnError: true)
    }

    @After
    void tearDown() {
        // Tear down logic here
    }
	
	@Test
	void testFailIfNoUser(){
		def email = new EmailAccount(email: "john@gmail.com", isMaster: true, confirmationCode: "rtyuio987y6trfgthyjuksadjhsaygdsaoiudsahdghjdksdsddd")
		assert !email.save()
		assert "nullable" == email.errors.getFieldError('user').code
	}	
	
	@Test
	void testFailIfMultipleMaster(){
		def john = User.findByLogin("johnDoe")
		def email1 = new EmailAccount(email: "johnnew@gmail.com", isMaster: true, confirmationCode: "dsadsadoi87657sardtk2ihgdtyusadiyosadhsakjkdsadsaddd")
		john.addToEmails(email1)
		
		try {
			email1.save(flush: true) // need to flush to cause error
			assert false
		}catch(DomainViolationException e){
			assert 'Cannot save. Only one master email allowed.' == e.message
		}
	}
	
	
}
