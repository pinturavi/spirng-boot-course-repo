package com.example.todoservice

import com.example.todoservice.model.Todo
import com.example.todoservice.repo.TodoRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean

@EnableDiscoveryClient
@SpringBootApplication
class TodoServiceApplication(val tr: TodoRepository) {

    @Bean
    fun commandLineRunner() = CommandLineRunner {
        tr.saveAll(
                listOf(
                        Todo(description = "todo 0"),
                        Todo(description = "todo 1"),
                        Todo(description = "todo 2")
                )
        )
    }
}

fun main(args: Array<String>) {
    runApplication<TodoServiceApplication>(*args)
}


