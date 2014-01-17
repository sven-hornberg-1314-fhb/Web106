package web106.auth

import web106.UserUtils

/**
 * Controller for managing Workgroups which
 * is not secured for admin access only, so ROLE_USER have access
 * to CREATE - LIST - SELECT workgroups
 */
class WorkGroupController {

    static defaultAction = 'listWorkGroups'
    static allowedMethods = [selectWorkGroup:'POST']

	def create() {

		def mail  = UserUtils.newInstance().emailFromCurrentUser
		User currentUser = User.find {email== mail}

		WorkGroup newWorkGroup = new WorkGroup()
		newWorkGroup.name = params.name

        if(!newWorkGroup.validate()){
            flash.message = 'Name nicht erlaubt'
            render view: 'SelectWorkgroup'

            def currentWorkgroups = WorkGroup.findAll {user.id == currentUser.id}

            def model = [
                    workgroup : currentWorkgroups
            ]

            render view:"SelectWorkgroup", model: model
        }else{
            def usersList = []
            usersList.add(newWorkGroup.user)
            usersList.add(currentUser)

            newWorkGroup.user = usersList as Set

            newWorkGroup.save(FailonError: true)

            params.workId = newWorkGroup.id as String
            selectWorkGroup()
        }

	}
	
    def listWorkGroups() {

        def mail  = UserUtils.newInstance().emailFromCurrentUser
        User currentUser = User.find {email== mail}

        def currentWorkgroups = WorkGroup.findAll {user.id == currentUser.id}

        def model = [
                workgroup : currentWorkgroups
        ]

        render view:"SelectWorkgroup", model: model
    }

    def selectWorkGroup(){

        long workId = Long.parseLong(params.workId)
        session.setAttribute("activeWorkGroup",workId)

        def selectedWorkgroup = WorkGroup.find{id == workId}
        def name = selectedWorkgroup.name
        session.setAttribute("activeWorkGroupName",selectedWorkgroup.name)

        //reset activeWebsite
        session.removeAttribute('activeWebsite')

        render view: "SuccessWorkgroupSelection", model:[name: name]
    }

}
