package rango.com.api.application.order

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rango.com.api.application.order.`in`.OrderCreateRequest
import rango.com.api.application.order.out.OrderCreateResponse
import rango.com.api.application.order.out.toResponse
import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.Product
import rango.com.api.domain.service.OrderService
import java.net.URI
import java.time.LocalDateTime

@RestController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    fun list(request: OrderCreateRequest){
        val map: Map<String, Product> = request.products
            .map { it.toEntity() }
            .associateBy { it.number }

        map[""]

        var list = ArrayList<Product>(request.products.map { it.toEntity() })
        list.filter { it.number == "" }
    }
    fun map(request: OrderCreateRequest){
        val map: Map<String, Product> = request.products
            .map { it.toEntity() }
            .associateBy { it.number }

        map[""]

        var list = ArrayList<Product>(request.products.map { it.toEntity() })
        list.filter { it.number == "" }
    }

    @PostMapping
    fun createOrder(@RequestBody request: OrderCreateRequest): ResponseEntity<OrderCreateResponse> {

        val map: Map<String, Product> = request.products
            .map { it.toEntity() }
            .associateBy { it.number }

        map[""]

        var list = ArrayList<Product>(request.products.map { it.toEntity() })
        list.filter { it.number == "" }

        val response = orderService.save(request.toEntity()).toResponse()
        return ResponseEntity.created(URI.create("/orders")).body(response)
    }

    @GetMapping
    fun retrieveOrders(): Collection<Order> = orderService.retrieveOrders()

    @GetMapping("/{number}")
    fun retrieveOrders(@PathVariable number: String): Order? = orderService.retrieveOrder(number)

    @GetMapping("/period/startAt/{startAt}/endAt/{endAt}")
    fun retrieveOrders(
        @PathVariable startAt: LocalDateTime,
        @PathVariable endAt: LocalDateTime,
   ): Collection<Order> = orderService.retrieveOrders()
}