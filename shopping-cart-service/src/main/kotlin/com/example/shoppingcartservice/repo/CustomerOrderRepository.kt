package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.CustomerOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerOrderRepository :JpaRepository<CustomerOrder, String>