package paymenowwebapp

class EmailAccount {

	static belongsTo = [user: User]
	
	String email
	String confirmationCode
	Date confirmationDate
	Boolean isMaster
	
    static constraints = {
		confirmationDate nullable: true
		confirmationCode unique: true
    }
}
