package web106.site

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Ignore
import spock.lang.Shared
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(WebsiteService)
@Mock([Page,Template, Website,PageService])
@Ignore //Not testable,cause of nested service and groovypageRender which is null even with -integration
class WebsiteServiceSpec extends Specification {

    @Shared Template template
    @Shared Website website


    def setup() {

        template = new Template(name:'Berlin')

        website = new Website(title : 'simpleWebsite')
        website.page = []

    }

    /**
     * Nested Pageservice, with pageRenderer -> needs to be startet with --integration
     */
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
