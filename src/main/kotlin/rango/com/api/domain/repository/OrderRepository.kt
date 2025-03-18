package rango.com.api.domain.repository

import rango.com.api.domain.entity.Order
import java.time.LocalDateTime

interface OrderRepository {

    fun save(order: Order): Order
    fun retrieveOrders(): Collection<Order>
    fun retrieveOrders(startAt: LocalDateTime, endsAt: LocalDateTime): Collection<Order>
    fun retrieveOrder(string: String): Order?

}