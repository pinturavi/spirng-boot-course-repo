package com.example.shoppingcartservice.model

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

data class BillingAddress (
        @Id
        @GeneratedValue
        var billingAddressId:String="",

        var address:String="",

        var city:String="",

        var state:String="",

        var zipCode:String="",

        var country:String="",

        @OneToOne(mappedBy = "billingAddress")
        var customer:Customer?=null
)