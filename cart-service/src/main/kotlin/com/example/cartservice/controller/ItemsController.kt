package com.example.cartservice.controller

import com.example.cartservice.exception.NotFoundException
import com.example.cartservice.model.Item
import com.example.cartservice.repo.CartRepository
import com.example.cartservice.repo.ItemRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/carts/{cartId}/items")
class ItemsController(val cartRepository: CartRepository, val itemRepository: ItemRepository) {

    @GetMapping
    fun getItems(@PathVariable("cartId") cartId: Long): List<Item> {
        return cartRepository.findById(cartId).map { it.items }.orElseThrow { NotFoundException("no carts available with the cart id $cartId") }
    }

    @GetMapping("/{itemId}")
    fun getItemByItemId(@PathVariable("cartId") cartId: Long, @PathVariable("itemId") itemId: Long): Item? {
        return cartRepository.findById(cartId).map { it.items.find { it.itemId == itemId } }.orElseThrow { NotFoundException("no cart available with the given id $cartId") }
    }

    @PostMapping
    fun addItem(@PathVariable("cartId") cartId: Long, @RequestBody item: Item): Item {
        return cartRepository.findById(cartId).map {
            item.itemCount = it.items.find { itm -> itm.itemId == item.itemId }?.itemCount ?: item.itemCount
            item.cart = it
            itemRepository.save(item)
        }.orElseThrow { NotFoundException("no cart available with the cart id $cartId") }
    }

    @PutMapping
    fun updateItem(@PathVariable("cartId") cartId: Long, @RequestBody item: Item): Item {
        return cartRepository.findById(cartId).map {
            item.cart = it
            itemRepository.save(item)
        }.orElseThrow { NotFoundException("no cart available with the cart id $cartId") }
    }

    @DeleteMapping("/{itemId}")
    fun removeItem(@PathVariable("cartId") cartId: Long, @PathVariable("itemId") itemId: Long) {
        return cartRepository.findById(cartId).map {
            itemRepository.deleteById(itemId)
        }.orElseThrow { NotFoundException("no cart available with the cart id $cartId") }
    }
}