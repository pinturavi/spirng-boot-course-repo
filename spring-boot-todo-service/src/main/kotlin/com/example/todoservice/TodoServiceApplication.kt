package com.example.todoservice

import com.example.todoservice.listener.TodoMessageListener
import com.example.todoservice.model.Todo
import com.example.todoservice.repo.TodoRepository
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
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.integration.annotation.IntegrationComponentScan

@IntegrationComponentScan
@EnableBinding(Sink::class)
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.*")
class TodoServiceApplication(val tr: TodoRepository) {

    companion object{
        val SFG_MESSAGE_QUEUE = "sfg-message-queue"
    }
    @Bean
    fun commandLineRunner() = CommandLineRunner {
        tr.saveAll(
                listOf(
                        Todo(description = "todo 0"),
                        Todo(description = "todo 1"),
                        Todo(description = "todo 2"),
                        Todo(description = "todo 3"),
                        Todo(description = "todo 5")
                )
        )
    }


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

    /*@Bean
    internal fun container(connectionFactory: ConnectionFactory,
                           listenerAdapter: MessageListenerAdapter): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(SFG_MESSAGE_QUEUE)
        container.setMessageListener(listenerAdapter)
        return container
    }

    @Bean
    internal fun listenerAdapter(receiver: TodoMessageListener): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }*/
}

fun main(args: Array<String>) {
    runApplication<TodoServiceApplication>(*args)
}


