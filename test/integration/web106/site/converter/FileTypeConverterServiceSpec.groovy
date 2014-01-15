package web106.site.converter

import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.plugins.testing.GrailsMockMultipartFile
import org.springframework.web.multipart.MultipartFile
import spock.lang.Ignore
import spock.lang.Specification
import web106.converter.FileTypeConverterService

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FileTypeConverterService)
class FileTypeConverterServiceSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    @Ignore
    def "Convert a MultipartFile to File"() {

        setup:

            MultipartFile multipartFile = new GrailsMockMultipartFile("test.jpg", "myspecialcontent".bytes)

        when:

            File file = service.convert(multipartFile)

        then:

            file != null
    }
}
