package com.example.shoppingcartservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty

@Entity
data class Product(
    @Id
    @GeneratedValue
    var productId:String="",

    var productCategory: String="",

    var productDescription:String="",

    var productManufacturer: String="",

    @get:NotEmpty
    var productName:String="",

    @get:Min(value = 100, message = "product price should be atleast 100")
    var prductPrice:Double=100.0,

    var unitStock:Int=0
    )