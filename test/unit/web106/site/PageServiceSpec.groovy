package web106.site

import grails.gsp.PageRenderer
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.web.GroovyPageUnitTestMixin
import spock.lang.Specification

import web106.auth.*
import web106.site.component.ContentComponent

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */

@TestFor(PageService)
@Mock([Page,Template,Box,ContentComponent,WorkGroup])
@TestMixin(GroovyPageUnitTestMixin)
class PageServiceSpec extends Specification {

    def PageService pageService



    def setup() {

    }

    def cleanup() {
    }

    void "Render Page to HTML for berlin template"() {
        setup:

            def website = new Website(title : 'simpleWebsite')
            website.page = []

            def visibleFromDate = new Date()
            def visibleToDate = new Date()

            def newPage = new Page(title: "berlinPageservice", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
            Template template = new Template(name:'Berlin')
            template.save()

            WorkGroup testGroup = new WorkGroup()
            testGroup.name = "testGroup"
            testGroup.save()

            newPage.template = template
            newPage.title = "Welcome to my new Page!"

            Box header = new Box()
            header.idName = 'header'
            header.component = []

            ContentComponent ccHello = new ContentComponent()
            ccHello.text = "HelloRenderText"
            ccHello.name = "hellotext"
            ccHello.workGroup = testGroup
            ccHello.save(failOnError: true, flush: true)

            header.component.add(ccHello)
            header.save(failOnError: true, flush: true)

            def boxesSet = []
            boxesSet.add(header)
            newPage.boxes = boxesSet as Set

            website = new Website(title : 'simpleWebsite')
            website.page = []

            website.page.add(newPage)
            newPage.website = website


        newPage.save(failOnError: true, flush: true)

        when:

            def model = service.ModelforPageRendering(newPage)
            String contents = render(template:'/template/'+model.template +'/template', model:model)

        then:

            contents.contains(ccHello.text)


    }
}
