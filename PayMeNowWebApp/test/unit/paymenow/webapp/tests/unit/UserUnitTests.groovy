package paymenow.webapp.tests.unit

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import paymenow.webapp.domain.*
import paymenow.webapp.test.BaseFixtureLoader

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import com.grailsrocks.authentication.AuthenticationUser

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([EmailAccount, User, Invoice, Payment, AuthenticationUser])
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
		assertThat(fixture['John'], not(nullValue())) 
	}
	
	@Test
	void authenticationUserUniqueConstraint(){
		/** Tested as integration test. See {@link paymenow.webapp.tests.integration.UserTests.uniqueAuthenticationUserConstraint} **/
	}
	
		
    
}
