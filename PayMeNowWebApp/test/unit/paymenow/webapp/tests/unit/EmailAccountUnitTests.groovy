package paymenow.webapp.tests.unit

import static org.junit.Assert.*


import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

import com.grailsrocks.authentication.AuthenticationUser;

import paymenow.webapp.domain.*
import paymenow.webapp.matchers.HasErrors;
import paymenow.webapp.test.BaseFixtureLoader
import static paymenow.webapp.matchers.HasErrors.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([EmailAccount, User, Invoice, Payment, AuthenticationUser, BankAccount])
class EmailAccountUnitTests {

	def baseFixtureLoader = new BaseFixtureLoader()
    def fixture
	def email
	
	void setUp() {
		fixture = baseFixtureLoader.load()
		email = new EmailAccount(
			email: "test@test.com",
			isMaster: false,
			confirmationCode: "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
			confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
		fixture.John.addToEmails(email)
	}
	

    void tearDown() {
        // Tear down logic here
    }
	
	@Test
	void happyCase() {
		assert email.save()
	}
	
	@Test
	void belongsToUser() {
		email.user = null
		assertThat(email, domainWithErrors(user: "nullable"))
	}
	
	@Test
	void emailRequired() {
		email.email = null
		assertThat(email, domainWithErrors(email: "nullable"))
	}
	
	@Test
	void confirmationCodeRequired(){
		email.confirmationCode = null
		assertThat(email, domainWithErrors(confirmationCode: "nullable"))
	}
	
	@Test
	void isMasterRequired() {
		email.isMaster = null
		assertThat(email, domainWithErrors(isMaster: "nullable"))
	}
	
	@Test
	void emailFormat() {
		email.email = "notcorrect.com"
		assertThat(email, domainWithErrors(email: "email.invalid"))
	}
	
	@Test
	void emailUniqueForUser() {
		email.email = "john.spammail@gmail.com"
		assertThat(email, domainWithErrors(email: "unique"))
	}
	
	@Test
	void emailNotEmpty() {
		email.email = ""
		assertThat(email, domainWithErrors(email: "blank"))
	}
	
	@Test
	void emailSizeOneLong() {
		email.email = "test@test.cooooooooooooooooooooooooooooooooooooooooooooooooooooooooooom"
		assertThat(email, domainWithErrors(email: "maxSize.exceeded"))
	}
	
	@Test
	void confirmationDateOptional() {
		email.confirmationDate = null
		assert email.save()
	}

	@Test
	void confirmationCodeUnique() {
		email.confirmationCode = fixture.John.emails.toArray()[0].confirmationCode
		assertThat(email, domainWithErrors(confirmationCode: "unique"))
	}
	
	@Test
	void confirmationCodeSizeOneShort() {
		email.confirmationCode = baseFixtureLoader.sampleVerificationCode.substring(1)
		assertThat(email, domainWithErrors(confirmationCode: "size.toosmall"))
	}
	
	@Test
	void confirmationCodeSizeOneLong() {
		email.confirmationCode = baseFixtureLoader.sampleVerificationCode + "a"
		assertThat(email, domainWithErrors(confirmationCode: "size.toobig"))
	}
	
	@Test
	void confirmationCodeNotEmpty() {
		email.confirmationCode = ""
		assertThat(email, domainWithErrors(confirmationCode: "blank"))
	}
		
	@Test
	void emailForcedToLowerCase() {
		email.email = "MYEMail@gmail.com"
		assert email.email == "myemail@gmail.com"
		assert email.save()
		assert email.email == "myemail@gmail.com"
		
		def email2 = new EmailAccount(
			email: "TESTUPPER@test.com",
			isMaster: false,
			confirmationCode: baseFixtureLoader.sampleVerificationCode,
			confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
		fixture.John.addToEmails(email)
		assert email2.email == "testupper@test.com"
	}
	
	@Test
	void onlyOneMasterAllowed() {
		email.isMaster = true
		assertThat(email, domainWithErrors(isMaster: "onlyOneMasterAllowed"))
	}
	
	@Test
	void oneMasterRequired() {
		fixture.JohnPrivateMail.isMaster = false;
		assertThat(fixture.JohnPrivateMail, domainWithErrors(isMaster: "oneMasterRequired"))
		
	}
	
    
}
