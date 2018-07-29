package com.demo;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReRouteDlqApplication {

	private static final String ORIGINAL_QUEUE = "emailexchange.emailproducerqueue";

	private static final String DLQ = ORIGINAL_QUEUE + ".dlq";

	private static final String PARKING_LOT = ORIGINAL_QUEUE + ".parkingLot";

	private static final String X_RETRIES_HEADER = "x-retries";

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * Route original messages back to the original queue but moves them to a
	 *  third “parking lot” queue after three attempts
	 *  as max-attempts = 3 every time it tries 3 times than retries inc to 1
	 */
	@RabbitListener(queues = DLQ)
	public void rePublish(Message failedMessage) {
		Integer retriesHeader = (Integer) failedMessage.getMessageProperties().getHeaders().get(X_RETRIES_HEADER);
		if (retriesHeader == null) {
			retriesHeader = Integer.valueOf(0);
		}
		if (retriesHeader < 3) {
			failedMessage.getMessageProperties().getHeaders().put(X_RETRIES_HEADER, retriesHeader + 1);
			this.rabbitTemplate.send(ORIGINAL_QUEUE, failedMessage);
		} else {
			this.rabbitTemplate.send(PARKING_LOT, failedMessage);
		}
	}

	@Bean
	public Queue parkingLot() {
		return new Queue(PARKING_LOT);
	}
}
