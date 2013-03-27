package paymenow.webapp.domain

class Payment {

	static belongsTo = [bill: Invoice]
	
	String assignedTo // email TODO: force lowercase
	String description
	Date paymentDate
	Date creationDate
	Double amount
		
    static constraints = {
		paymentDate nullable: true
    }
}
