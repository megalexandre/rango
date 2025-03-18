package rango.com.api.domain.service

import org.springframework.stereotype.Service
import rango.com.api.commons.OrderStatus
import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.OrderStatusUpdater
import rango.com.api.domain.repository.OrderRepository
import rango.com.api.domain.usecase.order.OrderCreateUseCase
import rango.com.api.domain.usecase.order.OrderStatusUpdaterUseCase
import rango.com.api.infrastructure.exception.DataNotFoundException
import java.time.LocalDateTime

@Service
class OrderServiceImp(
    private val orderRepository: OrderRepository,
    private val orderStatusUpdaterUseCase: OrderStatusUpdaterUseCase,
    private val orderCreateUseCase: OrderCreateUseCase,
): OrderService {

    override fun save(order: Order) = orderCreateUseCase.execute(order)

    override fun retrieveOrders(): Collection<Order>  = orderRepository.retrieveOrders()

    override fun retrieveOrders(
        startAt: LocalDateTime,
        endsAt: LocalDateTime
    ): Collection<Order> = orderRepository.retrieveOrders(startAt, endsAt)

    override fun retrieveOrder(number: String): Order? = orderRepository.retrieveOrder(number)

    override fun updateOrderStatus(number: String, status: OrderStatus): Order {
        val order = orderRepository.retrieveOrder(number) ?: throw DataNotFoundException("Order not found")
        return orderStatusUpdaterUseCase.execute(OrderStatusUpdater(order = order, nextStatus = status))
    }

}