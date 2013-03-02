package paymenowwebapp

//import com.grailsrocks.authentication.AuthenticationUser;

class User {

	static hasMany = [emailAccounts: EmailAccount, bankAccounts: BankAccount]
	String login // loginID of the authenticationUser
		
    static constraints = {
    }
}
