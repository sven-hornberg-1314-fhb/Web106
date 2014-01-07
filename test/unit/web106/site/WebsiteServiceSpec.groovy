package web106.site

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(WebsiteService)
@Mock([Page,Template, Website,PageService])
class WebsiteServiceSpec extends Specification {

    @Shared Template template
    @Shared Website website

    /*
    @Autowired
    private PageService pageService

    void setupSpec() {
        defineBeans {
            pageService PageService, ref('grailsApplication')

        }
    }
*/
    def setup() {
        template = new Template(name:'Berlin')

        website = new Website(title : 'simpleWebsite')
        website.page = []

//        service.pageService =  applicationContext.getBean('PageService')
    }

    def cleanup() {
    }


    void "Test if PageService is ready" (){
        assert grailsApplication.mainContext.getBean('PageService') != null
    }


    def "create Pages from Website with more than 1 Page"() {

        setup:

            def visibleFromDate = new Date()
            def visibleToDate = new Date()

            def index = new Page(title: "index", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
            index.template = template

            website.page.add(index)
            index.website = website


            index.save(flush: true, failOnError: true)

            def about = new Page(title: "about", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
            about.template = template

            website.page.add(about)
            about.website = website


            about.save(flush: true, failOnError: true)


        when:
            Map<String,String> mapPages = service.createPagesForWebsite(website)

        then:

            mapPages.keySet().size() == 2
    }
}
