package com.example.cartservice.controller

import com.example.cartservice.exception.NotFoundException
import com.example.cartservice.model.Cart
import com.example.cartservice.model.CustomerOrder
import com.example.cartservice.repo.CartRepository
import com.example.cartservice.repo.CustomerOrderRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts")
class CartsController(val cr: CartRepository, val cor:CustomerOrderRepository) {

    @GetMapping
    fun getAllCarts() = cr.findAll()

    @GetMapping("/id/{cartId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCartById(@PathVariable("cartId") cartId: Long): Cart {
        return cr.findById(cartId).map { it }.orElseThrow { NotFoundException("No carts available with the cart id $cartId") }
    }

    @GetMapping("/customer-name/{customerId}")
    fun getCartByCustomerName(@PathVariable("customerId") customerId: String): Cart {
        return cr.findByCustomerId(customerId).map { it }.orElseThrow { NotFoundException("Cart not available with the customer id $customerId") }
    }

    @PostMapping
    fun addCart(@RequestBody cart:Cart):Cart{
        return cr.save(cart)
    }

    @DeleteMapping
    fun deleteCartByCartId(@PathVariable("cartId") cartId:Long){
        cr.deleteById(cartId)
    }

    @PutMapping
    fun updateCart(@RequestBody cart:Cart):Cart{
        return cr.save(cart)
    }

    @PutMapping("/customer-name/{customerId}/")
    fun confirmOrder(@PathVariable("customerId") customerId: String):CustomerOrder{
        return cr.findByCustomerId(customerId).map {
           val customerOrder= cor.save(CustomerOrder(customerId = customerId, cart=it))
            it.items.map { it.bought=true }
            cr.save(it)
            customerOrder
        }.orElseThrow { NotFoundException("cart not found for the customer id $customerId") }
    }
}