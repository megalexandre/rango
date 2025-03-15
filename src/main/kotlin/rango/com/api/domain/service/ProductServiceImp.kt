package rango.com.api.domain.service

import rango.com.api.domain.Product
import rango.com.api.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
    class ProductServiceImp(
    val productRepository: ProductRepository
): ProductService {


    override fun getAllProducts(): List<Product> = productRepository.getAllProducts()

    override fun save(product: Product) = productRepository.save(product)
}