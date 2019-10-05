package com.example.cartservice.repo

import com.example.cartservice.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CartRepository :JpaRepository<Cart, Long>{
    fun findByCustomerId(customerId:String): Optional<Cart>
}