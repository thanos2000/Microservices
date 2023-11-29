package com.rabbitmq.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.rabbitmq.model.RabbitMQUser;

@Service
public class RabbitMQConsumer {

	private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.name}" })
	public void consume(String message) {
		logger.info("**** Message from the queue is :" + message + "****");
	}

	@RabbitListener(queues = { "${rabbitmq.queue.json.name}" })
	public void consume(RabbitMQUser user) {
		logger.info("****** Object from queue is: " + user.toString() + " *******");
	}

}
