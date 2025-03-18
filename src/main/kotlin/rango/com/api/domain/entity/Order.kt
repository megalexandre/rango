package rango.com.api.domain.entity

import rango.com.api.commons.OrderStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
    val number: String,
    val items: Collection<OrderItem>,
    val customer: Customer,
    val createdAt: LocalDateTime,
    val status: OrderStatus
){

    val numberOfItems: Int
        get() = items.sumOf { it.quantity }

    val total: BigDecimal
        get() = items.sumOf { it.product.price.multiply(BigDecimal(it.quantity)) }
}