package web106.site

import grails.transaction.Transactional

@Transactional
class PageService {
	
	Page create(final Page page) {
	
		return page.save();	
	}
	
	Page saveOrUpdate(final Page page) {
		
	}
	
	boolean delete(final Page) {
		
	}
	
	boolean deleteById(final long id) {
		
	}
}
