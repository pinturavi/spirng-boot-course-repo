package com.example.todoservice.controller

import com.example.todoservice.model.Todo
import com.example.todoservice.exception.TodoNotFoundException
import com.example.todoservice.repo.TodoRepository
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todos")
class TodoController(val tr: TodoRepository) {

    @GetMapping
    fun retrieveAllTodos():List<Todo> = tr.findAll()

    @GetMapping("/{id}")
    fun retrieveTodoById(@PathVariable("id") id: Long):Todo = tr.findById(id).map { it }.orElseThrow { TodoNotFoundException("") }

    @PostMapping
    fun addTodo(@RequestBody @Valid todo: Todo):Todo = tr.save(todo)

    @PutMapping
    fun updateTodo(@RequestBody @Valid todo: Todo):Todo = tr.save(todo)

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable("id") id:Long) = tr.deleteById(id)

}