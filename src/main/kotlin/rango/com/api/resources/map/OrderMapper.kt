package rango.com.api.resources.map

import rango.com.api.domain.entity.Order
import rango.com.api.resources.model.OrderModel

fun OrderModel.toEntity() = Order(
    number = number,
    products = products.map { it.toEntity() } ,
    customer = customer.toEntity(),
    createdAt = createdAt,
    status = status,
)

fun Order.toModel() = OrderModel(
    number = number,
    products = products.map { it.toModel() } ,
    customer = customer.toModel(),
    createdAt = createdAt,
    status = status,
)
