package com.example.todoservice.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class TodoNotFoundException(s: String) : RuntimeException(s)