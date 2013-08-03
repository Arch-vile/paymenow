package paymenow.webapp.domain

//import com.grailsrocks.authentication.AuthenticationUser;

class User {

	static hasMany = [emails: EmailAccount, bankAccounts: BankAccount]
	String login // loginID of the authenticationUser TODO: force lowercase
		
    static constraints = {
		
		
    }
}
