package paymenow.webapp.tests.unit



import grails.plugin.fixtures.FixtureLoader

import grails.test.mixin.*
import org.junit.*

import paymenow.webapp.domain.*
import paymenow.webapp.service.DomainService;
import grails.buildtestdata.mixin.Build
/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DomainService)
class DomainServiceUnitTests extends BaseFixtureTests {

//	def fixture['JohnPrivateMail']
//	def fixture['JohnSecondMail']
//	
//	@Before
//	void setUp(){
//		fixture['JohnPrivateMail'] = EmailAccount.build(isMaster: true)
//		fixture['JohnSecondMail'] = EmailAccount.build(isMaster: false, user: fixture['JohnPrivateMail'].user)
//	}
	
	
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
