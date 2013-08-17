package paymenow.webapp.domain

class Payment {

	// TODO: test the belongsTo association
	static belongsTo = [bill: Invoice]
	
	String assignedTo // email TODO: force lowercase
	String description
	Date paid
	Date created
	Double amount
		
    static constraints = {
		paid nullable: true
    }
	
}
