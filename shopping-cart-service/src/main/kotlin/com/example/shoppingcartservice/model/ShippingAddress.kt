package com.example.shoppingcartservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class ShippingAddress (
        @Id
        @GeneratedValue
        var shippingAddressId:String="",

        var address:String="",

        var city:String="",

        var state:String="",

        var zipCode:String="",

        var country:String="",

        @OneToOne(mappedBy = "billingAddress")
        var customer:Customer?=null
)
