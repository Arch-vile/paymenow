package paymenow.webapp.domain

class DummyDomain {

	String name
	Integer number
	
    static constraints = {
		name size: 10..15
		number range: 100..200  
    }
}
