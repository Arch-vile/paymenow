package paymenowwebapp

class HomeController {

	def authenticationService
	
    def home() { 
		if( authenticationService.isLoggedIn(request) )
			chain(controller: "account")
	}
}
