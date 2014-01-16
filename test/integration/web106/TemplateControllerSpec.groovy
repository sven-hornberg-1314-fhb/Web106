package web106

import grails.test.mixin.TestFor
import spock.lang.*
import web106.template.TemplateController

/**
 * spocktests for templates
 */
@TestFor(TemplateController)
class TemplateControllerSpec extends Specification {

    void "Defaultmodel Berlin contains Boxes"() {

        when:
            controller.berlin()

        then:
            response.status == 200


    }

}
