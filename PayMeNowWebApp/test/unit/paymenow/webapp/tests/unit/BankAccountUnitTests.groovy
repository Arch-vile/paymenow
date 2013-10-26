package paymenow.webapp.tests.unit

import static org.junit.Assert.*

import static paymenow.webapp.matchers.HasErrors.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import paymenow.webapp.domain.*
import paymenow.webapp.test.BaseFixtureLoader
import paymenow.webapp.test.TestUtils;

import static org.hamcrest.Matchers.*;
import com.grailsrocks.authentication.AuthenticationService



import com.grailsrocks.authentication.AuthenticationUser
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([EmailAccount, User, Invoice, Payment, AuthenticationUser, BankAccount])
class BankAccountUnitTests {

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
		assertThat(fixture.LudvigTheThird.bankAccounts.find { it.accountNmbr == "FI11111" },
			equalTo(fixture.LudvigBankAccount1));
	}
	
	@Test
	void hasAccountNmbr(){
		BankAccount ba = new BankAccount( )
		fixture.LudvigTheThird.addToBankAccounts(ba)
		assertThat(ba, domainWithErrors(accountNmbr: "nullable"))
	}
	
	
    
}
