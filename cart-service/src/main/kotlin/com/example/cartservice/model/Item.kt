package com.example.cartservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Item(
        @Id
        @GeneratedValue
        var itemId:Long=0,

        var itemName:String?=null,

        var unitPrice:Double=0.0,

        var bought:Boolean=false,

        @JsonIgnore
        @ManyToOne(fetch = FetchType.LAZY)
        var cart: Cart?=null
)