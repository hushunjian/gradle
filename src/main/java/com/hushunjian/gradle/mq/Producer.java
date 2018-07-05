package com.hushunjian.gradle.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消息生成者 
 */
public class Producer {
	
	public final static String QUEUE_NAME="rabbitMQ.test";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false,false,null);
		String message = "Hello RabbitMQ";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8")); 
		System.out.println("Producer send +'"+ message +"'");
		channel.close();
		connection.close();
	}
}
