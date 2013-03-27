package paymenow.webapp.domain

//import com.grailsrocks.authentication.AuthenticationUser;

class User {

	static hasMany = [emails: EmailAccount, bankAccounts: BankAccount]
	String login // loginID of the authenticationUser TODO: force lowercase
		
    static constraints = {
		emails validator: { emails, user, errors ->
			// Only one of the emails can be declared master
			if(emails.grep({ it.isMaster }).size() != 1){
				errors.rejectValue('emails', 'error.emails.master', 'Exactly one master required')
				return false
			}
			
		}
		
    }
}
