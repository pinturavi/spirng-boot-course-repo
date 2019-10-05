package com.example.cartservice.repo

import com.example.cartservice.model.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository :JpaRepository<Item, Long>