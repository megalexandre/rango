package rango.com.api.domain.usecase.order

import org.springframework.stereotype.Service
import rango.com.api.domain.entity.Order
import rango.com.api.domain.repository.OrderRepository
import rango.com.api.domain.repository.ProductRepository
import rango.com.api.infrastructure.exception.InvalidUseCaseException

@Service
class OrderCreateUseCase(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
) {

    fun execute(order: Order): Order {

        val existsByNumber = productRepository.existsByNumber(order.products.map { it.number })

        if (!existsByNumber) {
            throw InvalidUseCaseException("Product not found")
        }

        return orderRepository.save(order)
    }
}