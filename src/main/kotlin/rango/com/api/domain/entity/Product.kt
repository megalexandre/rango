package rango.com.api.domain.entity

import java.math.BigDecimal

class Product(
    val number: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
)