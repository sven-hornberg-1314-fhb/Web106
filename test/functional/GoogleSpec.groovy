
/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 18.12.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
import geb.spock.GebSpec
import pages.GoogleHomePage
import pages.GoogleResultsPage
import pages.WikipediaPage
import modules.GoogleSearchModule

class GoogleSpec extends GebSpec {

    def "first result for wikipedia search should be wikipedia"() {
        given:
        to GoogleHomePage

        expect:
        at GoogleHomePage

        when:
        search.field.value("wikipedia")

        then:
        waitFor { at GoogleResultsPage }

        and:
        firstResultLink.text() == "Wikipedia"

        when:
        firstResultLink.click()

        then:
        waitFor { at WikipediaPage }
    }
}
