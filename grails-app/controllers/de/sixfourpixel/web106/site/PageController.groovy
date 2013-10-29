package de.sixfourpixel.web106.site

import de.sixfourpixel.web106.dyntostatic.CrawlerService
import grails.converters.JSON

import static org.springframework.http.HttpStatus.*

class PageController {

    static scaffold = true

    def sven() {
        def objTest = new Expando()
        objTest.name = "Sven"
        objTest.alter = "27"
        String textInhalt = objTest.properties as JSON
        render(text: textInhalt ,contentType:"text/json",encoding:"UTF-8")

    }
}
