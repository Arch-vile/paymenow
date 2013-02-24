package paymenowwebapp

class AccountController {

	def authenticationService
	
    def account() { 
		
		if( !authenticationService.isLoggedIn(request) )
			chain(controller: "home")
	}
}
