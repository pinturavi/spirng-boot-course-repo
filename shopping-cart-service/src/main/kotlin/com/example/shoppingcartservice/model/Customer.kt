package com.example.shoppingcartservice.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
data class Customer(

        @Id
        @GeneratedValue
        var customerId:Long=0,

        @get:NotEmpty
        var firstName:String="",

        var lastName:String="",

        var phoneNumber:String="",

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "shippingAddressId")
        var shippingAddress:ShippingAddress,

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "billingAddressId")
        var billingAddress: BillingAddress,

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "cartId")
        @JsonIgnore
        var cart:Cart?=null,

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name = "userId")
        private var users: User? = null


)