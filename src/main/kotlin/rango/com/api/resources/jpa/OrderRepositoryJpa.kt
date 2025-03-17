package rango.com.api.resources.jpa

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import rango.com.api.resources.model.OrderModel

@Repository
interface OrderRepositoryJpa : MongoRepository<OrderModel, String>