package com.example.gatewayservice.service

import com.example.gatewayservice.config.RedisUtil
import com.example.gatewayservice.model.Todo
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class TodoCacheManagerImpl(val redisUtil:RedisUtil<String>, val mapper:ObjectMapper):TodoCacheManager {
    companion object{
         const val TODO_TABLE = "TODO_TABLE"
        const val TODO = "TODO"
    }
    override fun cacheTodos(todo: Todo) {
        val randomValue = TODO+ Random().nextLong()
        redisUtil.putMap(TODO_TABLE, randomValue, mapper.writeValueAsString(todo))
       // redisUtil.setExpire(key=TODO_TABLE, timeout=1L, unit = TimeUnit.MINUTES)
    }
}