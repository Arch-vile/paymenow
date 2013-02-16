package paymenowwebapp

class Invoice {

	static belongsTo = [bill: Bill]
	
	String description
	Date paymentDate
	Date creationDate
	Double amount
		
    static constraints = {
    }
}
