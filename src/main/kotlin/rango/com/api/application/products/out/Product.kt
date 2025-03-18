package rango.com.api.application.products.out

import org.javamoney.moneta.Money
import rango.com.api.commons.functions.toMoney
import rango.com.api.domain.entity.Product

class ProductRetrieveResponse(
    val number: String,
    val name: String,
    val description: String,
    val price: Money,
)

fun Product.toRetrieveResponse() = ProductRetrieveResponse(
    number = this.number,
    name = this.name,
    description = this.description,
    price = this.price.toMoney()
)