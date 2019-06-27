package com.example.gatewayservice.proxy

import com.example.gatewayservice.model.Todo
import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RibbonClient("todo-service")
@FeignClient("todo-service")
interface TodosProxy{
    @GetMapping("/api/todos")
    fun retrieveAllTodos():List<Todo>

    @GetMapping("/{id}")
    fun retrieveTodoById(@PathVariable("id") id: Long): Todo

    @PostMapping
    fun addTodo(@RequestBody @Valid todo: Todo): Todo

    @PutMapping
    fun updateTodo(@RequestBody @Valid todo: Todo): Todo

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable("id") id:Long)

}