package web106

/**
 * Trigger for bootStrapWeb106Service.init()
 */
class InitJob {

    def BootStrapWeb106Service bootStrapWeb106Service

    static triggers = {
        simple name:'initTrigger', startDelay:5000, repeatCount: 0
    }

    def execute() {
       bootStrapWeb106Service.init()
    }
}
