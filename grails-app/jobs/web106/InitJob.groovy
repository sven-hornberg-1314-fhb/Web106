package web106

/**
 * Created by devnull on 10.01.14.
 */
class InitJob {

    def BootStrapWeb106Service bootStrapWeb106Service

    static triggers = {
        simple name:'initTrigger', startDelay:5000
    }

    def execute() {
       bootStrapWeb106Service.init()
    }
}
