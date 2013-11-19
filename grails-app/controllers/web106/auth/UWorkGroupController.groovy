package web106.auth

import grails.converters.JSON
import web106.UserUtils
import web106.auth.WorkGroup

class UWorkGroupController {

    static defaultAction = "listWorkGroups"
    static allowedMethods = [selectWorkGroup:'POST']
    def index() { }

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

       render view: "SuccessWorkgroupSelection", model:[name: name]

    }
}
