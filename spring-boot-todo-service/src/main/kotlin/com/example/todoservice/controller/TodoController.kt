package com.example.todoservice.controller

import com.example.todoservice.TodoServiceApplication
import com.example.todoservice.exception.TodoNotFoundException
import com.example.todoservice.model.Todo
import com.example.todoservice.repo.TodoRepository
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todos")
class TodoController(val tr: TodoRepository, val rabbitTemplate:RabbitTemplate) {

    @GetMapping
    fun retrieveAllTodos():List<Todo> = tr.findAll()

    @GetMapping("/{id}")
    fun retrieveTodoById(@PathVariable("id") id: Long):Todo = tr.findById(id).map { it }.orElseThrow { TodoNotFoundException("") }

    @PostMapping
    fun addTodo(@RequestBody @Valid todo: Todo):Todo = tr.save(todo)

    @PutMapping
    fun updateTodo(@RequestBody @Valid todo: Todo):Todo {
        val todo = tr.save(todo)
        rabbitTemplate.convertAndSend(TodoServiceApplication.SFG_MESSAGE_QUEUE, mapOf("text" to "Hey!! it works!."))
        return todo
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable("id") id:Long) = tr.deleteById(id)

}