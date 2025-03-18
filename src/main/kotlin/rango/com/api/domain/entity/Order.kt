package rango.com.api.domain.entity

import rango.com.api.commons.OrderStatus
import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
    val number: String,
    val products: Collection<Product>,
    val customer: Customer,
    val createdAt: LocalDateTime,
    val status: OrderStatus
){

    val numberOfItems: Int
        get() = products.size

    val total: BigDecimal
        get() = products.sumOf { it.price }
}