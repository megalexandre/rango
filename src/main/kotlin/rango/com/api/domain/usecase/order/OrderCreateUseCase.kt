package rango.com.api.domain.usecase.order

import org.springframework.stereotype.Service
import rango.com.api.domain.entity.Order
import rango.com.api.domain.repository.OrderRepository
import rango.com.api.domain.repository.ProductRepository
import rango.com.api.infrastructure.exception.InvalidUseCaseException
import rango.com.api.infrastructure.observability.MetricType
import rango.com.api.infrastructure.observability.MetricsService

@Service
class OrderCreateUseCase(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val metricsService: MetricsService

) {

    fun execute(order: Order): Order {

        val productsNumber = order.items.map { it.product.number }
        val existsByNumber = productRepository.existsByNumber(productsNumber)

        if (!existsByNumber) {
            throw InvalidUseCaseException("Product not found")
        }


        return orderRepository.save(order).also {
            metricsService.increment(MetricType.ORDERS_CREATED)
            metricsService.sum(MetricType.ORDERS_REQUESTED_VALUE, it.total.toDouble())
        }
    }
}