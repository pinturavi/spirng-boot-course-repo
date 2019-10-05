package com.example.deliveryservice.controller

import com.example.deliveryservice.ItemCategory
import com.example.deliveryservice.repo.ProductDeliveryRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class ProductDeliveryController(val productDeliveryRepository: ProductDeliveryRepository){

    @GetMapping("/delivery/{location}/{category}")
    fun getDeliveryTime(@PathVariable("location") location:String, @PathVariable("category") category:ItemCategory): Map<String, LocalDateTime> {
       val noOfDays = productDeliveryRepository.findByProductCategoryAndLocation(category, location).map { it.daysToDeliver }.orElse(6)
        return mapOf("deliveryTime" to LocalDateTime.now().plusDays(noOfDays.toLong()))
    }
}