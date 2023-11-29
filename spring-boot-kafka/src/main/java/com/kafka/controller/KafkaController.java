package com.kafka.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.model.KafkaUser;
import com.kafka.service.KafkaProducer;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

	private KafkaProducer kafkaProducer;

	public KafkaController(KafkaProducer kafkaProducer) {
		super();
		this.kafkaProducer = kafkaProducer;
	}

	@GetMapping("/publish")
	public ResponseEntity<String> publish(@RequestParam("message") String message) {
		kafkaProducer.sendMessage(message);
		return ResponseEntity.ok("Message Sent to the topic");
	}

	@PostMapping("/publishObject")
	public ResponseEntity<String> publishObject(@RequestBody KafkaUser user) {
		kafkaProducer.sendObject(user);
		return ResponseEntity.ok("Object has been sent to the topic");
	}

}
