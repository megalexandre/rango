package rango.com.api.resources.map

import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.OrderItem
import rango.com.api.resources.model.OrderItemModel
import rango.com.api.resources.model.OrderModel

fun OrderModel.toEntity() = Order(
    number = number,
    items = items.map { it.toEntity() },
    customer = customer.toEntity(),
    createdAt = createdAt,
    status = status,
)

fun Order.toModel() = OrderModel(
    number = number,
    items = items.map { it.toModel() } ,
    customer = customer.toModel(),
    createdAt = createdAt,
    status = status,
)

fun OrderItemModel.toEntity() = OrderItem(
    product = product.toEntity(),
    quantity = quantity
)

fun OrderItem.toModel() = OrderItemModel(
    product = product.toModel(),
    quantity = quantity
)