package com.example.deliveryservice.repo

import com.example.deliveryservice.ItemCategory
import com.example.deliveryservice.model.ProductDelivery
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductDeliveryRepository :JpaRepository<ProductDelivery, Long> {
     fun findByProductCategoryAndLocation(category: ItemCategory, location: String): Optional<ProductDelivery>
}