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
@Build([EmailAccount, User])
class DomainServiceUnitTests {

	def oldMaster
	def newMaster
	
	@Before
	void setUp(){
		oldMaster = EmailAccount.build(isMaster: true)
		newMaster = EmailAccount.build(isMaster: false, user: oldMaster.user)
	}
	
	
	@Test
    void switchMasterEmailHappyCase() {
		assert newMaster.confirmationDate != null
		assert oldMaster.confirmationDate != null
		service.switchMasterEmail(oldMaster, newMaster)
		assert !oldMaster.isMaster
		assert newMaster.isMaster
    }
	
	@Test
	void switchMasterEmailRequiresOriginalMaster() {
		assert shouldFail(IllegalArgumentException) {
			oldMaster.isMaster = false
			service.switchMasterEmail(oldMaster, newMaster)
		} == "Old master must be master"
	}
	
	@Test
	void switchMasterEmailRequiresNonMaster() {
		assert shouldFail(IllegalArgumentException) {
			newMaster.isMaster = true
			service.switchMasterEmail(oldMaster, newMaster)
		} == "New master must not be master"
	}
	
	@Test
	void switchMasterEmailRequiresCorrectMasters() {
		shouldFail(IllegalArgumentException) {
			oldMaster.isMaster = false
			newMaster.isMaster = true
			service.switchMasterEmail(oldMaster, newMaster)
		}
	}
	
	
	@Test
	void emailMasterSwitchRequiresConfirmedOld(){
		assert shouldFail(IllegalArgumentException) {
			oldMaster.confirmationDate = null
			service.switchMasterEmail(oldMaster, newMaster)
		} == "Old master must be confirmed"
	}
	
	@Test
	void emailMasterSwitchRequiresConfirmedNew(){
		assert shouldFail(IllegalArgumentException) {
			newMaster.confirmationDate = null
			service.switchMasterEmail(oldMaster, newMaster)
		} == "New master must be confirmed"
	}

	@Test
	void emailMasterSwitchOnlyForSameUser(){
		assert shouldFail(IllegalArgumentException) {
			newMaster.user = User.build()
			service.switchMasterEmail(oldMaster, newMaster)
		} == "Emails must belong to same user"
	}
	
}
