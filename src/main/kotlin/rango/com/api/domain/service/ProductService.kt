package rango.com.api.domain.service

import rango.com.api.domain.Product

interface ProductService {

    fun getAllProducts(): List<Product>

    fun save(product: Product): Product
}