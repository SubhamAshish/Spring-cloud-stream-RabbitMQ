package com.example.demo;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class WebListener {
	
	/**
	 * Listening to the same  exchange with routing key email
	 */
	@StreamListener(value=ConsumerChannel.EMAIL_INPUTCHANNEL)
	public void listenMessage(EmailModel model){
		
		System.out.println("=========FROM CONSUMER==========");
		System.out.println(model.getFromUserName());
		System.out.println(model.getMessage());
	}

}
