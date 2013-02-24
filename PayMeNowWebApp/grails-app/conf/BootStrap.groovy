import org.springframework.web.context.support.WebApplicationContextUtils

class BootStrap {

	def userManagementService
	
    def init = { servletContext ->
		
		def appCtx = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext)
		
		appCtx.authenticationService.events.onConfirmAccount= { user ->
			return true // always require confirmation no matter who it is or what their email address is
		}
		
		appCtx.authenticationService.events.onSignup = { params ->
			userManagementService.onNewUser(params.user.login, params.user.email)
		}
		
		
    }
    def destroy = {
    }
}
