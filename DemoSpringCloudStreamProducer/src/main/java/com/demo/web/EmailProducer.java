package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.EmailChannel;
import com.demo.model.EmailModel;

@RestController
@RequestMapping("/api")
public class EmailProducer {
	
	/**
	 * this will produce and consume itself, that is producer and consumer in the same application
	 */
	
	@Autowired
	private EmailChannel emailChannel;
	
	@RequestMapping(value="/check")
	@Transactional
	public ResponseEntity<String> produceMessage(){
		
		EmailModel email = new EmailModel();
		email.setFromUserName("admin");
		email.setMessage("verify SPRING CLOUD STREAM");
		email.setSubject("Rabbit-MQ");
		
		emailChannel.emailOutputChannel().send(MessageBuilder.withPayload(email).build());
		
		throw new RuntimeException();
//		return new ResponseEntity<String>("produced",HttpStatus.OK);
	}

	/**
	 * listener
	 */
	
	@StreamListener(value=EmailChannel.EMAIL_INPUTCHANNEL)
	@Transactional
	public void consumeMessage(EmailModel model){
		
		
		System.out.println("Consumer called:::::");
		System.out.println(model.getFromUserName());
		System.out.println(model.getMessage());
		throw new RuntimeException("BOOM!");
	}
	
	/**
	 * error handler
	 * 
	 * For each input binding, Spring Cloud Stream creates a dedicated error channel with the 
	 * following semantics <channel-name>.<group-name>.errors
	 */
	@Transformer(inputChannel =EmailChannel.EMAIL_INPUTCHANNEL+ ".emailproducerqueue.errors")
	public void error(Message<?> message){
		
		System.out.println("------------------error handler called--------------------!!");
		System.out.println("handling error: " + message);
	
		
	}
}
