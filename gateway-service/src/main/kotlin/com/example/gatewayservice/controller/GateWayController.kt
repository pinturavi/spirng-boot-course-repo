package com.example.gatewayservice.controller

import com.example.gatewayservice.model.Todo
import com.example.gatewayservice.proxy.TodosProxy
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class GateWayController(val todosProxy: TodosProxy) {

    fun getDummyTodos():List<String> = listOf()

    @HystrixCommand(fallbackMethod = "getDummyTodos")
    @GetMapping("/api/geteway/todos")
    fun getAllTodos():List<String>{
        return todosProxy.retrieveAllTodos().map { it.description }
    }
}

