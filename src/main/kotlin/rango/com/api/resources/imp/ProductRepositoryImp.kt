package rango.com.api.resources.imp

import rango.com.api.domain.entity.Product
import rango.com.api.domain.repository.ProductRepository
import rango.com.api.resources.jpa.ProductRepositoryJpa
import rango.com.api.resources.map.toEntity
import rango.com.api.resources.map.toModel
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImp(
    val productRepositoryJpa: ProductRepositoryJpa
): ProductRepository {

    override fun getAllProducts(): List<Product> {
        return productRepositoryJpa.findAll().map { it -> it.toEntity() }
    }

    override fun save(product: Product): Product = productRepositoryJpa.save(product.toModel()).toEntity()


}