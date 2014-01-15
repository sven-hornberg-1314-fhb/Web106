package web106

import grails.test.mixin.TestFor
import spock.lang.Specification
import web106.file.FileService

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FileService)
class FileServiceSpec extends Specification {

    def "create Tempfile"() {

        setup:

            String fileName = "tempfilegrails.tmp"
            String content = "Ein kleiner Testinhalt"


        when:

            File tempFile =  service.createTempFile("",fileName, content)


        then:

            tempFile.exists()

    }

    def "create Tempfile and delete"() {

        setup:

        String fileName = "tempfilegrails.tmp"
        String content = "Ein kleiner Testinhalt"


        when:

        File tempFile =  service.createTempFile("",fileName, content)
        service.deleteTempFile("",fileName)

        then:

        !tempFile.exists()

    }


}
