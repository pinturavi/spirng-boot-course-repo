package com.example.gatewayservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
class GatewayServiceApplication

fun main(args: Array<String>) {
    runApplication<GatewayServiceApplication>(*args)
}
