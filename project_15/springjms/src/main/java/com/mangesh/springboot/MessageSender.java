package com.mangesh.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	@Value("${springjms.myQueue}")
	private String queue;
	
	/*
	 * public void send(String message) {
	 * System.out.println("Message sent===> "+message);
	 * jmsTemplate.convertAndSend(queue,message); }
	 */
	
	public void send(String message)
	{
		MessageCreator mc=s->s.createTextMessage("Hello Spring JMS!!");
		System.out.println("Message sent===> "+message);
		jmsTemplate.send(queue,mc);
	}


}
