package rango.com.api.domain.entity

import java.time.LocalDateTime

class Order(
    val number: String,
    val products: Collection<Product>,
    val customer: Customer,
    val createdAt: LocalDateTime,
    val status: String
)