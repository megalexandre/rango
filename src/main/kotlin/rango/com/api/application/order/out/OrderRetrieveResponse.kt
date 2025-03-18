package rango.com.api.application.order.out

import org.javamoney.moneta.Money
import rango.com.api.application.products.out.ProductRetrieveResponse
import rango.com.api.commons.OrderStatus
import rango.com.api.commons.functions.toMoney
import rango.com.api.domain.entity.Customer
import rango.com.api.domain.entity.Order
import rango.com.api.domain.entity.OrderItem
import rango.com.api.domain.entity.Product
import java.time.LocalDateTime

class OrderRetrieveResponse(
    val number: String,
    val items: Collection<OrderItemRetrieveResponse>,
    val customer: Customer,
    val createdAt: LocalDateTime,
    val status: OrderStatus,
    val numberOfItems: Int,
    val total: Money,
)

class OrderItemRetrieveResponse(
    val product: ProductRetrieveResponse,
    val quantity: Int,
)

fun OrderItem.toOrderItemRetrieveResponse() = OrderItemRetrieveResponse(
    product = this.product.toProductRetrieveResponse(),
    quantity = this.quantity
)

fun Product.toProductRetrieveResponse() = ProductRetrieveResponse(
    number = this.number,
    name = this.name,
    description = this.description,
    price = this.price.toMoney()
)

fun Order.toOrderItemRetrieveResponse() = OrderRetrieveResponse(
    number = this.number,
    items = this.items.map { it.toOrderItemRetrieveResponse() },
    customer = this.customer,
    createdAt = this.createdAt,
    status = this.status,
    numberOfItems = this.numberOfItems,
    total = this.total.toMoney()
)