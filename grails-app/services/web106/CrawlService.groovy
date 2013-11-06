package web106

import grails.transaction.Transactional

import java.sql.DriverManager

@Transactional
class CrawlService {

    def crawlPage(defaultAction) {
        println('crawlPage()')
        println(defaultAction)
    }
}
