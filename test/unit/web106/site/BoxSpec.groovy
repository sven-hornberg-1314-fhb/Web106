package web106.site

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([Box])
class BoxSpec extends Specification {


    void "Create new Box and save"() {

        setup:

            Box boxFooter = new Box()
            boxFooter.idName = "footer"

        when:
            boxFooter.save(failOnError: true)

        then:
            boxFooter.id > 0

    }
}
