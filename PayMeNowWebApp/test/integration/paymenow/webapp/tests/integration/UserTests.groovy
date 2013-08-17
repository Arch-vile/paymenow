package paymenow.webapp.tests.integration

import static org.junit.Assert.*

import com.grailsrocks.authentication.AuthenticationService;
import com.grailsrocks.authentication.AuthenticationUser
import org.junit.*

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static paymenow.webapp.matchers.HasErrors.*

import paymenow.webapp.domain.User

class UserTests {

	
	/**
	 * Couldn't get the unique constraint on authUser to work on unit tests so created integration test for it
	 */
	@Test
	void uniqueAuthenticationUserConstraint(){
		AuthenticationUser auth = new AuthenticationUser(
			login: "dfghjklkjhgf",
			password: "fghj8765rfghjui",
			email: "mikko@mikko.com",
			status: AuthenticationService.STATUS_NEW).save()
		User john = new User(authUser: auth).save()
		User jane = new User(authUser: auth)
		
		assertThat(jane, domainWithErrors(authUser: "unique"))
	}
	
	
	// TODO: this must be tested somewhere
	@Test
	void deletingAuthenticationUser(){
		fixture.LudvigTheThirdAuth.delete(flush: true, failOnError: true)
	}
	
	
	
	
}
