package com.telnet.project.webSocket;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telnet.project.ServiceImpl.MailServiceImpl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin( "*")
public class MainController {
	@Autowired
	private GlobalProperties globalProperties;
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	
	private final SimpMessagingTemplate template;
	
	@Autowired
	private RabbitMQSender rabbitMQSender;
	  @Autowired
	   MainController(SimpMessagingTemplate template){
	        this.template = template;
	    }
	private Logger logger = LoggerFactory.getLogger(MainController.class);

	@RequestMapping("/notifications")
	public String notifications() {
		return "notifications";
	}

	@RequestMapping("/")
	public String home() {
		return "notifications";
	}
	@PostMapping(value = "/api/pfe/producer/{transactionId}")
	public String producer (@RequestBody String message,@PathVariable("transactionId")  String transactionId)throws IOException{
		System.out.println(message + "hi message");
		 rabbitMQSender.send(message, transactionId);
		
		 System.out.println("yes");
		return "Message sent to RabbitMQ";
	}
	@PostMapping(value = "/api/pfe/notifUser/{username}")
	public String notifUser (@RequestBody String message,@PathVariable("username")  String username)throws IOException{
		System.out.println(message + "hi message");
		 rabbitMQSender.send(message, username);
		 System.out.println("yes");
		return "Message sent to RabbitMQ";
	}

	@MessageMapping("/register")
	public void processMessageFromClient(@Payload Message message, SimpMessageHeaderAccessor headerAccessor)
			throws Exception {
		 System.out.println(message +"hi Socket");
		// logger.info("sessionID" + message);
		logger.info("sessionID" + message.getSessionId());
		
		globalProperties.addTransactionAndSession(message.getSessionId(), message.getTransactionId());
	}

	@MessageMapping("/unregister")
	public void unregister(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		// logger.info("sessionID" + message);
		System.out.println(message +"Bye Socket");
		logger.info("sessionID" + message.getSessionId());
		globalProperties.removeTransactionAndSession(message.getSessionId());
	}
	   @MessageMapping("/send/message")
	    public void sendMessage(String message){
	        System.out.println(message +"hi Socket");
	        this.template.convertAndSend("/message",  message);
	    }
}

