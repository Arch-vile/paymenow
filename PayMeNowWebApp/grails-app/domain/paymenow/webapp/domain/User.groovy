package paymenow.webapp.domain

import com.grailsrocks.authentication.AuthenticationUser;

class User {
	
	static hasMany = [bankAccounts: BankAccount, emails: EmailAccount]
	AuthenticationUser authUser
		
    static constraints = {
		authUser unique: true
    }
}
