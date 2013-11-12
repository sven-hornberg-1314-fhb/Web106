package de.sixfourpixel.web106.dyntostatic

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import groovy.xml.MarkupBuilder
import groovy.test.GroovyAssert;
import groovy.util.XmlSlurper
import groovy.util.slurpersupport.NodeChild;

class HtmlUtil {

	
	def parseInternalLinks(String htmlcode) {
		def rootNode = new XmlSlurper().parseText(htmlcode)
				
		List<NodeChild> linkNodes = []
		List<String> links = []
		
		//find all link tags
		rootNode.'**'.findAll { it.name() == 'a' }.each { linkNodes.add(it) }
		
		//get href of each link
		linkNodes.each {
			links.add(it.@href.text())
		}
		
		//filter external links
		return links.findAll { !it.startsWith("http") }			
	}	
}
