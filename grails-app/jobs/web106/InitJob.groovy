package web106

import groovy.util.logging.Log

/**
 * Trigger for bootStrapWeb106Service.init()
 * Is not running well on t1.micro -> quarzt is removed
 */
@Log
class InitJob {

    def BootStrapWeb106Service bootStrapWeb106Service

    static triggers = {
        simple name:'initTrigger', startDelay:5000, repeatCount: 0
    }

    def execute() {
        try {
           bootStrapWeb106Service.init()

        } catch (AmazonServiceException) {
            log.severe('Error by bootStrapWeb106Service.init(): AmazonServiceException')
        } catch (AmazonClientException) {
            log.severe( 'Error by bootStrapWeb106Service.init(): AmazonClientException')
        } catch (UnexpectedRollbackException) {
            log.severe( 'Error by bootStrapWeb106Service.init(): UnexpectedRollbackException')
        }
    }
}
