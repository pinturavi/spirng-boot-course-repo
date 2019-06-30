package com.example.shoppingcartservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.persistence.CascadeType.*

@Entity
data class Cart(

        @Id
        @GeneratedValue
        var cartId:Long=0,

        @OneToOne
        @JoinColumn(name = "customerId")
        @JsonIgnore
        var customer: Customer?=null,

        @OneToMany(mappedBy = "cart", cascade = arrayOf(ALL),fetch=FetchType.EAGER)
        var products:List<Product> = listOf(),

        var totalPrice:Double=0.0

        )