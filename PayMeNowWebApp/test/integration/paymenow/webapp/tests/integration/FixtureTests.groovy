package paymenow.webapp.tests.integration

import static org.junit.Assert.*


import org.junit.*

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
    void johnIsLoadedByFixtures() {
        assert fixture.John.login == "johnDoe"
    }
}
