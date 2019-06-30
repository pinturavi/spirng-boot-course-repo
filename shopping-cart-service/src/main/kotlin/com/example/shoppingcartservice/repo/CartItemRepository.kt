package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.CartItem
import org.springframework.data.jpa.repository.JpaRepository

interface CartItemRepository:JpaRepository<CartItem, String>