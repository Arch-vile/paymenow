package paymenowwebapp

class Invoice {
	
	static hasMany = [payments: Payment]
	
	String adminUrl
	String password
	String alias
	String description
	String bankAccount
	String owner // email of the owner
		
    static constraints = {
		password nullable: true
		adminUrl nullable: true
		alias unique: true, size: 2..100
    }
}
