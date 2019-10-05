package com.example.todoservice.listener

import com.example.todoservice.model.Todo
import com.example.todoservice.repo.TodoRepository
import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Component

@Component
class TodoMessageListener(val todoRepository: TodoRepository) {
   val log = LogManager.getLogger(this.javaClass)
    fun receiveMessage(message: Map<String, String>) {
        log.info("Received <$message>")
        val description = message["description"]
        description?.apply { todoRepository.save(Todo(description=description)) }
        log.info("Message processed...")
    }
}