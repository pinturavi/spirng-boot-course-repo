package com.example.cartservice.repo

import com.example.cartservice.model.OrderItem
import org.springframework.data.jpa.repository.JpaRepository

interface OrderItemRepository :JpaRepository<OrderItem, Long>