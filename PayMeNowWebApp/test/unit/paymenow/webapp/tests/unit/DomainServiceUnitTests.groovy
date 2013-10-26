package paymenow.webapp.tests.unit



import grails.plugin.fixtures.FixtureLoader

import grails.test.mixin.*
import org.junit.*

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import paymenow.webapp.domain.*
import paymenow.webapp.service.DomainService;
import paymenow.webapp.test.BaseFixtureLoader
import grails.buildtestdata.mixin.Build

import com.grailsrocks.authentication.AuthenticationUser
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DomainService)
@Mock([EmailAccount, User, Invoice, Payment, User, AuthenticationUser, BankAccount])
class DomainServiceUnitTests  {

	
	def fixture
	
	void setUp() {
		fixture = new BaseFixtureLoader().load()	
	}
	
	
	@Test
	void getInvoicesByOwner(){
		assertThat(service.getInvoicesForUser(fixture['Jane']), 
			containsInAnyOrder(
				fixture['MarocTripInvoice'],
				fixture['GolfInvoice']))
	}
	
	@Test
    void switchMasterEmailHappyCase() {
		assert fixture['JohnPrivateMail'].isMaster == true
		assert fixture['JohnSecondMail'].isMaster == false
		service.switchMasterEmail(fixture['JohnPrivateMail'], fixture['JohnSecondMail'])
		assert fixture['JohnPrivateMail'].isMaster == false
		assert fixture['JohnSecondMail'].isMaster == true
    }
	
	@Test
	void switchMasterEmailRequiresOriginalMaster() {
		assert shouldFail(IllegalArgumentException) {
			fixture['JohnPrivateMail'].isMaster = false
			service.switchMasterEmail(fixture['JohnPrivateMail'], fixture['JohnSecondMail'])
		} == "Old master must be master"
	}
	
	@Test
	void switchMasterEmailRequiresNonMaster() {
		assert shouldFail(IllegalArgumentException) {
			fixture['JohnSecondMail'].isMaster = true
			service.switchMasterEmail(fixture['JohnPrivateMail'], fixture['JohnSecondMail'])
		} == "New master must not be master"
	}
	
	@Test
	void switchMasterEmailRequiresCorrectMasters() {
		shouldFail(IllegalArgumentException) {
			fixture['JohnPrivateMail'].isMaster = false
			fixture['JohnSecondMail'].isMaster = true
			service.switchMasterEmail(fixture['JohnPrivateMail'], fixture['JohnSecondMail'])
		}
	}
	
	
	@Test
	void emailMasterSwitchRequiresConfirmedOld(){
		assert shouldFail(IllegalArgumentException) {
			fixture['JohnPrivateMail'].confirmationDate = null
			service.switchMasterEmail(fixture['JohnPrivateMail'], fixture['JohnSecondMail'])
		} == "Old master must be confirmed"
	}
	
	@Test
	void emailMasterSwitchRequiresConfirmedNew(){
		assert shouldFail(IllegalArgumentException) {
			fixture['JohnSecondMail'].confirmationDate = null
			service.switchMasterEmail(fixture['JohnPrivateMail'], fixture['JohnSecondMail'])
		} == "New master must be confirmed"
	}

	@Test
	void emailMasterSwitchOnlyForSameUser(){
		assert shouldFail(IllegalArgumentException) {
			service.switchMasterEmail(fixture['JohnPrivateMail'], fixture['DoeFamilyMailJane'])
		} == "Emails must belong to same user"
	}
	
}
