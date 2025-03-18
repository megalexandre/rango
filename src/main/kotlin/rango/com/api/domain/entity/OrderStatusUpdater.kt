package rango.com.api.domain.entity

import rango.com.api.commons.OrderStatus

class OrderStatusUpdater(
    val order: Order,
    val nextStatus: OrderStatus,
){

    val canUpdate: Boolean
        get() = order.status != nextStatus &&  nextStatus.value > order.status.value

    val cannotUpdate: Boolean
        get() = canUpdate.not()

}