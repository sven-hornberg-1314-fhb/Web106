package web106.export

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ExportController)
class ExportControllerSpec extends Specification {

    def "Route to clouds3"() {
        controller.cloudS3()

        assert response.text.contains('Export Cloud S3')
    }
}
