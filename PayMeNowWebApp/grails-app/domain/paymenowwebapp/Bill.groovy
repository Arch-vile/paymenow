package paymenowwebapp

class Bill {
	
	static hasMany = [invoices: Invoice]
	static belongsTo = [user: User]
	
	
	String adminUrl
	String password
	String alias
	String description
	String bankAccount
	
    static constraints = {
    }
}
