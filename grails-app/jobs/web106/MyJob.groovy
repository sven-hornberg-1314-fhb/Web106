package web106

/**
 * Created with IntelliJ IDEA.
 * User: marcman
 * Date: 20.11.13
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
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