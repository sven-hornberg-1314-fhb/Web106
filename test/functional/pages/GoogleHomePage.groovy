package pages

import geb.Page
import geb.Module
import modules.GoogleSearchModule

/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 18.12.13
 * Time: 21:16
 * To change this template use File | Settings | File Templates.
*/

class GoogleHomePage extends Page {

    // pages can define their location, either absolutely or relative to a base
    static url = "http://google.com/ncr"

    // “at checkers” allow verifying that the browser is at the expected page
    static at = { title == "Google" }

    static content = {
        // include the previously defined module
        search { module modules.GoogleSearchModule, buttonValue: "Google Search" }
    }
}



