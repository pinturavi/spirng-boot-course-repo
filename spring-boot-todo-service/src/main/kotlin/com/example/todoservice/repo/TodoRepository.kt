package com.example.todoservice.repo

import com.example.todoservice.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>