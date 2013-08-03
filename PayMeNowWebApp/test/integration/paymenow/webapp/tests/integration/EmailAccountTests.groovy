package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import org.junit.*
import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.User
import paymenow.webapp.domain.DomainViolationException

class EmailAccountTests {
	
	def fixtureLoader
	def fixture
	def verificationCodeGenerator
	def email
	
    @Before
    void setUp() {
		fixture = fixtureLoader.load("baseFixture")
		email = new EmailAccount(
			email: "test@test.com",
			isMaster: false,
			confirmationCode: verificationCodeGenerator.createCode(),
			confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
		fixture.John.addToEmails(email)
    }

    @After
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
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "nullable" == email.errors.getFieldError('user').code
	}
	
	void emailRequired() {
		email.user = null
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "nullable" == email.errors.getFieldError('user').code
	}
	
	@Test
	void confirmationCodeRequired(){
		email.confirmationCode = null
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "nullable" == email.errors.getFieldError('confirmationCode').code
	}
	
	@Test
	void isMasterRequired() {
		email.isMaster = null
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "nullable"== email.errors.getFieldError("isMaster").code
	}
	
	@Test
	void emailFormat() {
		email.email = "notcorrect.com"
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "email.invalid"== email.errors.getFieldError("email").code
	}
	
	@Test
	void emailUniqueForUser() {
		email.email = "john.spammail@gmail.com"
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "unique"== email.errors.getFieldError("email").code
	}
	
	@Test
	void emailNotEmpty() {
		email.email = ""
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "blank"== email.errors.getFieldError("email").code
	}
	
	@Test
	void emailSizeOneLong() {
		email.email = "test@test.cooooooooooooooooooooooooooooooooooooooooooooooooooooooooooom"
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "maxSize.exceeded"== email.errors.getFieldError("email").code
	}
	
	@Test
	void confirmationDateOptional() {
		email.confirmationDate = null
		assert email.save()
	}

	@Test
	void confirmationCodeUnique() {
		email.confirmationCode = fixture.John.emails.toArray()[0].confirmationCode
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "unique"== email.errors.getFieldError("confirmationCode").code
	}
	
	@Test
	void confirmationCodeSizeOneShort() {
		email.confirmationCode = verificationCodeGenerator.createCode().substring(1)
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "size.toosmall"== email.errors.getFieldError("confirmationCode").code
	}
	
	@Test
	void confirmationCodeSizeOneLong() {
		email.confirmationCode = verificationCodeGenerator.createCode() + "a"
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "size.toobig"== email.errors.getFieldError("confirmationCode").code
	}
	
	@Test
	void confirmationCodeNotEmpty() {
		email.confirmationCode = ""
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "blank"== email.errors.getFieldError("confirmationCode").code
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
			confirmationCode: verificationCodeGenerator.createCode(),
			confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
		fixture.John.addToEmails(email)
		assert email2.email == "testupper@test.com"
	}
	
	@Test
	void onlyOneMasterAllowed() {
		email.isMaster = true
		assert !email.save()
		assert 1 == email.errors.errorCount
		assert "onlyOneMasterAllowed"== email.errors.getFieldError("isMaster").code
	}
	
	@Test
	void oneMasterRequired() {
		fixture.JohnPrivateMail.isMaster = false;
		assert !fixture.JohnPrivateMail.save()
		assert 1 == fixture.JohnPrivateMail.errors.errorCount
		assert "oneMasterRequired"== fixture.JohnPrivateMail.errors.getFieldError("isMaster").code
		
		
	}	
	
	@Test
	void oneMasterRequiredDirty() {
		
		EmailAccount.withSession { session ->
			email.isMaster = true
			fixture.JohnPrivateMail.isMaster = false;
			session.flush() // should fail
		}
		
		assert false
	}
		
	
	
}
