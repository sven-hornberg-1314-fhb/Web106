package web106

import grails.test.mixin.TestFor
import spock.lang.Specification
import web106.file.FileService

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FileService)
class FileServiceSpec extends Specification {

    def FileService fileService

    def setup() {
    }

    def cleanup() {
    }

    def "create Tempfile"() {

        setup:

            String fileName = "tempfilegrails.tmp"
            String content = "Ein kleiner Testinhalt"


        when:

            File tempFile =  fileService.createTempFile("",fileName, content)


        then:

            tempFile.exists() == true

    }

    def "create Tempfile and delete"() {

        setup:

        String fileName = "tempfilegrails.tmp"
        String content = "Ein kleiner Testinhalt"


        when:

        File tempFile =  fileService.createTempFile("",fileName, content)
        fileService.deleteTempFile("",fileName)

        then:

        tempFile.exists() == false

    }

    def
}
