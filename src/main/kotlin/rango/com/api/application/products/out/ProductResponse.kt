package rango.com.api.application.products.out

import rango.com.api.domain.Product

class ProductResponse (val number: String)

fun Product.toResponse() = ProductResponse(this.number)