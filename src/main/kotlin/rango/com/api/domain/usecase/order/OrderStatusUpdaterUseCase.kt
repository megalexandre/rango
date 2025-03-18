package rango.com.api.domain.usecase.order

import org.springframework.stereotype.Service
import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.OrderStatusUpdater
import rango.com.api.domain.repository.OrderRepository
import rango.com.api.infrastructure.exception.InvalidUseCaseException

@Service
class OrderStatusUpdaterUseCase(
    private val orderRepository: OrderRepository
) {

    fun execute(orderProgress: OrderStatusUpdater): Order {

        if(orderProgress.cannotUpdate){
            throw InvalidUseCaseException("Order status cannot be progressed from ${orderProgress.order.status} to ${orderProgress.nextStatus}")
        }

        return orderRepository.save(orderProgress.order.copy(status = orderProgress.nextStatus))
    }
}