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
		email email:true, unique: ['user'], blank: false, maxSize: 70
		confirmationDate nullable: true
		confirmationCode unique: true, size: 52..52, blank: false // TODO: should use value from config
		isMaster validator: { val , obj ->
			
			if(obj.user){
				if(val){
					return obj.user.emails.grep { it.isMaster && it != obj	}.size() > 0 ? "onlyOneMasterAllowed" : true
				} else {
					return obj.user.emails.grep { it.isMaster }.size() != 1 ? "oneMasterRequired" : true
				}
			}
		}
	}
	
	public void setEmail(String value){
		email = value?.toLowerCase()
	}

}
