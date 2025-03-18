package rango.com.api.resources.jpa

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import rango.com.api.resources.model.OrderModel
import java.time.LocalDateTime

@Repository
interface OrderRepositoryJpa : MongoRepository<OrderModel, String>{
    fun findByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Collection<OrderModel>
}