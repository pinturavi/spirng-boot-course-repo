package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository:JpaRepository<Customer, Long>