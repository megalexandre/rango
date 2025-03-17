package rango.com.api.resources.map

import rango.com.api.domain.entity.Customer
import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.Product
import rango.com.api.resources.model.CustomerModel
import rango.com.api.resources.model.OrderModel
import rango.com.api.resources.model.ProductModel

fun CustomerModel.toEntity() = Customer(
    name = name,
    address = address,
)

fun Customer.toModel() = CustomerModel(
    name = name,
    address = address,
)
