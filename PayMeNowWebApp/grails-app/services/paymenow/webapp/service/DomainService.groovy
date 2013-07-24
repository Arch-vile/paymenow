package paymenow.webapp.service

class DomainService {

    def getInvoicesByOwner(listOfEmails){
		return Invoice.findAllByOwnerInList(listOfEmails)
	}
}
