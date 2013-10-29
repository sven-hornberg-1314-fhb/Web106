package de.sixfourpixel.web106

import de.sixfourpixel.web106.site.Page
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional
class HelloService {

    def callHello() {
        println("Hallo")

        def myPage = Page.newInstance()
        myPage.title = "PageService"


        myPage.save(flush: true)


        println(Page.all as JSON)

        /*TOOD: get save working */
    }
}
