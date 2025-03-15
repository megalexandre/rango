package rango.com.api.application.products.`in`

import rango.com.api.commons.Id
import rango.com.api.domain.Product
import java.math.BigDecimal

class ProductCreateRequest (
    val name: String,
    val description: String,
    val price: BigDecimal,
){
    fun toEntity() = Product(
        number = Id.random(),
        name = name,
        description = description,
        price = price
    )
}
