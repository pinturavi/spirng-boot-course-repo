package com.example.shoppingcartservice.model

import javax.persistence.*

@Entity
data class CustomerOrder(

        @Id
        @GeneratedValue
        var customerOrderId:String="",

        @OneToOne
        @JoinColumn(name = "cartId")
        var cart: Cart?=null,

        @OneToOne
        @JoinColumn(name = "customerId")
        var customer:Customer?=null,

        @OneToOne
        @JoinColumn(name = "shippingAddressId")
        var shippingAddress: ShippingAddress?=null,

        @OneToOne
        @JoinColumn(name = "billingAddressId")
        var billingAddress: BillingAddress?=null


)