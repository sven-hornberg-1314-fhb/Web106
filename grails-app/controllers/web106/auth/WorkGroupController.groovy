package web106.auth

import org.apache.jasper.compiler.Node.ParamsAction;

import grails.converters.JSON
import web106.UserUtils
import web106.auth.WorkGroup

/**
 * Controller for managing Workgroups which
 * is not secured, so ROLE_USER have access
 * to CREATE - LIST - SELECT workgroups
 */
class WorkGroupController {

    static defaultAction = "listWorkGroups"
    static allowedMethods = [selectWorkGroup:'POST']
	
    def index() { }

	def create() {

		def mail  = UserUtils.newInstance().emailFromCurrentUser
		User currentUser = User.find {email== mail}
		
		WorkGroup newWorkGroup = new WorkGroup() 
		newWorkGroup.name = params.name
		def usersList = [] 
		usersList.add(newWorkGroup.user)
		usersList.add(currentUser)
		
		newWorkGroup.user = usersList as Set
		
		newWorkGroup.save(FailonError: true)

		redirect( action: "listWorkGroups")
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

    def selectWorkGroup() {

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
