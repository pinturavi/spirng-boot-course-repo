package com.example.deliveryservice.model

import com.example.deliveryservice.ItemCategory
import javax.persistence.*

@Entity
data class ProductDelivery(
        @Id
        @GeneratedValue
        var id:Long=0,

        @Enumerated(EnumType.STRING)
        var productCategory:ItemCategory=ItemCategory.ANDROID,

        var location:String="",

        var daysToDeliver:Int=0
)