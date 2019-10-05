package com.example.cartservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class CustomerOrder(
        @Id
        @GeneratedValue
        var id:Long=0,

        var customerId:String?=null,

        @OneToMany(mappedBy = "customerOrder", orphanRemoval=true)
        var items:List<OrderItem>?= listOf(),

        var location:String=""
)