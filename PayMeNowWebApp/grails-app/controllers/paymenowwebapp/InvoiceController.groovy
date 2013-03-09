package paymenowwebapp

class InvoiceController {

	def userManagementService
	
	def create(){
		log.info("create()")
		def invoice = new Invoice(description: "provide description")
		
		for(i in 0..5){
			invoice.addToPayments(new Payment())
		}
		
		return [invoice: invoice]
	}
	
	
	// NOTE. the order of the submitted elements. make sure on that on edit correct elements are edited
	def save(){
		
		log.info("Saving")
		
		def invoice = new Invoice(params)
		invoice.owner = userManagementService.getUserMasterEmail()
		
		invoice.payments.removeAll( invoice.payments.grep { !it.assignedTo || !it.description || !it.amount } )
		invoice.payments.each {
			it.creationDate = new Date()
		}
		
		if(invoice.hasErrors()){
			log.info("Invoice hasErrors on bind")
			invoice.errors.each {
				log.error(it)
			}
		}
		
		if(!invoice.save()){
			log.info("Invoice hasErrors on save")
			invoice.errors.each {
				log.error(it)
			}
			
		}else {
			log.info("Saved: ");
			def message = "Saved successfully"
			render(view: "/messageViewer", model: [message: message])
		}
		
	}
}
