package com.example.deliveryservice

import com.example.deliveryservice.model.ProductDelivery
import com.example.deliveryservice.repo.ProductDeliveryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DeliveryServiceApplication{

	@Bean
	fun commandLineRunner(productDeliveryRepository: ProductDeliveryRepository)=CommandLineRunner{
		val productDeliveryList = listOf(
				ProductDelivery(productCategory = ItemCategory.ANDROID, location = "chennai", daysToDeliver = 2),
				ProductDelivery(productCategory = ItemCategory.ANDROID, location = "bengaluru", daysToDeliver = 4),
				ProductDelivery(productCategory = ItemCategory.ANDROID, location = "mumbai", daysToDeliver = 5),
				ProductDelivery(productCategory = ItemCategory.WINDOWS, location = "chennai", daysToDeliver = 2),
				ProductDelivery(productCategory = ItemCategory.IOS, location = "bengaluru", daysToDeliver = 4),
				ProductDelivery(productCategory = ItemCategory.MAC, location = "mumbai", daysToDeliver = 5)
		)
		productDeliveryRepository.saveAll(productDeliveryList)
	}
}

fun main(args: Array<String>) {
	runApplication<DeliveryServiceApplication>(*args)
}
