package paymenowwebapp

import com.grailsrocks.authentication.AuthenticationUser;

class User {

	static hasMany = [emailAccounts: EmailAccount, bankAccounts: BankAccount]
	String authenticationUserLink // loginID of the authenticationUser
		
    static constraints = {
    }
}
