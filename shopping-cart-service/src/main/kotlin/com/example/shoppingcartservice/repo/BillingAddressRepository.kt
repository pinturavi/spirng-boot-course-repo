package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.BillingAddress
import org.springframework.data.jpa.repository.JpaRepository

interface BillingAddressRepository:JpaRepository<BillingAddress, String>