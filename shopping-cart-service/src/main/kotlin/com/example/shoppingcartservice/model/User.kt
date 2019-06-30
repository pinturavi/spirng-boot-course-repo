package com.example.shoppingcartservice.model

import java.io.Serializable

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userId: String? = null
    var emailId: String? = null
    var password: String? = null
    var isEnabled: Boolean = false

    @OneToOne(mappedBy = "users")
    var customer: Customer? = null

}
