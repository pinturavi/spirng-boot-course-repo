package com.example.gatewayservice.service

import com.example.gatewayservice.model.Todo

interface TodoCacheManager {
    fun cacheTodos(todo: Todo)
}