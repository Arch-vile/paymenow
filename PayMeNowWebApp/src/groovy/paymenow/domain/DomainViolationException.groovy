package paymenow.domain

import org.codehaus.groovy.grails.exceptions.GrailsException;

class DomainViolationException extends GrailsException {

	def DomainViolationException(message){
		super(message)
	}
	
}