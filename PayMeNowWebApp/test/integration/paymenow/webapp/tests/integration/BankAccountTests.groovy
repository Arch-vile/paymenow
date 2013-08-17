package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import liquibase.exception.SetupException;

import com.grailsrocks.authentication.AuthenticationService;
import com.grailsrocks.authentication.AuthenticationUser
import org.junit.*

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*
import static paymenow.webapp.matchers.HasErrors.*

import paymenow.webapp.domain.BankAccount
import paymenow.webapp.domain.User
import paymenow.webapp.test.BaseFixtureLoader

class BankAccountTests {

	def grailsApplication
	def baseFixtureLoader = new BaseFixtureLoader()
	def fixture

	@Before	
	void setUp() {
		fixture = baseFixtureLoader.load(grailsApplication)
	}
	
	
	@Test
	void happyCase(){
		assertThat(BankAccount.findByAccountNmbr("FI11111"),
			not(nullValue()));
	}
	
	/**
	 * Cascading actions do not work on unit tests
	 */
	@Test
	void belongsToUser(){
		def ba = BankAccount.findByAccountNmbr(fixture.LudvigTheThird.bankAccounts.toArray()[0].accountNmbr)
		fixture.LudvigTheThird.delete(flush: true, failOnError: true)
		assertThat(BankAccount.findByAccountNmbr(ba.accountNmbr),
			nullValue())
		
	}
	
	
	
	
}
