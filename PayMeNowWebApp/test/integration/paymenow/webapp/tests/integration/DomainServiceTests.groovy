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
	void findUserCaseInsensitive(){
		assert false
	}
	
	@Test
	void findEmailCaseInsensitive(){
		def user = domainService.findUser("johnDoe")
		assert false
	}
	
	
	
	
}
