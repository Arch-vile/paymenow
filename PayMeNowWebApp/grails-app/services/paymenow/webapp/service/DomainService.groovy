package paymenow.webapp.service

import paymenow.webapp.domain.*
import org.apache.commons.lang.Validate

class DomainService {

    def getInvoicesForUser(user){
		return Invoice.findAllByOwnerInList(user.emails.email)
	}
	
	// TODO: test that this is transactional by giving oldmail an invalid email address and make sure newmail->false is not persisted.
	// you cannot test that in integration test as they are transactional themselves and thus will be rolled back only later
	// cannot think of better way then just some temporary hack test
	// TODO: in addition this is really to be confirmed that things work without calling save() on domain objects
	def switchMasterEmail(oldMaster, newMaster){
		Validate.isTrue(oldMaster.isMaster, "Old master must be master")
		Validate.isTrue(!newMaster.isMaster, "New master must not be master")
		Validate.notNull(oldMaster.confirmationDate, "Old master must be confirmed")
		Validate.notNull(newMaster.confirmationDate, "New master must be confirmed")
		Validate.isTrue(oldMaster.user == newMaster.user, "Emails must belong to same user")
		oldMaster.isMaster = false
		newMaster.isMaster = true
		oldMaster.save(failOnError: true) 
		newMaster.save(failOnError: true)
	}
}
