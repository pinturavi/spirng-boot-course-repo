package com.example.alertservice

import com.example.alertservice.service.EmailService
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class AlertServiceApplication{
	companion object{
		val SFG_MESSAGE_QUEUE = "sfg-message-queue"
	}

	/*@Bean
	fun commandLinerunner(emailService:EmailService)=CommandLineRunner{
		emailService.sendEmail(mapOf("text" to "Hello pintu!!"))
	}*/
	@Bean
	internal fun queue(): Queue {
		return Queue(SFG_MESSAGE_QUEUE, false)
	}

	@Bean
	internal fun exchange(): TopicExchange {
		return TopicExchange("spring-boot-exchange")
	}

	@Bean
	internal fun binding(queue: Queue, exchange: TopicExchange): Binding {
		return BindingBuilder.bind(queue).to(exchange).with(SFG_MESSAGE_QUEUE)
	}

	@Bean
	internal fun container(connectionFactory: ConnectionFactory,
						   listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
		val container = SimpleMessageListenerContainer()
		container.connectionFactory = connectionFactory
		container.setQueueNames(SFG_MESSAGE_QUEUE)
		container.setMessageListener(listenerAdapter)
		return container
	}

	@Bean
	internal fun listenerAdapter(receiver: EmailService): MessageListenerAdapter {
		return MessageListenerAdapter(receiver, "sendEmail")
	}
}

fun main(args: Array<String>) {
	runApplication<AlertServiceApplication>(*args)
}
