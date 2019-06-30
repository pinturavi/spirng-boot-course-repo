package com.example.shoppingcartservice.repo

import com.example.shoppingcartservice.model.Authorities
import org.springframework.data.jpa.repository.JpaRepository

interface AuthoritiesRepository :JpaRepository<Authorities, String>