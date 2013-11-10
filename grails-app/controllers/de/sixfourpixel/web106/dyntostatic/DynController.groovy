package de.sixfourpixel.web106.dyntostatic

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.xml.MarkupBuilder
import groovy.test.GroovyAssert;
import groovy.util.XmlSlurper
import groovy.util.slurpersupport.NodeChild;

import de.sixfourpixel.web106.dyntostatic.HtmlUtil



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

		String startUrl = "http://localhost:8080/Web106/dyn/";

      
		Stack<String> toDoLinks = new Stack<String>();
		def crawledLinks = []
		toDoLinks.push(startUrl);
		
		while (!toDoLinks.isEmpty()) {
			String nextLink = toDoLinks.pop()
			crawledLinks.add(nextLink)
			
			List<String> links =  crawlLink(nextLink)
			links.each {
				if(!it.equals("crawl")) {
					String newLink =startUrl + it;
					if(!(newLink in crawledLinks)) {
						crawledLinks.add(newLink);
						toDoLinks.push(newLink);
					}
				}
			}
		}
	//	if(!startUrl in crawledLinks) {
			
			
	//	}
		def calendar = Calendar.instance
			
		render(text: calendar.time.toString()+ "<br>" + crawledLinks,contentType:"text/html",encoding:"UTF-8")
    }
	
	private static List<String> crawlLink(String link) {
		def rest = new RestBuilder()
		def htmlUtil = new HtmlUtil();
		def resp = rest.get(link)
		List<String> internalLinks = htmlUtil.parseInternalLinks(resp.text)
		return internalLinks;
	} 
	

	
}
