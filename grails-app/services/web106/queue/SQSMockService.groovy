package web106.queue

import web106.mock.Message

import grails.transaction.Transactional

@Transactional
/**
 * Evaluation of SQS Mock with a much smaller and simple , less powerful api
 */
class SQSMockService {


	def sqs = [:]
	
	//MESSAGE
	//http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/services/sqs/model/Message.html
	
	/*
	// Send a message
	System.out.println("Sending a message to MyQueue.\n");
	sqs.sendMessage(new SendMessageRequest()
		.withQueueUrl(myQueueUrl)
		.withMessageBody("This is my message text."));
	*/
	def sendMessage(String queueUrl, String messageBody) {
		
		Message newMessage = new Message()
		newMessage.messagebody = messageBody
		
		if(sqs.containsKey(queueUrl)) {
	
			Queue<Message> urlqueue = new LinkedList<Message>()
			urlqueue.offer(newMessage)
			sqs[queueUrl]  = urlqueue
				
		} else {
		
			Queue<Message> urlqueue = sqs[queueUrl]
			urlqueue.offer(newMessage)
		}
	}
	
	/*
	 * System.out.println("Receiving messages from MyQueue.\n");
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
		for (Message message : messages) {
		    System.out.println("  Message");
		    System.out.println("    MessageId:     " + message.getMessageId());
		    System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
		    System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
		    System.out.println("    Body:          " + message.getBody());
		    for (Entry<String, String> entry : message.getAttributes().entrySet()) {
		        System.out.println("  Attribute");
		        System.out.println("    Name:  " + entry.getKey());
		        System.out.println("    Value: " + entry.getValue());
		    }
		}
		System.out.println();
	 * 
	 */
	def receiveMessage(String queueUrl) {
		
		Message returnMessage
		
		if(sqs.containsKey(queueUrl)) {
			
			returnMessage = null
				
		} else {
		
			Queue<Message> urlqueue = sqs[queueUrl]
			returnMessage = urlqueue.peek()
		}
	}
	
	/*
	 * // Delete a message
	 * 		System.out.println("Deleting a message.\n");
	String messageReceiptHandle = messages.get(0).getReceiptHandle();
	sqs.deleteMessage(new DeleteMessageRequest()
    .withQueueUrl(myQueueUrl)
    .withReceiptHandle(messageReceiptHandle));
	 */

	
	def deleteMessage(String queueUrl) {

		if(!sqs.containsKey(queueUrl)) {
	
			Queue<Message> urlqueue = sqs[queueUrl]
			returnMessage = urlqueue.remove()
		}
	}
	
}
