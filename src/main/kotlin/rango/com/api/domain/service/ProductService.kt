package rango.com.api.domain.service

import rango.com.api.domain.entity.Product

interface ProductService {

    fun getAllProducts(): List<Product>

    fun save(product: Product): Product
}