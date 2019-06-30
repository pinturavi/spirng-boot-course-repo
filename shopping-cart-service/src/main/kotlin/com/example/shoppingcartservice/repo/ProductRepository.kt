package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository :JpaRepository<Product, String>