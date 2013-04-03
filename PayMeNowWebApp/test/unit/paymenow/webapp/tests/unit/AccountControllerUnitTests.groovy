package paymenow.webapp.tests.unit

import static org.junit.Assert.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import groovy.mock.interceptor.MockFor
import javax.servlet.http.HttpServletRequest
import org.junit.*


import paymenow.webapp.controller.AccountController;
import paymenow.webapp.service.UserManagementService
import paymenow.webapp.service.CurrentUserService

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@TestFor(AccountController)
class AccountControllerUnitTests {

	
	
    void setUp() {
		def userManagementServiceMock = new MockFor(UserManagementService)
		userManagementServiceMock.demand.isLoggedIn { HttpServletRequest request -> return true }
		controller.userManagementService = userManagementServiceMock.proxyInstance()
		
		def currentUserServiceMock = new MockFor(CurrentUserService)
		currentUserServiceMock.demand.getOwnedInvoices {return []}
		currentUserServiceMock.demand.getEmails { return [] }
		controller.currentUserService = currentUserServiceMock.proxyInstance()
    }

    void tearDown() {
        // Tear down logic here
    }

    void testIndexRedirectIfNotLoggedIn() {
		def userManagementServiceMock = new MockFor(UserManagementService)
		userManagementServiceMock.demand.isLoggedIn { HttpServletRequest request -> return false }
		controller.userManagementService = userManagementServiceMock.proxyInstance()
		controller.index()
		assert response.redirectedUrl == '/home'
    }
	
	void testIndexIfLoggedIn() {
		controller.index()
		assert view == "/account/accountIndex"
	}
	
}
