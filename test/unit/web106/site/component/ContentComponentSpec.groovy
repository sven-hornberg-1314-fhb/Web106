package web106.site.component

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import web106.auth.WorkGroup
import web106.site.Box

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([WorkGroup, Box])
@TestFor(ContentComponent)
class ContentComponentSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

	def "create ContentComponent" () {
		
		setup:
			def contentComponent = new ContentComponent()
	
		when:
		
			contentComponent.name = "Welcome"
		
		then:
		
			contentComponent.name == "Welcome"
	}
}
