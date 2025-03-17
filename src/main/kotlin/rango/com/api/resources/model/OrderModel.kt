package rango.com.api.resources.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import rango.com.api.commons.OrderStatus
import java.time.LocalDateTime

@Document(collection = "order")
data class OrderModel(

    @Id
    val number: String,
    val products: Collection<ProductModel>,
    val customer: CustomerModel,
    val createdAt: LocalDateTime,
    val status: OrderStatus,

)


data class CustomerModel(

    val name: String,
    val address: String,

)