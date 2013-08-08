package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import com.grailsrocks.authentication.AuthenticationUser
import com.grailsrocks.authentication.AuthenticationService
import org.junit.*
import paymenow.webapp.domain.User

class EmailTests {

    @Before
    void setUp() {
		
    }

    @After
    void tearDown() {
        // Tear down logic here
    }
	
	@Test
	void testPass(){
		
	}
	
/*	@Test
	void testHasOneConstraintsOwned(){
		def john = new User(login: 'johnDoe')
		def email = new Email(emailAddress: 'thisIsFarTooLongToPassConstaint@gmail.com')
		
		john.save() // ??? otherwise: NULL not allowed for column "USER_ID" is thrown for email.save()
		john.email = email
		
		assert !email.save()
		assert "size.toobig"== email.errors.getFieldError("emailAddress").code
	}
	
	@Test
	void testHasOneConstraintsOwning(){
		def john = new User(login: 'johnDoe')
		def email = new Email(emailAddress: 'thisIsFarTooLongToPassConstaint@gmail.com')
		john.email = email
		
		john.save(failOnError: true)
		
		assert !john.save() // Assertion failed: save() is true!
		assert !john.save(flush: true) // throws null id in paymenowwebapp.Email entry (don't flush the Session after an exception occurs)
	}
	
*/
	
/*	@Test
	void testHasManyConstraintsOwned(){
		def john = new User(login: 'johnDoe')
		def email = new Email(emailAddress: 'john@gmail.com')
		def duplicateEmail = new Email(emailAddress: 'john@gmail.com')
		
		john.save() // otherwise: NULL not allowed for column "USER_ID" is thrown for email.save()
		john.addToEmails(email).addToEmails(duplicateEmail)
		
		assert email.save()
		assert !duplicateEmail.save()
		assert "unique"== duplicateEmail.errors.getFieldError("emailAddress").code
	}
	*/
	/*@Test
	
	void testHasManyConstraintsOwning(){
		
		def john = new User(login: 'johnDoe')
		def email = new Email(emailAddress: 'john@gmail.com')
		def duplicateEmail = new Email(emailAddress: 'john@gmail.com')
		
		john.addToEmails(email).addToEmails(duplicateEmail)
		assert !john.save() // Assertion failed: save() is true!
		assert !john.save(flush: true) // throws null id in Email entry (don't flush the Session after an exception occurs)
	}*/
	
	
	/*@Test
	void testHasManyConstraintsOwningExcp(){
		def john = new User(login: 'johnDoe')
		def email = new Email(emailAddress: 'john@gmail.com')
		def duplicateEmail = new Email(emailAddress: 'john@gmail.com')
		
		john.addToEmails(email).addToEmails(duplicateEmail)
		try {
			john.save(flush: true, failOnError: true)
			assert false // should not reach here
		}catch(Exception e){
			
		}
	}
	*/


    /*@Test
    void testOnlyOneMaster() {
		def john = new User(login: 'johnDoe')
		def email = new Email(emailAddress: 'john1@gmail.com', isMaster: true)
		john.addToEmails(email)
		john.save()
		assert 1 == john.emails.size()
		assert !john.errors.hasErrors()
		
		email = new Email(emailAddress: 'john2@gmail.com', isMaster: false)
		john.addToEmails(email)
		john.save()
		assert 2 == john.emails.size()
		assert !john.errors.hasErrors()
		
		email = new Email(emailAddress: 'john3@gmail.com', isMaster: true)
		john.addToEmails(email)
		john.save()
		assert "error.emails.multipleMaster"== john.errors.getFieldError("emails").code
		
    }*/
	
	/*@Test
	void testUniqueEmails(){
		def john = new User(login: 'johnDoe')
		john.save(flush: true)
		def email1 = new Email(emailAddress: 'john1@gmail.com', isMaster: false)
		def email2 = new Email(emailAddress: 'john1@gmail.com', isMaster: false)
		john.addToEmails(email1)
		john.addToEmails(email2)
		try {
			def result = john.save(flush: true)
			
		}catch(Exception e){
			println 'Exception'
		}	
		
		println "john.errors.errorCount" + john.errors.errorCount
		println "email1.errorCount" + email1.errorCount
		println "email2.errorCount" + email2.errorCount
		
			
		assert 1 == john.errors.errorCount
		assert "unique"== email2.errors.getFieldError("emailAddress").code
		assert 1 == john.errors.errorCount
		
	}*/
}
