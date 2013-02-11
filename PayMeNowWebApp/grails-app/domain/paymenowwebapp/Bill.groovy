package paymenowwebapp

class Bill {

	static hasMany = [payments: Payment]
	static belongsTo = [user: User]
	
	String adminUrl
	String password
	String alias
	String description
	
	
    static constraints = {
    }
}
