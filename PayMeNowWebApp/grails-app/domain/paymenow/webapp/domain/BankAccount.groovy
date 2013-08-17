package paymenow.webapp.domain

class BankAccount {

	static belongsTo = [user: User]

	String accountNmbr // TODO: force uppercase
	
    static constraints = {
    }
}
