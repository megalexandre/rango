package rango.com.api.domain.repository

import rango.com.api.domain.Product

interface ProductRepository {

    fun getAllProducts(): List<Product>
    fun save(product: Product): Product

}