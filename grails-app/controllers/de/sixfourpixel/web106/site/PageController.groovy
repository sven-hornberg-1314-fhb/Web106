package de.sixfourpixel.web106.site

import grails.converters.JSON

class PageController {

    static scaffold = true
    def helloService


    def sven() {
        def objTest = new Expando()
        objTest.name = "Sven"
        objTest.alter = "27"
        String textInhalt = objTest.properties as JSON
        render(text: textInhalt ,contentType:"text/json",encoding:"UTF-8")

    }


    def test() {
        helloService.callHello()
        render(text:"done calling Hello in Service")
    }

}
