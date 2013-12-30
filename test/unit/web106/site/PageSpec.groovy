package web106.site

import grails.test.mixin.TestFor;
import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Shared;
import spock.lang.Specification
import grails.test.mixin.Mock
import web106.site.*
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Mock([Page, Box,Template, Website])
@TestFor(Page)
class PageSpec extends Specification {
	
	@Shared Template template
    @Shared Website website

	def setup() {
		template = new Template(name:'Berlin')

        website = new Website(title : 'simpleWebsite')
        website.page = []
    }

    def cleanup() {
    }

    def "page set title"() {

		setup:
			def newPage = new Page() 
			def template = Template.find {
				name ==  'Berlin'
			}
			newPage.template = template	
		
		when: 
		
			newPage.title = "Welcome to my new Page!"
		
		then:
		
			newPage.title == "Welcome to my new Page!"
    }
	
	def "save new Page"() {
	
		setup:

			def visibleFromDate = new Date()
			def visibleToDate = new Date()
			
			def newPage = new Page(title: "saveMe", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
			newPage.template = template
			
		when:

		    website.page.add(newPage)
            newPage.website = website


			newPage.save(flush: true, failOnError: true)
		
		then:
			
			newPage.id > 0
	}
	
	def "find page by title"() {
		
		setup:
		
			def visibleFromDate = new Date()
			def visibleToDate = new Date()
			
			def newPage = new Page(title: "saveMe", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
			newPage.template = template

            website.page.add(newPage)
            newPage.website = website


            newPage.save(flush: true, failOnError: true)

		when:

			def searchPage = Page.find {
				title == "saveMe"
			}
		
		then:
		
			searchPage.id > 0
		
	}
	
	
	def "create and update page"()  {
		
		setup:
			
			def visibleFromDate = new Date()
			def visibleToDate = new Date()
			
			def newPage = new Page(title: "saveMe", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
			newPage.template = template

            website.page.add(newPage)
            newPage.website = website

        newPage.save(flush: true, failOnError: true)
			
		when:

			//update title
			newPage.title = "saveMeNew"
			newPage.save(flush: true, failOnError: true)
			
		then:
		
			def searchPage = Page.find {
				title == "saveMeNew"
			}
			searchPage.id > 0
	}
	
	
	def "create and delete"() {
		
		setup:
		
			def visibleFromDate = new Date()
			def visibleToDate = new Date()
			
			def newPage = new Page(title: "saveMe", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
			newPage.template = template

            website.page.add(newPage)
            newPage.website = website


        newPage.save(flush: true, failOnError: true)
			
		when:
			
			newPage.delete()
		
		then:
				
			def searchPage = Page.find {
				title == "saveMe"
			}
			searchPage == null
	}
	
	
	def "create and add boxes"() {
		
		setup:
		
			def box1 = new Box(idName: "box1")
			
			def box2 = new Box(idName: "box2")
			
			def visibleFromDate = new Date()
			def visibleToDate = new Date()
			
			def newPage = new Page(title: "saveMe", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
			def boxesSet = []
			boxesSet.add(box1)
			boxesSet.add(box2)
			newPage.template = template
			
		when:

            website.page.add(newPage)
            newPage.website = website

            newPage.boxes = boxesSet as Set
			newPage.save(flush: true, failOnError: true)
	
		then:
		
			def searchPage = Page.find {
				title == "saveMe"
			}
			searchPage.boxes.size() == 2
			
	}
	
	def "create without boxes"() {
		
		setup:
		
		
			def visibleFromDate = new Date()
			def visibleToDate = new Date()
			
			def newPage = new Page(title: "saveMe", visibleTo: visibleToDate, visibleFrom : visibleFromDate)
			newPage.template = template
			
		
		when:

            website.page.add(newPage)
            newPage.website = website


            newPage.save(flush: true, failOnError: true)
	
		then:
		
			def searchPage = Page.find {
				title == "saveMe"
			}
			searchPage.id != null 
			
			
	}
	
}
