package rango.com.api.cucumber.stepdefinitions

import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import rango.com.api.cucumber.stepdefinitions.transformer.ProductModelTransformer
import rango.com.api.resources.jpa.ProductRepositoryJpa
import rango.com.api.resources.model.ProductModel

class DatabaseStep(
    val productRepositoryJpa: ProductRepositoryJpa
) {

    @And("database should have the {string} with the following data")
    fun databaseShouldHaveTheProduct(document: String, dataTable: DataTable) {
        val products: List<ProductModel> = dataTable.asMaps().map { row -> ProductModelTransformer().productModelTransformer(row) }

        val first = productRepositoryJpa.findAll().first()

        assertEquals(first.name, products.first().name)
        assertEquals(first.description, products.first().description)
        assertEquals(first.price, products.first().price)
    }

}