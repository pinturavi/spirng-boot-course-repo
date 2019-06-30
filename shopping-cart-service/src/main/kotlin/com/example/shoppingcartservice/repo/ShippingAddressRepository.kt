package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.ShippingAddress
import org.springframework.data.jpa.repository.JpaRepository

interface ShippingAddressRepository :JpaRepository<ShippingAddress, String>