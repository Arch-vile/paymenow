package paymenow.webapp.controller

class HomeController {
	
	def userManagementService
	
    def home() { 
		if( userManagementService.isLoggedIn(request) )
			chain(controller: "account")
	}
}
