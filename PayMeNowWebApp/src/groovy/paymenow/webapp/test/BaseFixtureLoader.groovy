package paymenow.webapp.test

import grails.util.Environment;
import grails.util.GrailsUtil;

import javax.security.auth.login.AccountNotFoundException;

import com.grailsrocks.authentication.AuthenticationUser
import com.grailsrocks.authentication.AuthenticationService
import org.codehaus.groovy.grails.commons.GrailsApplication
import paymenow.webapp.domain.BankAccount
import paymenow.webapp.domain.EmailAccount
import paymenow.webapp.domain.Invoice
import paymenow.webapp.domain.Payment
import paymenow.webapp.domain.User

class BaseFixtureLoader {

	def fixture = [:]

	// Some helper data
	def sampleVerificationCode = 'jdue830djsdkj39djsdj39fjfk30jfksd03jtj309jgfk03kjd23'
	def anotherVerificationCode = 'jj9hjasd8njasdlkvgadj7305l958jdhdd9idjdjdhjbdgeudhds'

	def newMail1 = new EmailAccount(
		email: "newmail1@gmail.com",
		isMaster: false,
		confirmationCode: "newmail1newmail1newmail1newmail1newmail1newmail1newm")

	def newMail2 = new EmailAccount(
		email: "newmail2@gmail.com",
		isMaster: false,
		confirmationCode: "newmail2newmail2newmail2newmail2newmail2newmail2newm")


	def newBankAccount1 = new BankAccount(
		accountNmbr: "FI82837373")
	
	def newBankAccount2 = new BankAccount(
		accountNmbr: "FI2kd02020")

	
	
