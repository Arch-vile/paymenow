package paymenow.webapp.domain

import org.codehaus.groovy.grails.exceptions.GrailsException
import paymenow.webapp.domain.DomainViolationException

class EmailAccount {
	
	static belongsTo = [user: User]
	
	String email
	String confirmationCode
	Date confirmationDate
	Boolean isMaster
	
    static constraints = {
		email email:true, unique: ['user'], blank: false
		confirmationDate nullable: true
		confirmationCode unique: true, size: 52..52 // TODO: should use value from config
	}
	
	def beforeValidate() {
		email = email?.toLowerCase()
	}
	
	
	def beforeInsert(){
		EmailAccount.withNewSession {
			if(isMaster){
				if(EmailAccount.findAllByUserAndIsMaster(user,true).size() != 0){
					throw new DomainViolationException('Cannot save. Only one master email allowed.')
				}
			}
		}
	}

}
