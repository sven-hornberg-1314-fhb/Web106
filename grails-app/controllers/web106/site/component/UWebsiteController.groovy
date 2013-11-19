package web106.site.component

import grails.converters.JSON
import org.springframework.security.core.context.SecurityContextHolder
import web106.auth.User
import web106.template.TemplateController
import web106.UserUtils


class UWebsiteController {


    def index() {
    }

    def create(){

        String email = UserUtils.newInstance().emailFromCurrentUser

        User user = User.findByEmail(email)



        //check if user has chosen workgroup
        if(!session.attribute('activeWorkGroup')){
            //if yes redirect to existing one

            //if not create one
        }

        TemplateController  tmp = new TemplateController()
        def templateList = tmp.list();

        def model = [
                list:templateList,
                title:'neue Website',
                workgroup:workgroup,


        ]
    }


}
