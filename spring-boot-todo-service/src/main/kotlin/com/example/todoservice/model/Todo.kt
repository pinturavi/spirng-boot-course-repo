package com.example.todoservice.model

import com.example.todoservice.TodoStatus
import java.io.Serializable
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
data class Todo(
        @Id
        @GeneratedValue
        var id: Long = 0,

        @Size(min = 3, message = "description size should be atleast 3")
        var description: String?=null,

        @Enumerated(EnumType.STRING)
        var status:TodoStatus=TodoStatus.INITIATED

):Serializable