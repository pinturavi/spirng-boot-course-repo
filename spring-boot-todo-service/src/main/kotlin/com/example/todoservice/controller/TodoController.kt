package com.example.todoservice.controller

import com.example.todoservice.TodoServiceApplication
import com.example.todoservice.exception.TodoNotFoundException
import com.example.todoservice.model.Todo
import com.example.todoservice.repo.TodoRepository
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
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
        val previousStatus = tr.findById(todo.id).map { it.status }.orElseThrow { RuntimeException("Todo is invalid, it has no status") }
        val todoFromDb = tr.save(todo)
        if(previousStatus != todoFromDb.status){
            val msgMap = mapOf("text" to "todo with id ${todoFromDb.id}'s status changed from $previousStatus to ${todoFromDb.status}")
            rabbitTemplate.convertAndSend(TodoServiceApplication.SFG_MESSAGE_QUEUE, msgMap)
        }
        return tr.save(todo)
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable("id") id:Long) = tr.deleteById(id)

}