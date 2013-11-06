package de.sixfourpixel.web106.dyntostatic

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.xml.MarkupBuilder

class DynController {

    static defaultAction = "pageHome"

    def crawlService

    def pageHome() {
        def writer = new StringWriter()
        def builder = new MarkupBuilder(writer)
        builder.html {
            head {
                title 'Home'
            }
            body {
                h1 'Hello'
                a (id:"linktoAbout", href:"pageAbout", "About")
                a (id:"linktoCrawl", href:"crawl", "crawl")


                br ''
                div 'Willkommen auf der Startseite dieser Homepage'
            }
        }
        def html = '<!DOCTYPE HTML>'+ '\n' + writer.toString()
        render html
    }

    def pageAbout() {
        def writer = new StringWriter()
        def builder = new MarkupBuilder(writer)
        builder.html {
            head {
                title 'About'
            }
            body {
                h1 'About'
                a (id:"linktoHome", href:"pageHome", "Home")
                br ''
                div 'Danke für das Interesse an uns'
            }
        }
        def html = '<!DOCTYPE HTML>' + '\n' + writer.toString()
        render html

    }

    def crawl() {

        //def contents =  render(controller: defaultAction)
        //println(contents)

        //crawlService.crawlPage(defaultAction.toString())
        render(text:"trigger crawling",contentType:"text/html",encoding:"UTF-8")

        println(request.requestURL)

        def rest = new RestBuilder()


        def resp = rest.get("http://grails.org/api/v1.0/plugin/acegi/")

        println(resp.text)
    }
}
