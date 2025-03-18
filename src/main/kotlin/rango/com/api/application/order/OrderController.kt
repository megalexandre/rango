package rango.com.api.application.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rango.com.api.application.order.`in`.OrderCreateRequest
import rango.com.api.application.order.out.OrderCreateResponse
import rango.com.api.application.order.out.OrderRetrieveResponse
import rango.com.api.application.order.out.toCreateResponse
import rango.com.api.application.order.out.toOrderItemRetrieveResponse
import rango.com.api.commons.OrderStatus
import rango.com.api.domain.service.OrderService
import java.net.URI
import java.time.LocalDateTime

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun createOrder(@RequestBody request: OrderCreateRequest): ResponseEntity<OrderCreateResponse> {
        val response = orderService.save(request.toEntity()).toCreateResponse()
        return ResponseEntity.created(URI.create("/orders")).body(response)
    }

    @GetMapping
    fun retrieveOrders(): Collection<OrderRetrieveResponse> = orderService.retrieveOrders().map { it.toOrderItemRetrieveResponse() }

    @GetMapping("/{number}")
    fun retrieveOrders(@PathVariable number: String): OrderRetrieveResponse? = orderService.retrieveOrder(number)?.toOrderItemRetrieveResponse()

    @GetMapping("/period/startAt/{startAt}/endAt/{endAt}")
    fun retrieveOrders(
        @PathVariable startAt: LocalDateTime,
        @PathVariable endAt: LocalDateTime,
   ): Collection<OrderRetrieveResponse> = orderService.retrieveOrders(startAt, endAt).map { it.toOrderItemRetrieveResponse() }

    @PutMapping("/{number}/status/{status}")
    fun updateOrder(@PathVariable number: String, @PathVariable status: OrderStatus): OrderRetrieveResponse =
        orderService.updateOrderStatus(number, status).toOrderItemRetrieveResponse()

}