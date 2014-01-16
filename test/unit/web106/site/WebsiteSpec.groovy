package web106.site

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import web106.auth.WorkGroup

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([Page,Template, Website, WorkGroup])
class WebsiteSpec extends Specification {


    void "Create Website with Page and Title"() {

        setup:


            WorkGroup wG = new WorkGroup()
            wG.name = "Workgroup1"



            Template template = new Template(name:'Berlin')

            Website website = new Website(title : 'simpleWebsite')
            website.page = []


            def visibleFromDate = new Date()
            def visibleToDate = new Date()

            def newPage = new Page(title: "saveMe", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
            newPage.template = template

            website.page.add(newPage)
            newPage.website = website


            newPage.save(flush: true, failOnError: true)


            wG.website = []
            wG.website.add(website)
            wG.save()

            website.workGroup = wG

        when:
            website.save(failOnError: true)

        then:

            website.id > 0
    }
}
