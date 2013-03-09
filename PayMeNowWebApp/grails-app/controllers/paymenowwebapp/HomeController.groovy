package paymenowwebapp

class HomeController {
	
	def userManagementService
	
    def home() { 
		if( userManagementService.isLoggedIn(request) )
			chain(controller: "account")
	}
}
