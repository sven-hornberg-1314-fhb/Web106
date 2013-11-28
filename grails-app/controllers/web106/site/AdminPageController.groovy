package web106.site

@Secured(['ROLE_ADMIN'])
class AdminPageController {

	static scaffold = Page
}		

