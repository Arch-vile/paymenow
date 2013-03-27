package paymenow.webapp.security

import org.codehaus.groovy.grails.exceptions.GrailsException


class SecurityException extends GrailsException {

	def SecurityException(message){
		super(message);
	}
	
}
