package rango.com.api.resources.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import rango.com.api.commons.OrderStatus
import java.time.LocalDateTime

@Document(collection = "order")
data class OrderModel(

    @Id
    val number: String,
    val items: Collection<OrderItemModel>,
    val customer: CustomerModel,
    val createdAt: LocalDateTime,
    val status: OrderStatus,

)

data class OrderItemModel(
    val product: ProductModel,
    val quantity: Int
)

data class CustomerModel(

    val name: String,
    val address: String,

)