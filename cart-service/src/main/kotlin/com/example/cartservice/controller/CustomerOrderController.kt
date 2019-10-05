package com.example.cartservice.controller

import com.example.cartservice.model.CustomerOrder
import com.example.cartservice.repo.CustomerOrderRepository
import com.example.cartservice.repo.OrderItemRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class CustomerOrderController(val customerOrderRepository: CustomerOrderRepository, val orderItemRepository: OrderItemRepository) {

    @GetMapping
    fun getAllOrders():List<CustomerOrder> = customerOrderRepository.findAll()

    @GetMapping("/{customerId}")
    fun getOrdersByCustomerId(@PathVariable("customerId") customerId:String) = customerOrderRepository.findByCustomerId(customerId)

    @DeleteMapping("/{customerId}")
    fun deleteOrdersById(@PathVariable("customerId") customerId: String) = customerOrderRepository.deleteByCustomerId(customerId)

    @DeleteMapping("/{customerId}/{itemId}")
    fun deleteOrderByItemId(@PathVariable("customerId") customerId: String, @PathVariable("itemId") itemId:Long)=
        orderItemRepository.deleteById(itemId)

}