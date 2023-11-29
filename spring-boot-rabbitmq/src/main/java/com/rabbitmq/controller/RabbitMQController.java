package com.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.model.RabbitMQUser;
import com.rabbitmq.producer.RabbitMQProducer;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

	@Autowired
	private RabbitMQProducer producer;

	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message) {
		producer.sendMessage(message);
		return ResponseEntity.ok("Message has been sent to the Queue.......");
	}

	@PostMapping("/publishJson")
	public ResponseEntity<String> publishJson(@RequestBody RabbitMQUser user) {
		producer.sendJsonMessage(user);
		return ResponseEntity.ok("Object has been sent to the Queue.......");
	}

}
