package com.demo;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface EmailChannel {

	String EMAIL_INPUTCHANNEL = "emailchannel-in";

	String EMAIL_OUTPUTCHANNEL = "emailchannel-out";

	@Input(EmailChannel.EMAIL_INPUTCHANNEL)
	SubscribableChannel emailInputChannel();

	@Output(EmailChannel.EMAIL_OUTPUTCHANNEL)
	MessageChannel emailOutputChannel();

}
