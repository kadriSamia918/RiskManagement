package com.telnet.project.webSocket;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
	
	@Autowired AmqpTemplate rabbitTemplate;
	
	@Scheduled
	public void send(@Payload String message, @Payload String transactionId)
	{
		String CustomMessage= "{\"transactionId\":\""+transactionId+"\",\"message\":\""+message+"\"}";
		rabbitTemplate.convertAndSend("long_running_process_progress_status",CustomMessage);
	}

}
