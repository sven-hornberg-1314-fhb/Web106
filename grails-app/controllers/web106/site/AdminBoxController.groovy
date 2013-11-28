package web106.site

@Secured(['ROLE_ADMIN'])
class AdminBoxController {

    static scaffold = Box

}
