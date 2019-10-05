package com.example.gatewayservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
import org.springframework.data.redis.serializer.GenericToStringSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration


@Configuration
class RedisConfiguration {

    @Value(value = "\${spring.redis.host}")
    lateinit var REDIS_HOSTNAME: String

    @Value("\${spring.redis.port}")
    var REDIS_PORT: Int=0


    @Bean
    protected fun jedisConnectionFactory(): JedisConnectionFactory {

        val configuration = RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT)

        val jedisClientConfiguration = JedisClientConfiguration.builder().usePooling().build()

        val factory = JedisConnectionFactory(configuration, jedisClientConfiguration)

        factory.afterPropertiesSet()

        return factory

    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {

        val redisTemplate = RedisTemplate<String, Any>()

        redisTemplate.keySerializer = StringRedisSerializer()

        redisTemplate.hashKeySerializer = GenericToStringSerializer(Any::class.java)

        redisTemplate.hashValueSerializer = JdkSerializationRedisSerializer()

        redisTemplate.valueSerializer = JdkSerializationRedisSerializer()

        redisTemplate.setConnectionFactory(jedisConnectionFactory())

        return redisTemplate

    }

}