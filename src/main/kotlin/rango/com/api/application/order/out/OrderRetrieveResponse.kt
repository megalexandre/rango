package rango.com.api.application.order.out

import org.javamoney.moneta.Money
import rango.com.api.commons.OrderStatus
import rango.com.api.domain.entity.Customer
import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.Product
import java.time.LocalDateTime
import javax.money.Monetary

class OrderRetrieveResponse(
    val number: String,
    val products: Collection<Product>,
    val customer: Customer,
    val createdAt: LocalDateTime,
    val status: OrderStatus,
    val numberOfItems: Int,
    val total: Money,
)


fun Order.toRetrieveResponse() = OrderRetrieveResponse(
    number = this.number,
    products = this.products,
    customer = this.customer,
    createdAt = this.createdAt,
    status = this.status,
    numberOfItems = this.numberOfItems,
    total = Money.of(this.total, Monetary.getCurrency("BRL") )
)