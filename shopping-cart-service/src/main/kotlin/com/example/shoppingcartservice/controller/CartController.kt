package com.example.shoppingcartservice.controller

import com.example.shoppingcartservice.service.CartService
import com.example.shoppingcartservice.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CartController(val customerService: CustomerService, val cartService: CartService) {

    @GetMapping("")
    fun getCartById(@PathVariable("cartId") cartId:Long){

    }
}