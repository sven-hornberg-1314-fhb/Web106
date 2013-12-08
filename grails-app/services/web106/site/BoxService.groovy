package web106.site

import grails.transaction.Transactional

@Transactional
class BoxService {

    def serviceMethod() {

    }
	
	Box newEmptyBox() {
		return new Box()
	}
}
