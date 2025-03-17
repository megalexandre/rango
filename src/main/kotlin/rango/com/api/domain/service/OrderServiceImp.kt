package rango.com.api.domain.service

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Service
import rango.com.api.domain.entity.Order
import rango.com.api.domain.repository.OrderRepository

@Service
class OrderServiceImp(
    private val orderRepository: OrderRepository,
    meterRegistry: MeterRegistry
): OrderService {

    private var orderCreated: Counter = Counter.builder("created_order").register(meterRegistry)

    override fun save(order: Order) = orderRepository.save(order).also {
        orderCreated.increment()
    }

    override fun retrieveOrders(): Collection<Order> {
        return orderRepository.retrieveOrders()
    }

    override fun retrieveOrder(string: String): Order? = orderRepository.retrieveOrder(string)
}