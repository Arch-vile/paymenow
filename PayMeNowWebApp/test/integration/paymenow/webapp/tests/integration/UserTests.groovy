package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import org.junit.*
import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.User

class UserTests {

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
    void testFailMultipleMaster() {
        def john = User.findByLogin('johnDoe')
		john.addToEmails(new EmailAccount(email: "some@gmail.com", confirmationCode: "somesomesomesomesomesomeomsomesomeomsomeomsomeomsome", isMaster: true, confirmationDate: new Date()))

		!john.save()//flush: true, failOnError: true) // need to flush to cause error
		assert "error.emails.master" == john.errors.getFieldError("emails").code
		assert 0 == EmailAccount.findAllByUserAndEmail(john, "some@gmail.com").size()
    }
	
	@Test
	void testSuccessMasterEmailChange(){
		def john = User.findByLogin('johnDoe')
		def oldMaster = john.emails.find({ it.isMaster })
		oldMaster.isMaster = false;
		assert oldMaster.save(flush: true, failOnError: true)
		def newMaster = new EmailAccount(email: "john56@gmail.com", confirmationCode: "jd8a7sdhgfs3w09840987hufds987oiyy098435hlkdshlkdsgde", isMaster: true, confirmationDate: new Date())
		john.addToEmails(newMaster)
		assert john.save(flush: true)
		assert "john56@gmail.com" == EmailAccount.findByUserAndIsMaster(john,true).email
		assert 1 == EmailAccount.findAllByUserAndIsMaster(john, true).size()
	}
	
	
	@Test
	void testFailNoMasterEmail(){
		def jack = new User(login: 'jack')
		jack.addToEmails(new EmailAccount(email: "jack@gmail.com", confirmationCode: "ghjkjhghyjukiuyhjuikuygtysu787y6trfgfdheomsdsadsadsa", isMaster: false, confirmationDate: new Date()))
		assert !jack.save()
		assert "error.emails.master" == jack.errors.getFieldError("emails").code
	}
	
	@Test
	void testDefault(){
		
	}
}
