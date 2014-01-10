package web106

/**
 * Created by devnull on 10.01.14.
 */
class InitJob {

    def BootStrapWeb106Service bootStrapWeb106Service

    static triggers = {
        simple name:'initTrigger', startDelay:10000
        //cron name:'cronTrigger', startDelay:10000, cronExpression: '0 0/30 * * * ?' //wiederhole alle minute
        //custom name:'customTrigger', triggerClass:MyTriggerClass, myParam:myValue, myAnotherParam:myAnotherValue
    }

    def execute() {
       bootStrapWeb106Service.init()
    }
}
