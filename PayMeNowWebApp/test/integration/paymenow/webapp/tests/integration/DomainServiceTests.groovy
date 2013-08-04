package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import org.junit.*
import paymenow.webapp.domain.EmailAccount


class DomainServiceTests {
	
	def fixtureLoader
	def fixture
	def domainService
	
    @Before
    void setUp() {
		fixture = fixtureLoader.load("baseFixture")
    }

    @After
    void tearDown() {
        // Tear down logic here
    }
	
	@Test
	void happyCase() {
		
	}
	
	@Test
	void getInvoicesByOwner(){
		assert false
	}
	
	@Test
	void findUser(){
		assert false
	}
	
	@Test
	void findEmailAccount(){
		assert false
	}
	
	
	
	
}
