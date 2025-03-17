package rango.com.api.resources.map

import rango.com.api.domain.entity.Product
import rango.com.api.resources.model.ProductModel

fun ProductModel.toEntity() = Product(
    number = this.number,
    name = this.name,
    description = this.description,
    price = this.price
)

fun Product.toModel() = ProductModel(
    number = this.number,
    name = this.name,
    description = this.description,
    price = this.price
)
