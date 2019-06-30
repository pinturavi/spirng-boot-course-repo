package com.example.gatewayservice.controller

import com.example.gatewayservice.GatewayServiceApplication
import com.example.gatewayservice.model.Todo
import com.example.gatewayservice.proxy.TodosProxy
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.cloud.stream.messaging.Source
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.apache.tomcat.jni.Socket.send
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.messaging.support.MessageBuilder


@RestController
class GateWayController(val todosProxy: TodosProxy, val source: Source, val rabbitTemplate: RabbitTemplate) {

    fun getDummyTodos(): List<String> = listOf()

    @HystrixCommand(fallbackMethod = "getDummyTodos")
    @GetMapping("/api/gateway/todos")
    fun getAllTodos(): List<String> {
        return todosProxy.retrieveAllTodos().map { it.description }
    }

    @PostMapping("/api/gateway/todos")
    fun writeTodo(@RequestBody todo: Todo) {
        val msgMap = mapOf("description" to todo.description)
        rabbitTemplate.convertAndSend(GatewayServiceApplication.SFG_MESSAGE_QUEUE, msgMap)
    }
}

