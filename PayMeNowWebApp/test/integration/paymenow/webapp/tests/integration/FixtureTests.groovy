package paymenow.webapp.tests.integration

import static org.junit.Assert.*
import org.junit.*
import paymenow.webapp.domain.Payment

class FixtureTests {
	
	def fixtureLoader
	def fixture

    @Before
    void setUp() {
        fixture = fixtureLoader.load("baseFixture")
    }

    @After
    void tearDown() {
		
	}

    @Test
    void fixturesLoaded() {
        assert fixture.John.login == "johnDoe"
		assert fixture.MarocTripInvoice.payments.contains(fixture.MarocFlightsPaymentLudvig)
    }
	
	
}
