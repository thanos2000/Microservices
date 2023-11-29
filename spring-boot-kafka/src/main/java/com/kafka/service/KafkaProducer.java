package com.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.kafka.model.KafkaUser;

@Service
public class KafkaProducer {

	@Value("${spring.kafka.topic.name}")
	private String topicName;

	@Value("${spring.kafka.topic-json.name}")
	private String jsonTopic;

	private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	private KafkaTemplate<String, String> kafkaTemplate;

	public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(String message) {
		logger.info("Message sent is " + message);
		kafkaTemplate.send(topicName, message);
	}

	public void sendObject(KafkaUser user) {
		logger.info("Message sent is: * " + user.toString());
		Message<KafkaUser> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, jsonTopic).build();
		logger.info("Message sent is: * " + message + " * ");
		kafkaTemplate.send(message);
	}

}
