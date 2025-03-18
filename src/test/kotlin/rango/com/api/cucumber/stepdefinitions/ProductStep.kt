package rango.com.api.cucumber.stepdefinitions

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import rango.com.api.cucumber.stepdefinitions.transformer.ProductModelTransformer
import rango.com.api.resources.jpa.ProductRepositoryJpa
import rango.com.api.resources.model.ProductModel
import java.util.function.Consumer

class ProductStep(
    val productRepositoryJpa: ProductRepositoryJpa,
    applicationContext: WebApplicationContext,
    val objectMapper: ObjectMapper,

){
    lateinit var resultActions: ResultActions

    var mockMvc: MockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build()

    @Given("I have the following products")
    fun iHaveProducts(dataTable: DataTable) {
        val products: List<ProductModel> = dataTable.asMaps().map { row -> ProductModelTransformer().productModelTransformer(row) }
        productRepositoryJpa.saveAll(products)
    }

    @Given("I have no products")
    fun iHaveNoProducts() {
        productRepositoryJpa.deleteAll()
    }


    @When("I fetch the product list {string}")
    fun iFetchTheProductList(path: String) {

        resultActions = mockMvc.perform(
            MockMvcRequestBuilders.get(path)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
    }

    @Then("I should see the available products")
    fun iShouldSeeTheAvailableProducts(expectedResponse: String) {

        val responseBody = resultActions.andReturn().response.contentAsString
        val response: JsonNode = objectMapper.readTree(responseBody)

        val expectedNode: JsonNode = ObjectMapper().readTree(expectedResponse)
        expectedNode
            .fieldNames()
            .forEachRemaining(
                Consumer { field: String? ->
                    val expectedValue = expectedNode.get(field)
                    val actualValue = response.get(field)

                    Assertions.assertNotNull(actualValue, "Key not found: $field")
                    Assertions.assertEquals(
                        expectedValue.asText(),
                        actualValue.asText(),
                        "Value mismatch for key: $field"
                    )
                })
    }

    @When("I send a product to {string}")
    fun iSendTheProduct(path: String, productJson: String) {
        resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson)
                .accept(MediaType.APPLICATION_JSON)
        )
    }

    @Then("I should see the keys with code {int}")
    fun iShouldSeeTheKeysWithCode(statusCode: Int, expectedResponse: String) {
        resultActions.andExpect(status().`is`(statusCode))

        val responseBody = resultActions.andReturn().response.contentAsString
        val response: JsonNode = objectMapper.readTree(responseBody)

        val expectedNode: JsonNode = ObjectMapper().readTree(expectedResponse)
        expectedNode
            .fieldNames()
            .forEachRemaining { field ->
                val actualValue = response.get(field)
                Assertions.assertNotNull(actualValue, "Key not found: $field")
            }
    }

    @And("database should have a product with the following data")
    fun databaseShouldHaveTheProduct(dataTable: DataTable) {
        val products: List<ProductModel> = dataTable.asMaps().map { row -> ProductModelTransformer().productModelTransformer(row) }

        val first = productRepositoryJpa.findAll().first()

        assertEquals(first.name, products.first().name)
        assertEquals(first.description, products.first().description)
        assertEquals(first.price, products.first().price)
    }

}