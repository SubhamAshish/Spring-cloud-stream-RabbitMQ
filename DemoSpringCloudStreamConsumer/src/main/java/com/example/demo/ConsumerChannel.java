package com.example.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannel {

	String EMAIL_INPUTCHANNEL = "emailchannel-in";

	@Input(ConsumerChannel.EMAIL_INPUTCHANNEL)
	SubscribableChannel emailInputChannel();
}
