package com.example.shoppingcartservice.model

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

data class CartItem(

        @Id
        @GeneratedValue
        var cartItemId:String="",

        var quantity:Int=0,

        var price:Double=0.0,

        @ManyToOne
        @JoinColumn(name="productId")
        var product: Product?=null,

        @ManyToOne
        @JoinColumn(name="cartId")
        var cart: Cart?=null

)