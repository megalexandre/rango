package rango.com.api.application.order.out

import rango.com.api.domain.entity.Order

class OrderCreateResponse (val number: String)

fun Order.toCreateResponse() = OrderCreateResponse(this.number)