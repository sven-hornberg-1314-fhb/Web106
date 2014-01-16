package web106.site

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([Template])
class TemplateSpec extends Specification {

    def "Create Template Potsdam"() {

        setup:
            Template potsdam = new Template()
            potsdam.name = "Potsdam"

        when:
            potsdam.save()

        then:
            potsdam.id > 0
    }
}
