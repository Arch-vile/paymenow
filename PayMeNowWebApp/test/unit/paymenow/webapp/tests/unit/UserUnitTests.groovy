package paymenow.webapp.tests.unit

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import paymenow.webapp.domain.*
import paymenow.webapp.test.BaseFixtureLoader

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;



import com.grailsrocks.authentication.AuthenticationUser

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([EmailAccount, User, Invoice, Payment, AuthenticationUser, BankAccount])
class UserUnitTests {

	def baseFixtureLoader = new BaseFixtureLoader()
    def fixture
	
	void setUp() {
		fixture = baseFixtureLoader.load()
	}
	
    void tearDown() {
        // Tear down logic here
    }
	
	@Test
	void happyCase() {
		assertThat(fixture.John, not(nullValue())) 
	}
	
	@Test
	void hasManyEmails(){
		fixture.John.addToEmails(baseFixtureLoader.newMail1);
		fixture.John.addToEmails(baseFixtureLoader.newMail2);
		assertThat(fixture.John.save(), not(nullValue()))
	}
	
	@Test
	void hasManyBackAccounts(){
		fixture.John.addToBankAccounts(baseFixtureLoader.newBankAccount1);
		fixture.John.addToBankAccounts(baseFixtureLoader.newBankAccount2);
		assertThat(fixture.John.save(), not(nullValue()))
	}
	
	
	@Test
	void authenticationUserUniqueConstraint(){
		/** Tested as integration test. See {@link paymenow.webapp.tests.integration.UserTests.uniqueAuthenticationUserConstraint} **/
	}
	
		
    
}
