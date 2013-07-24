import paymenow.webapp.domain.User
import paymenow.webapp.domain.EmailAccount
import com.mdimension.jchronic.*



fixture {
	JohnPrivateMail2(EmailAccount,
		email: "john@myhost.com",
		isMaster: false,
		confirmationCode: "john@myhost.comjohn@myhost.comjohn@myhost.comjohn@my")
	JohnPrivateMail(EmailAccount,
		email: "john.doe@gmail.com",
		isMaster: true,
		confirmationCode: "john.doe@gmail.comjohn.doe@gmail.comjohn.doe@gmail.c",
		confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
	JohnSecondMail(EmailAccount,
		email: "john.spammail@gmail.com",
		isMaster: false,
		confirmationCode: "john.spammail@gmail.comjohn.spammail@gmail.comjohn.s",
		confirmationDate: Date.parse( "yyyy-M-d", "2013-01-01" ))
	DoeFamilyMailJohn(EmailAccount,
		email: "doe@fastmail.com",
		isMaster: false,
		confirmationCode: "doe@fastmail.comdoe@fastmail.comdoe@fastmail.comdoe@",
		confirmationDate: Date.parse( "yyyy-M-d", "2013-02-16" ))
	John(User, login: "johnDoe", emails: [JohnPrivateMail2, JohnPrivateMail])
}
	
