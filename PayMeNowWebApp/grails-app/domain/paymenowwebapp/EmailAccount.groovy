package paymenowwebapp

class EmailAccount {

	static belongsTo = [user: User]
	
	String email
	String confirmationCode
	Date confirmationDate
	Boolean isMaster
	
    static constraints = {
		confirmationDate nullable: true
		confirmationCode unique: true // security hardening
		email unique: ['user'] // do not allow same email for same user multiple times
    }
}
