package rango.com.api.domain.repository

import rango.com.api.domain.entity.Order

interface OrderRepository {

    fun save(order: Order): Order
    fun retrieveOrders(): Collection<Order>
    fun retrieveOrder(string: String): Order?

}