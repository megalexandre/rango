package rango.com.api.domain.service

import rango.com.api.commons.OrderStatus
import rango.com.api.domain.entity.Order
import java.time.LocalDateTime

interface OrderService {

    fun save(order: Order): Order
    fun retrieveOrders(): Collection<Order>
    fun retrieveOrders(startAt: LocalDateTime, endsAt: LocalDateTime): Collection<Order>
    fun retrieveOrder(string: String): Order?
    fun updateOrderStatus(string: String, status: OrderStatus): Order

}