	def load(GrailsApplication app) {
		
		// Authentication users
		fixture.JohnAuth = new AuthenticationUser(
			login: "john.doe@gmail.com",
			password: "idue873jkdks",
			email: "john.doe@gmail.com",
			status: AuthenticationService.STATUS_VALID)
		
		fixture.JaneAuth = new AuthenticationUser(
			login: "jane@gmail.com",
			password: "kd8e0dj2jdns",
			email: "jane@gmail.com",
			status: AuthenticationService.STATUS_VALID)
		fixture.LudvigTheThirdAuth = new AuthenticationUser(
			login: "ludvig@hotmail.com",
			password: "99464745h79e",
			email: "ludvig@hotmail.com",
			status: AuthenticationService.STATUS_VALID)
		fixture.EronAuth = new AuthenticationUser(
			login: "eron23@gmail.com",
			password: "9867ohud8dse",
			email: "eron23@gmail.com",
			status: AuthenticationService.STATUS_AWAITING_CONFIRMATION)
		fixture.IlonaAuth = new AuthenticationUser(
			login: "ilona@hotmail.com",
			password: "czxdwek9djse",
			email: "ilona@hotmail.com",
			status: AuthenticationService.STATUS_AWAITING_CONFIRMATION)
		
		
		
		// Users
		fixture.John = new User( authUser: fixture.JohnAuth)
		fixture.Jane = new User( authUser: fixture.JaneAuth)
		fixture.LudvigTheThird = new User( authUser: fixture.LudvigTheThirdAuth)
		fixture.Eron = new User( authUser: fixture.EronAuth)
		fixture.Ilona = new User( authUser: fixture.IlonaAuth)

		
		// John's emails
		fixture.JohnPrivateMail2 = new EmailAccount(
				email: "john@myhost.com",
				isMaster: false,
				confirmationCode: "JohnPrivateMail2JohnPrivateMail2JohnPrivateMail2John")
		fixture.John.addToEmails(fixture.JohnPrivateMail2)

		fixture.JohnPrivateMail = new EmailAccount(
				email: "JOHN.DOE@gmail.com",
				isMaster: true,
				confirmationCode: "JohnPrivateMailJohnPrivateMailJohnPrivateMailJohnPri",
				confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
		fixture.John.addToEmails(fixture.JohnPrivateMail)

		fixture.JohnSecondMail = new EmailAccount(
				email: "john.spammail@gmail.com",
				isMaster: false,
				confirmationCode: "JohnSecondMailJohnSecondMailJohnSecondMailJohnSecond",
				confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
		fixture.John.addToEmails(fixture.JohnSecondMail)

		fixture.DoeFamilyMailJohn = new EmailAccount(
				email: "DOE@FASTMAIL.COM",
				isMaster: false,
				confirmationCode: "DoeFamilyMailJohnDoeFamilyMailJohnDoeFamilyMailJohnD",
				confirmationDate: Date.parse( "yyyy-M-d", "2013-02-16" ))
		fixture.John.addToEmails(fixture.DoeFamilyMailJohn)

		// Jane's emails
		fixture.DoeFamilyMailJane = new EmailAccount(
				email: "doe@fastmail.com",
				isMaster: false,
				confirmationCode: "DoeFamilyMailJaneDoeFamilyMailJaneDoeFamilyMailJaneD",
				confirmationDate: Date.parse( "yyyy-M-d", "2011-06-16" ))
		fixture.Jane.addToEmails(fixture.DoeFamilyMailJane)

		fixture.JaneMail = new EmailAccount(
				email: "jane@gmail.com",
				isMaster: true,
				confirmationCode: "JaneMailJaneMailJaneMailJaneMailJaneMailJaneMailJane",
				confirmationDate: Date.parse( "yyyy-M-d", "2010-08-01" ))
		fixture.Jane.addToEmails(fixture.JaneMail)

		// Ludvig's emails
		fixture.LudvigTheThirdMail = new EmailAccount(
				email: "ludvig@hotmail.com",
				isMaster: true,
				confirmationCode: "LudvigMailLudvigMailLudvigMailLudvigMailLudvigMailLu",
				confirmationDate: Date.parse( "yyyy-M-d", "2010-05-28" ))
		fixture.LudvigTheThird.addToEmails(fixture.LudvigTheThirdMail)

		// Eron's emails
		fixture.EronMail = new EmailAccount(
				email: "eron23@gmail.com",
				isMaster: true,
				confirmationCode: "EronMailEronMailEronMailEronMailEronMailEronMailEron")
		fixture.Eron.addToEmails(fixture.EronMail)

		// Ilona's emails
		fixture.IlonaMail = new EmailAccount(
				email: "ilona@hotmail.com",
				isMaster: true,
				confirmationCode: "IlonaMailIlonaMailIlonaMailIlonaMailIlonaMailIlonaMa")
		fixture.Ilona.addToEmails(fixture.IlonaMail)


		
		// Bank accounts
		fixture.LudvigBankAccount1 = new BankAccount(
				accountNmbr: "FI11111")
		fixture.LudvigTheThird.addToBankAccounts(fixture.LudvigBankAccount1)
		fixture.JaneBankAccount1 = new BankAccount(
				accountNmbr: "FI22222")
		fixture.Jane.addToBankAccounts(fixture.JaneBankAccount1)

		// Invoices
		fixture.ZooInvoice = new Invoice(
				bankAccount: "FI11111",
				alias: "dayAtZoo",
				description: "Costs for trip to zoo",
				owner: "ludvig@hotmail.com")
		fixture.MarocTripInvoice = new Invoice(
				bankAccount: "FI22222",
				alias: "maroc",
				description: "The bills for the Maroc trip",
				owner: "jane@gmail.com")
		fixture.GolfInvoice = new Invoice(
				bankAccount: "FI22222",
				alias: "golf",
				description: "expenses for the golf round",
				owner: "jane@gmail.com")


		// Payments
		fixture.ZooFoodPayment = new Payment(
				amount: 56,
				assignedTo: "john.doe@gmail.com",
				description: "French fres and steaks",
				paid: Date.parse( "yyyy-M-d", "2013-02-20" ),
				created: Date.parse( "yyyy-M-d", "2013-02-15" ) )
		fixture.ZooInvoice.addToPayments(fixture.ZooFoodPayment)

		fixture.ZooTicketPayment = new Payment(
				amount: 112.56,
				assignedTo: "doe@fastmail.com",
				description: "tickets",
				created: Date.parse( "yyyy-M-d", "2013-02-15" ) )
		fixture.ZooInvoice.addToPayments(fixture.ZooTicketPayment)

		fixture.MarocFlightsPaymentLudvig = new Payment(
				amount: 1600,
				assignedTo: "ludvig@hotmail.com",
				description: "plane tickets",
				paid: Date.parse( "yyyy-M-d", "2013-04-14" ),
				created: Date.parse( "yyyy-M-d", "2012-08-09" ) )
		fixture.MarocTripInvoice.addToPayments(fixture.MarocFlightsPaymentLudvig)

		fixture.MarocFlightsPaymentEron = new Payment(
				amount: 1600,
				assignedTo: "eron23@hotmail.com",
				description: "plane tickets",
				created: Date.parse( "yyyy-M-d", "2012-08-09" ) )
		fixture.MarocTripInvoice.addToPayments(fixture.MarocFlightsPaymentEron)

		fixture.MarocFlightsPaymentRick = new Payment(
				amount: 1600,
				assignedTo: "rick@hotmail.com",
				description: "plane tickets",
				created: Date.parse( "yyyy-M-d", "2012-08-09" ) )
		fixture.MarocTripInvoice.addToPayments(fixture.MarocFlightsPaymentRick)

		def myService = new AuthenticationService()
		myService.metaClass.checkLogin { login -> return true }
		
		fixture.each { key, value ->  
			
			// Tweak to allow mock AuthenticationUser in unit tests
			if(!app && value instanceof AuthenticationUser){
				value.authenticationService = myService;
			}
			
			value.save(failOnError: true)
		}

		return fixture
	}

}
