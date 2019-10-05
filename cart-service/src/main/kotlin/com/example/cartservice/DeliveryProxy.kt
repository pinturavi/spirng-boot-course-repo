package com.example.cartservice

import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.time.LocalDateTime


@FeignClient(url ="\${delivery-service-uri}", name = "delivery-service")
interface DeliveryProxy {

    @GetMapping("/delivery/{location}/{category}")
    fun getDeliveryTime(@PathVariable("location") location:String, @PathVariable("category") category:ItemCategory): Map<String, LocalDateTime>

}