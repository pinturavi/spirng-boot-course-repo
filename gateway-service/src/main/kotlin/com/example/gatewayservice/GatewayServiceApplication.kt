package com.example.gatewayservice

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Source
import org.springframework.context.annotation.Bean

@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@EnableFeignClients
@EnableBinding(Source::class)
@SpringBootApplication
class GatewayServiceApplication{
    companion object{
        val SFG_MESSAGE_QUEUE = "sfg-message-queue";
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



}

fun main(args: Array<String>) {
    runApplication<GatewayServiceApplication>(*args)
}
