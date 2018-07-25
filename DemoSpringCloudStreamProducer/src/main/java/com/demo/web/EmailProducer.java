package com.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
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
	public ResponseEntity<String> produceMessage(){
		
		EmailModel email = new EmailModel();
		email.setFromUserName("admin");
		email.setMessage("verify SPRING CLOUD STREAM");
		email.setSubject("Rabbit-MQ");
		
		emailChannel.emailOutputChannel().send(MessageBuilder.withPayload(email).build());
		
		
		return new ResponseEntity<String>("produced",HttpStatus.OK);
	}

	/**
	 * listener
	 */
	
	@StreamListener(value=EmailChannel.EMAIL_INPUTCHANNEL)
	public void consumeMessage(EmailModel model){
		
		System.out.println(model.getFromUserName());
		System.out.println(model.getMessage());
	}
}
