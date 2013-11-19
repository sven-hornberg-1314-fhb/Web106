package web106.site

import web106.auth.WorkGroup

class Website {

    String title

    static hasMany = [page : Page]
    static belongsTo = [workGroup: WorkGroup]


    static constraints = {
        title blank:false
    }
}
