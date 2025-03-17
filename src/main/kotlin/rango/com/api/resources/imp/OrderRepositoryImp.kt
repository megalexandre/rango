package rango.com.api.resources.imp

import org.springframework.stereotype.Repository
import rango.com.api.domain.entity.Order
import rango.com.api.domain.repository.OrderRepository
import rango.com.api.resources.jpa.OrderRepositoryJpa
import rango.com.api.resources.map.toEntity
import rango.com.api.resources.map.toModel
import kotlin.jvm.optionals.getOrNull

@Repository
class OrderRepositoryImp(
    val repository: OrderRepositoryJpa
): OrderRepository {

    override fun save(order: Order): Order = repository.save(order.toModel()).toEntity()

    override fun retrieveOrders(): Collection<Order> = repository.findAll().map { it -> it.toEntity()  }

    override fun retrieveOrder(string: String): Order? = repository.findById(string).map { it.toEntity() }.getOrNull()

}