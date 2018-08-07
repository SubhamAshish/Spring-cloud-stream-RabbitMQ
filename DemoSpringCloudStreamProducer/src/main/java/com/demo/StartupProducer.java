package com.demo;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableBinding(EmailChannel.class)
@EnableTransactionManagement
public class StartupProducer extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StartupProducer.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartupProducer.class);
	}

	@Bean
	public RabbitTransactionManager transactionManager(ConnectionFactory cf) {
		return new RabbitTransactionManager(cf);
	}

}
