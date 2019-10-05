package com.example.gatewayservice.controller

import com.example.gatewayservice.config.RedisUtil
import com.example.gatewayservice.model.Todo
import com.example.gatewayservice.proxy.TodosProxy
import com.example.gatewayservice.service.TodoCacheManager
import com.fasterxml.jackson.databind.ObjectMapper
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/gws/todos")
class TodosController(val todosProxy: TodosProxy,
                      val todoCacheManager: TodoCacheManager,
                      val redisUtil: RedisUtil<String>,
                      val mapper:ObjectMapper) {

    fun getDummyTodos(): List<Todo> = listOf()

    @HystrixCommand(fallbackMethod = "getDummyTodos")
    @GetMapping
    fun getAllTodos(): List<Todo> {
        val cachedTodos = redisUtil.getMapAsAll("TODO_TABLE")
        if (!cachedTodos.isEmpty()) {
            return cachedTodos.values.map { jsonValue -> mapper.readValue(jsonValue, Todo::class.java) }.toList()
        }
        val todos = todosProxy.retrieveAllTodos()
        todos.forEach { todoCacheManager.cacheTodos(it) }

        return todos
    }

    @PostMapping
    fun writeTodo(@RequestBody todo: Todo): Todo = todosProxy.addTodo(todo)

    @PutMapping
    fun updateTodo(@RequestBody todo: Todo): Todo = todosProxy.updateTodo(todo)

    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable("id") id: Long) = todosProxy.deleteTodo(id)


    @GetMapping("/{id}")
    fun getTodoById(@PathVariable("id") id: Long): Todo = todosProxy.retrieveTodoById(id)

}

