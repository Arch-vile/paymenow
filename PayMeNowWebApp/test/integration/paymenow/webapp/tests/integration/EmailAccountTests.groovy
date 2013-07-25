package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import org.junit.*
import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.User
import paymenow.webapp.domain.DomainViolationException

class EmailAccountTests {
	
	def fixtureLoader
	def fixture
	
    @Before
    void setUp() {
		fixture = fixtureLoader.load("baseFixture")
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
	
	@Test
	void testFailUniqueEmailsPerUser(){
		def validCode = "djsahud87sadsad09sad09sadisadsadgsahd9sadsdisdsdsddd"
		def validCode2 = "ldodiud864iunhfhyfi8f9rjujfig9gjgkg09gjgog08udjudodd"
		def first = new EmailAccount(email: "john.doe2@gmail.com", confirmationCode: validCode, isMaster: false, user: fixture.John)
		def second = new EmailAccount(email: "john.DOE2@gmail.com", confirmationCode: validCode2, isMaster: false, user: fixture.John)
		assert first.save()
		assert !second.save()
		assert !second.validate()
		assert 1 == second.errors.errorCount
		assert "unique"== second.errors.getFieldError("email").code
	}
	
	
}
