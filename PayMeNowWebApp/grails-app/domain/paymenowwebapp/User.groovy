package paymenowwebapp

import com.grailsrocks.authentication.AuthenticationUser;

class User {

	static hasMany = [bills: Bill, bankAccounts: BankAccount]
	AuthenticationUser authenticationUser
	
	String email
	
    static constraints = {
    }
}
