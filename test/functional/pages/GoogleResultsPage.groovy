package pages

import geb.Page

/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 18.12.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */

class GoogleResultsPage extends Page {
    static at = { title.endsWith "Google Search" }
    static content = {
        // reuse our previously defined module
        search { module modules.GoogleSearchModule, buttonValue: "Search" }

        // content definitions can compose and build from other definitions
        results { $("li.g") }
        result { i -> results[i] }
        resultLink { i -> result(i).find("a.l") }
        firstResultLink { resultLink(0) }
    }
}