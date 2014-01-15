package web106.site

import grails.transaction.Transactional

@Transactional
class BoxService {

    /**
     * creates a new empty Box
     * @return new Box
     */
	Box newEmptyBox() {
		return new Box()
	}
}
