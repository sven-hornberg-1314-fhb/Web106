package de.sixfourpixel.web106



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PageController {

	static scaffold = true

}
