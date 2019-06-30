package com.example.shoppingcartservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "authorities")
class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var authorityId: String? = null
    var emailId: String? = null
    var authorities: String? = null

}
