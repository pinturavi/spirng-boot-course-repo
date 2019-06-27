package com.example.todoservice.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
data class Todo(
        @Id
        @GeneratedValue
        var id: Long = 0,

        @Size(min = 3, message = "description size should be atleast 3")
        var description: String
)