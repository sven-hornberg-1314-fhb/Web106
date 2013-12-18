package modules

import geb.Module
import pages.GoogleResultsPage

/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 18.12.13
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */

class GoogleSearchModule extends Module {

    // a paramaterised value set when the module is included
    def buttonValue

    // the content DSL
    static content = {

        // name the search input control “field”, defining it with the jQuery like navigator
        field { $("input", name: "q") }

        // the search button declares that it takes us to the results page, and uses the
        // parameterised buttonValue to define itself
        button(to: GoogleResultsPage) {
            $("input", value: buttonValue)
        }
    }
}
