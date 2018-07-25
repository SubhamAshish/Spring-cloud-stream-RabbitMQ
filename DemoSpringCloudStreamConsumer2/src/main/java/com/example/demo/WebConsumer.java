package com.example.demo;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class WebConsumer {
	
	/**
	 * Listening to the same  exchange with routing key data
	 */
	
	@StreamListener(value=ConsumerChannel2.EMAIL_INPUTCHANNEL)
	public void listerToProducer(EmailModel model){
		
		System.out.println("=========FROM CONSUMER---2==========");
		System.out.println(model.getFromUserName());
		System.out.println(model.getMessage());
		
	}

}
