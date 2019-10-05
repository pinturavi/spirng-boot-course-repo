package com.example.cartservice.model

import com.example.cartservice.ItemCategory
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class OrderItem(
        @Id
        @GeneratedValue
        var itemId:Long=0,

        var itemName:String?=null,

        var unitPrice:Double=0.0,

        var itemCount: Int = 1,

        var deliveryTime:LocalDateTime?=null,

        @Enumerated(EnumType.STRING)
        var itemCategory: ItemCategory = ItemCategory.ANDROID,

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        var customerOrder: CustomerOrder?=null
)