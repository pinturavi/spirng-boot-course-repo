package com.example.gatewayservice.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.ListOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import java.util.concurrent.TimeUnit


@Configuration
class RedisUtil<T>(val redisTemplate: RedisTemplate<String, Any>) {

    private val hashOperation: HashOperations<String, Any, T> = redisTemplate.opsForHash()

    private val listOperation: ListOperations<String, Any> = redisTemplate.opsForList()

    private val valueOperations: ValueOperations<String, Any> = redisTemplate.opsForValue()


    fun putMap(redisKey: String, key: Any, data: T) = hashOperation.put(redisKey, key, data)


    fun getMapAsSingleEntry(redisKey: String, key: Any): T? = hashOperation.get(redisKey, key)


    fun getMapAsAll(redisKey: String): Map<Any, T> = hashOperation.entries(redisKey)


    fun putValue(key: String, value: Any) = valueOperations.set(key, value)


    fun putValueWithExpireTime(key: String, value: Any, timeout: Long, unit: TimeUnit) = valueOperations.set(key, value, timeout, unit)


    fun getValue(key: String): Any? = valueOperations.get(key)


    fun setExpire(key: String, timeout: Long, unit: TimeUnit) = redisTemplate.expire(key, timeout, unit)


}