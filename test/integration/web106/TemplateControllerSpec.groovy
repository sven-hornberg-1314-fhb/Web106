package web106

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*
import web106.auth.Role
import web106.template.TemplateController

/**
 * spocktests for templates
 */
@TestFor(TemplateController)
@Mock([Role])
class TemplateControllerSpec extends Specification {

    void "Defaultmodel Berlin contains Boxes"() {

        setup:
            def templateController = new TemplateController()

        when:
            templateController.berlin()
            def test = templateController.response.contentAsString

        then:
           templateController.response.contentAsString != null
           templateController.response.contentAsString.contains('sidebar1')
           templateController.response.contentAsString.contains('footer2')

    }

}
