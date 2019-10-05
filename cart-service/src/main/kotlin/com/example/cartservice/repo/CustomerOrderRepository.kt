package com.example.cartservice.repo

import com.example.cartservice.model.CustomerOrder
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerOrderRepository :JpaRepository<CustomerOrder, Long>{

    fun findByCustomerId(customerId: String):List<CustomerOrder>

    fun deleteByCustomerId(customerId: String)
}