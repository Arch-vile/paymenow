package paymenowwebapp

class Payment {

	static belongsTo = [bill: Invoice]
	
	String assignedTo // email
	String description
	Date paymentDate
	Date creationDate
	Double amount
		
    static constraints = {
		paymentDate nullable: true
    }
}
