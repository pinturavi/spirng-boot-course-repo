package com.example.cartservice.controller

import com.example.cartservice.DeliveryProxy
import com.example.cartservice.exception.NotFoundException
import com.example.cartservice.model.Cart
import com.example.cartservice.model.CustomerOrder
import com.example.cartservice.model.OrderItem
import com.example.cartservice.repo.CartRepository
import com.example.cartservice.repo.CustomerOrderRepository
import com.example.cartservice.repo.ItemRepository
import com.example.cartservice.repo.OrderItemRepository
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/carts")
class CartsController(val cartRepository: CartRepository,
                      val customerOrderRepository: CustomerOrderRepository,
                      val orderItemRepository: OrderItemRepository,
                      val itemRepository: ItemRepository,
                      val deliveryProxy: DeliveryProxy
) {

    @GetMapping
    fun getAllCarts() = cartRepository.findAll()

    @GetMapping("/{cartId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCartById(@PathVariable("cartId") cartId: Long): Cart {
        return cartRepository.findById(cartId).map { it }.orElseThrow { NotFoundException("No carts available with the cart id $cartId") }
    }

    @GetMapping("/customer-name/{customerId}")
    fun getCartByCustomerName(@PathVariable("customerId") customerId: String): Cart {
        return cartRepository.findByCustomerId(customerId).map { it }.orElseThrow { NotFoundException("Cart not available with the customer id $customerId") }
    }

    @PostMapping
    fun addCart(@RequestBody cart: Cart): Cart {
        return cartRepository.save(cart)
    }

    @DeleteMapping
    fun deleteCartByCartId(@PathVariable("cartId") cartId: Long) {
        cartRepository.deleteById(cartId)
    }

    @PutMapping
    fun updateCart(@RequestBody cart: Cart): Cart {
        return cartRepository.save(cart)
    }


    @PutMapping("/{id}/confirm")
    fun confirmOrder(@PathVariable("id") id: Long, @RequestParam("location") location:String): CustomerOrder {
        val cart: Cart = cartRepository.findById(id).map { it }.orElseThrow { return@orElseThrow NotFoundException("cart not found for the cart id $id") }
        val customerOrder = customerOrderRepository.save(CustomerOrder(customerId = cart.customerId, location = location))
        val orderItems:List<OrderItem> = cart.items.map { OrderItem(itemName=it.itemName, unitPrice=it.unitPrice, itemCategory=it.itemCategory, customerOrder=customerOrder) }
        orderItems.map { it.deliveryTime = getDeliveryTime(location, it) }
        orderItemRepository.saveAll(orderItems)
        cart.items.forEach { itemRepository.deleteById(it.itemId) }
        return customerOrderRepository.findById(customerOrder.id).map { it }.orElseThrow { NotFoundException("no orders found") }
    }

    @HystrixCommand(fallbackMethod = "getDefaultDeliveryTime")
    private fun getDeliveryTime(location: String, orderItem: OrderItem) =
            deliveryProxy.getDeliveryTime(location, orderItem.itemCategory)["deliveryTime"]

    fun getDefaultDeliveryTime():LocalDateTime = LocalDateTime.now().plusDays(5)
}