package web106.queue

import grails.test.mixin.TestFor
import spock.lang.Specification
import web106.queue.SQSMockService;
import web106.mock.Message

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Mock([Message])
@TestFor(SQSMockService)
class SQSMockServiceSpec extends Specification {

	
	
    def setup() {
		
    }

    def cleanup() {
    }

	/*
    void "create Message and add it to queryurl and read it"() {
		
		setup:
			//def sqs = mockFor(SQSMockService).createMock()
			
			SQSMockService sqs = new SQSMockService()
	
		when:
			sqs.sendMessage("q1", "Hello") 
		
		then:
			Message messageRecived = sqs.receiveMessage("q1")
			messageRecived.messagebody == "Hello" 
    }
    */
}
