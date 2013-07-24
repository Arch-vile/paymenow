
import paymenow.webapp.domain.User
import paymenow.webapp.domain.EmailAccount

fixture {
	
	emailMikko(EmailAccount,
		email: "mikko@gmail.com",
		isMaster: true,
		confirmationCode: "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
	mikko(User, login: "mikko", emails: [emailMikko])


}
	
