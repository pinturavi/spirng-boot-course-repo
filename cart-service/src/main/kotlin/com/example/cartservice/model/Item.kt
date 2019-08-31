package com.example.cartservice.model

import com.example.cartservice.ItemCategory
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Item(
        @Id
        @GeneratedValue
        var itemId: Long = 0,

        var itemName: String? = null,

        var unitPrice: Double = 0.0,

        var itemCount:Int = 1,

        @Enumerated(EnumType.STRING)
        var itemCategory: ItemCategory = ItemCategory.ANDROID,

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        var cart: Cart? = null
)