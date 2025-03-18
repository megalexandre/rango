package rango.com.api.application.order.`in`

import rango.com.api.application.products.`in`.ProductCreateRequest
import rango.com.api.commons.Id
import rango.com.api.commons.OrderStatus
import rango.com.api.domain.entity.Customer
import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.Product
import java.math.BigDecimal
import java.time.LocalDateTime

class OrderCreateRequest (
    val products: Collection<ProductRequest>,
    val customer: CustomerRequest,
){

    fun toEntity() = Order(
        number = Id.random(),
        products = products.map { it.toEntity() },
        customer = customer.toEntity(),
        createdAt = LocalDateTime.now(),
        status = OrderStatus.OPEN,
    )

}

class ProductRequest(
    val number: String,
    val name: String,
    val description: String,
    val price: BigDecimal,
){
    fun toEntity() = Product(
        number = number,
        name = name,
        description = description,
        price = price
    )
}

class CustomerRequest(
    val name: String,
    val address: String
) {
    fun toEntity() = Customer(
        name = name,
        address = address
    )
}