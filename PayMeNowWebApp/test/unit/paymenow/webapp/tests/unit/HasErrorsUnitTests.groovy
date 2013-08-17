

package paymenow.webapp.tests.unit


import org.junit.Test
import grails.test.mixin.TestMixin
import grails.test.mixin.support.*
import grails.test.mixin.Mock

import paymenow.webapp.domain.*
import paymenow.webapp.test.BaseFixtureLoader

import paymenow.webapp.matchers.HasErrors

import static org.hamcrest.Matchers.*
import static paymenow.webapp.matchers.HasErrors.*

@TestMixin(GrailsUnitTestMixin)
@Mock([DummyDomain])
class HasErrorsUnitTests {
	
	void setUp() {
		
	}

    void tearDown() {
    }
	
	@Test
	void hasErrors() {
		DummyDomain domain = new DummyDomain(name: "short", number: "2")
		assertThat(domain,
			domainWithErrors(number: "range.toosmall", name: "size.toosmall"))//nname: "name is too short", number: "number is too small"))
	}
	
    
}
