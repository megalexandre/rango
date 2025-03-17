package rango.com.api.resources.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import rango.com.api.domain.entity.Customer
import rango.com.api.domain.entity.Product
import java.math.BigDecimal
import java.time.LocalDateTime

@Document(collection = "Order")
data class OrderModel(

    @Id
    val number: String,
    val products: Collection<ProductModel>,
    val customer: CustomerModel,
    val createdAt: LocalDateTime,
    val status: String,

)


data class CustomerModel(

    val name: String,
    val address: String,

    )