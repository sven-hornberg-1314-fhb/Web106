package web106.auth


@Secured(['ROLE_ADMIN'])
class AdminWorkGroupController {

		static scaffold = WorkGroup

}
