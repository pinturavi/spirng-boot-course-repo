package com.example.cartservice.model

import javax.persistence.*

@Entity
data class Cart(
        @Id
        @GeneratedValue
        var cartId:Long=0,

        @Column(unique = true)
        var customerId:String?=null,

        @OneToMany(mappedBy = "cart", orphanRemoval=true)
        var items:List<Item> = listOf()
)