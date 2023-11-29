package com.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.RabbitMQUser;

@Service
public class RabbitMQProducer {

	@Value("${rabbitmq.exchange.name}")
	private String exchangeName;

	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	@Value("${rabbitmq.routing.json.key}")
	private String routingJsonKey;;

	private RabbitTemplate rabbitTemplate;

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQProducer.class);

	public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
		super();
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(String message) {
		logger.info("Message received in queue: " + message);
		rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
	}

	public void sendJsonMessage(RabbitMQUser user) {
		logger.info("Object received in queue: " + user.toString());
		rabbitTemplate.convertAndSend(exchangeName, routingJsonKey, user);
	}

}
