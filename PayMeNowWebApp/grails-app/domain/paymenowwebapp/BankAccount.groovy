package paymenowwebapp

class BankAccount {

	static belongsTo = [user: User]
	
	String accountNmbr
	String description
	
    static constraints = {
    }
}
