import paymenow.webapp.domain.User
import paymenow.webapp.domain.*

fixture {

	// John's emails
	JohnPrivateMail2(EmailAccount,
		email: "john@myhost.com",
		isMaster: false,
		confirmationCode: "JohnPrivateMail2JohnPrivateMail2JohnPrivateMail2John")
	JohnPrivateMail(EmailAccount,
		email: "john.doe@gmail.com",
		isMaster: true,
		confirmationCode: "JohnPrivateMailJohnPrivateMailJohnPrivateMailJohnPri",
		confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
	JohnSecondMail(EmailAccount,
		email: "john.spammail@gmail.com",
		isMaster: false,
		confirmationCode: "JohnSecondMailJohnSecondMailJohnSecondMailJohnSecond",
		confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
	DoeFamilyMailJohn(EmailAccount,
		email: "doe@fastmail.com",
		isMaster: false,
		confirmationCode: "DoeFamilyMailJohnDoeFamilyMailJohnDoeFamilyMailJohnD",
		confirmationDate: Date.parse( "yyyy-M-d", "2013-02-16" ))
	
	
	// Jane's emails
	DoeFamilyMailJane(EmailAccount,
		email: "doe@fastmail.com",
		isMaster: false,
		confirmationCode: "DoeFamilyMailJaneDoeFamilyMailJaneDoeFamilyMailJaneD",
		confirmationDate: Date.parse( "yyyy-M-d", "2011-06-16" ))
	JaneMail(EmailAccount,
		email: "jane@gmail.com",
		isMaster: true,
		confirmationCode: "JaneMailJaneMailJaneMailJaneMailJaneMailJaneMailJane",
		confirmationDate: Date.parse( "yyyy-M-d", "2010-08-01" ))
	
	// Ludvig's emails
	LudvigMail(EmailAccount,
		email: "ludvig@hotmail.com",
		isMaster: true,
		confirmationCode: "LudvigMailLudvigMailLudvigMailLudvigMailLudvigMailLu",
		confirmationDate: Date.parse( "yyyy-M-d", "2010-05-28" ))
	
	// Eron's emails
	EronMail(EmailAccount,
		email: "eron23@gmail.com",
		isMaster: true,
		confirmationCode: "EronMailEronMailEronMailEronMailEronMailEronMailEron")
	
	// Ilona's emails
	IlonaMail(EmailAccount,
		email: "ilona@hotmail.com",
		isMaster: true,
		confirmationCode: "IlonaMailIlonaMailIlonaMailIlonaMailIlonaMailIlonaMa")
	
	// Users
	John(User, login: "johnDoe", emails: [JohnPrivateMail2, JohnPrivateMail, JohnSecondMail, DoeFamilyMailJohn])
	Jane(User, login: "janeDoe", emails: [DoeFamilyMailJane, JaneMail])
	LudvigTheThird(User, login: "ludvig", emails: [LudvigMail])
	Eron(User, login: "eronrentle", emails: [EronMail])
	Ilona(User, login: "ilona", emails: [IlonaMail])
	
	
	// Payments
	ZooFoodPayment(Payment,
		amount: 56, 
		assignedTo: "john.doe@gmail.com",
		description: "French fres and steaks",
		paid: Date.parse( "yyyy-M-d", "2013-02-20" ),
		created: Date.parse( "yyyy-M-d", "2013-02-15" ) )
	ZooTicketPayment(Payment,
		amount: 112.56,
		assignedTo: "doe@fastmail.com",
		description: "tickets",
		created: Date.parse( "yyyy-M-d", "2013-02-15" ) )
	MarocFlightsPaymentLudvig(Payment,
		amount: 1600,
		assignedTo: "ludvig@hotmail.com",
		description: "plane tickets",
		paid: Date.parse( "yyyy-M-d", "2013-04-14" ),
		created: Date.parse( "yyyy-M-d", "2012-08-09" ) )
	MarocFlightsPaymentEron(Payment,
		amount: 1600,
		assignedTo: "eron23@hotmail.com",
		description: "plane tickets",
		created: Date.parse( "yyyy-M-d", "2012-08-09" ) )
	MarocFlightsPaymentRick(Payment,
		amount: 1600,
		assignedTo: "rick@hotmail.com",
		description: "plane tickets",
		created: Date.parse( "yyyy-M-d", "2012-08-09" ) )
	
	// Invoices
	ZooInvoice(Invoice,
		bankAccount: "FI11111",
		alias: "dayAtZoo",
		description: "Costs for trip to zoo",
		owner: "ludvig@hotmail.com",
		payments: [ZooFoodPayment, ZooTicketPayment])
	MarocTripInvoice(Invoice,
		bankAccount: "FI22222",
		alias: "maroc",
		description: "The bills for the Maroc trip",
		owner: "jane@gmail.com",
		payments: [MarocFlightsPaymentLudvig, MarocFlightsPaymentEron, MarocFlightsPaymentRick])
	
}