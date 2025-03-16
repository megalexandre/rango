package meu.rango.com.cucumber.transformer

import io.cucumber.java.DataTableType
import rango.com.api.resources.model.ProductModel
import java.math.BigDecimal
import java.util.UUID

class ProductModelTransformer {

    @DataTableType
    fun productModelTransformer(row: Map<String, String>): ProductModel {

        val number: String = row["number"] ?: UUID.randomUUID().toString()
        val name: String = row["name"] ?: ""
        val price: BigDecimal = row["price"]?.let { BigDecimal(it) }?: BigDecimal.ZERO
        val description: String = row["description"] ?: ""

        return ProductModel(
            number = number,
            name =name,
            price = price,
            description = description
        )
    }
}