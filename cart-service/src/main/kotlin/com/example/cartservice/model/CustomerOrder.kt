package com.example.cartservice.model

import javax.persistence.*

@Entity
data class CustomerOrder(
        @Id
        @GeneratedValue
        var id:Long=0,

        var customerId:String?=null,

        @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.REMOVE])
        @JoinColumn(name = "cartId")
        var cart: Cart?=null
)