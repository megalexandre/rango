package rango.com.api.domain.service

import rango.com.api.domain.entity.Order

interface OrderService {

    fun save(order: Order): Order
}