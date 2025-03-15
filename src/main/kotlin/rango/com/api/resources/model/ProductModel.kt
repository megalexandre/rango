package rango.com.api.resources.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document(collection = "product")
data class ProductModel(

    @Id
    val number: String,
    val name: String,
    val price: BigDecimal,
    val description: String,

)