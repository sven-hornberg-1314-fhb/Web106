package web106

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*
import web106.auth.Role
import web106.auth.WorkGroupController

/**
 * spocktests for workgroupcontroller
 */
@TestFor(WorkGroupController)
@Mock([Role])
class WorkGroupControllerSpec extends Specification {


    def "Test if GET is not allowed for selectWorkgroup"() {

            request.method = "GET"
            def workGroupController = new WorkGroupController()

            workGroupController.selectWorkGroup()

            assertTrue(workGroupController.response.status == 404)

    }
}
