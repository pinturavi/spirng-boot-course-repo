package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.Cart
import org.springframework.data.jpa.repository.JpaRepository

interface CartRepository: JpaRepository<Cart, Long> {
}