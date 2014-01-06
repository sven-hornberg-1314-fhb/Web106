package web106

/**
 * Quartz Job
 * including simple, cron and custom trigger,
 * which run execute method in predefined time interval
 */
class MyJob {
    static triggers = {
        //simple name:'simpleTrigger', startDelay:10000, repeatInterval: 30000, repeatCount: 10
        cron name:'cronTrigger', startDelay:10000, cronExpression: '0 * *  * * ?' //wiederhole alle minute
        //custom name:'customTrigger', triggerClass:MyTriggerClass, myParam:myValue, myAnotherParam:myAnotherValue
    }

    def execute() {
        println "Job run!"
    }
}