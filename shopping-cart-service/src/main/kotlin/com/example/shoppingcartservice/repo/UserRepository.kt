package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository :JpaRepository<User, String>