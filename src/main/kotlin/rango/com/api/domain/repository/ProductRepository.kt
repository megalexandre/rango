package rango.com.api.domain.repository

import rango.com.api.domain.entity.Product

interface ProductRepository {

    fun getAllProducts(): List<Product>
    fun existsByNumber(productsNumber: Collection<String>): Boolean
    fun save(product: Product): Product

}