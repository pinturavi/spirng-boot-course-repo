package com.example.gatewayservice.model

import com.example.gatewayservice.TodoStatus
import java.io.Serializable

data class Todo(var id:Long=0, var description:String="", var status:TodoStatus=TodoStatus.INITIATED): Serializable
