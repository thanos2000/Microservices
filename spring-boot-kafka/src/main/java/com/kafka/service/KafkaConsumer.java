package com.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.model.KafkaUser;

@Service
public class KafkaConsumer {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "spring.kafka.topic.name", groupId = "spring.kafka.consumer.group-id")
	public void consume(String message) {
		logger.info("Message received : " + message);
	}

	@KafkaListener(topics = "spring.kafka.topic-json.name", groupId = "spring.kafka.consumer.group-id")
	public void consumeJson(KafkaUser user) {
		logger.info("Object received: * " + user.toString() + " *");
	}

}
