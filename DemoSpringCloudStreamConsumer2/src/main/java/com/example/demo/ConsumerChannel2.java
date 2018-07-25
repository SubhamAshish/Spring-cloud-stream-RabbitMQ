package com.example.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel2 {

	String EMAIL_INPUTCHANNEL = "emailchannel-in";

	@Input(ConsumerChannel2.EMAIL_INPUTCHANNEL)
	SubscribableChannel emailInputChannel();
}
