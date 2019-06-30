package com.example.cartservice.controller

import com.example.cartservice.exception.NotFoundException
import com.example.cartservice.model.Cart
import com.example.cartservice.model.Item
import com.example.cartservice.repo.CartRepository
import com.example.cartservice.repo.ItemRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts/id/{cartId}/items")
class ItemsController(val cr: CartRepository, val ir: ItemRepository) {

    @GetMapping
    fun getItems(@PathVariable("cartId") cartId: Long): List<Item> {
        return cr.findById(cartId).map { it.items }.orElseThrow { NotFoundException("no carts available with the cart id $cartId") }
    }

    @GetMapping("/{itemId}")
    fun getItemByItemId(@PathVariable("cartId") cartId: Long, @PathVariable("itemId") itemId: Long): Item? {
        return cr.findById(cartId).map { it.items.find { it.itemId == itemId } }.orElseThrow { NotFoundException("no cart available with the given id $cartId") }
    }

    @PostMapping
    fun addItem(@PathVariable("cartId") cartId: Long, @RequestBody item: Item): Item {
        return cr.findById(cartId).map {
            item.cart = it
            ir.save(item)
        }.orElseThrow { NotFoundException("no cart available with the cart id $cartId") }
    }

    @PutMapping
    fun updateItem(@PathVariable("cartId") cartId: Long, @RequestBody item: Item): Item {
        return cr.findById(cartId).map {
            item.cart = it
            ir.save(item)
        }.orElseThrow { NotFoundException("no cart available with the cart id $cartId") }
    }

    @DeleteMapping("/{itemId}")
    fun removeItem(@PathVariable("cartId") cartId: Long, @PathVariable("itemId") itemId: Long) {
        return cr.findById(cartId).map {
            ir.deleteById(itemId)
        }.orElseThrow { NotFoundException("no cart available with the cart id $cartId") }
    }
}