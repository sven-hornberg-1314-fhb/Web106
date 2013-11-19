package web106.auth

import grails.converters.JSON
import web106.UserUtils
import web106.auth.WorkGroup

class UWorkGroupController {

    def index() {
    }
    def listWorkGroups() {

        def mail  = UserUtils.newInstance().emailFromCurrentUser
        User currentUser = User.find {email== mail}

        def currentWorkgroups = WorkGroup.findAll {user.id == currentUser.id}
               render currentWorkgroups as JSON
        def model = [
                workgroup : Workgroup


        ]


    }


}
