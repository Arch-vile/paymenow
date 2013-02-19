package paymenowwebapp

class Bill {
	
	static hasMany = [invoices: Invoice]
	
	String adminUrl
	String password
	String alias
	String description
	String bankAccount
	String owner // email of the owner
		
    static constraints = {
    }
}
