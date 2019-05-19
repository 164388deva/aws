package com.sqs.reciever.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sqs")
public class SQSController {
private static final Logger LOG =LoggerFactory.getLogger(SQSController.class);

@Autowired
private QueueMessagingTemplate queueMessagingTemplate;

@Value("${cloud.aws.end-point.url}")
private String sqsEndpoint;

@GetMapping
public void sendMessage() {
	queueMessagingTemplate.send(sqsEndpoint,MessageBuilder.withPayload("Hello from  dev").build());
	
}
@SqsListener("demo")
public void getMessages(String message) {
	LOG.info("messagw from sqs" +message);
}
}
