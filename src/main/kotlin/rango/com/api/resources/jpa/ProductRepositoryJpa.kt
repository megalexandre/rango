package rango.com.api.resources.jpa

import rango.com.api.resources.model.ProductModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepositoryJpa : MongoRepository<ProductModel, String>