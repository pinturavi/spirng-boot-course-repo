package com.example.cartservice

import com.example.cartservice.model.Cart
import com.example.cartservice.model.Item
import com.example.cartservice.repo.CartRepository
import com.example.cartservice.repo.ItemRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.hystrix.EnableHystrix
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
class CartServiceApplication {

    @Bean
    fun commandLineRunner(cartRepository: CartRepository, itemRepository:ItemRepository) = CommandLineRunner {
		val cart1 = Cart(customerId = "ravi")
		val nokia5 = Item(itemName = "Nokia 6", unitPrice = 16000.0, cart = cart1)
		val nokia6 = Item(itemName = "Nokia 5", unitPrice = 15000.0, cart = cart1)
		cart1.items= listOf(nokia5, nokia6)
  		cartRepository.save(
				cart1
		)
		itemRepository.saveAll(listOf(nokia5, nokia6))

    }
}

fun main(args: Array<String>) {
    runApplication<CartServiceApplication>(*args)
}
