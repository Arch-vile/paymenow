package paymenow.webapp.domain

class Invoice {
	
	static hasMany = [payments: Payment]
	
	String adminUrl
	String password
	String alias // TODO: force lowercase
	String description
	String bankAccount // TODO: force uppercase
	String owner // email of the owner
		
    static constraints = {
		password nullable: true
		adminUrl nullable: true
		alias unique: true, size: 2..100
    }
}
