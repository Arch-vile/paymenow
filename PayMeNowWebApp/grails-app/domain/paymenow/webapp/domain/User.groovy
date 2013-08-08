package paymenow.webapp.domain

import com.grailsrocks.authentication.AuthenticationUser;

class User {

	static hasMany = [emails: EmailAccount, bankAccounts: BankAccount]
	AuthenticationUser authUser
		
    static constraints = {
		authUser unique: true
    }
}
