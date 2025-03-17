package rango.com.api.application.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rango.com.api.application.order.`in`.OrderCreateRequest
import rango.com.api.application.order.out.OrderCreateResponse
import rango.com.api.application.order.out.toResponse
import rango.com.api.domain.service.OrderService
import java.net.URI

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun createOrder(@RequestBody request: OrderCreateRequest): ResponseEntity<OrderCreateResponse> {
        val response = orderService.save(request.toEntity()).toResponse()
        return ResponseEntity.created(URI.create("/orders")).body(response)
    }

}