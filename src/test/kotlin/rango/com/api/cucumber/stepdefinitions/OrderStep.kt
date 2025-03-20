package rango.com.api.cucumber.stepdefinitions

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.jupiter.api.Assertions
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import rango.com.api.commons.Id
import rango.com.api.commons.OrderStatus
import rango.com.api.resources.jpa.OrderRepositoryJpa
import rango.com.api.resources.model.CustomerModel
import rango.com.api.resources.model.OrderItemModel
import rango.com.api.resources.model.OrderModel
import rango.com.api.resources.model.ProductModel
import java.math.BigDecimal
import java.time.LocalDateTime
import kotlin.test.assertEquals

class OrderStep(
    val orderRepositoryJpa: OrderRepositoryJpa,
    applicationContext: WebApplicationContext,
    val objectMapper: ObjectMapper,

){
    lateinit var resultActions: ResultActions

    var mockMvc: MockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build()


    @When("I send a POST request to {string} with the following body")
    fun iSendAPostRequestToWithTheFollowingBody(path: String, requestBody: String) {
        resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .accept(MediaType.APPLICATION_JSON)
        )
    }

    @Then("the response status should be {int}")
    fun theResponseStatusShouldBe(status: Int) {
        resultActions.andExpect(status().`is`(status))
    }

    @Then("the response should contain")
    fun theResponseStatusShouldContain(dataTable: DataTable) {
        val responseJson: JsonNode = objectMapper.readTree(resultActions.andReturn().response.contentAsString)

        val expectedMap = dataTable.asMaps(String::class.java, String::class.java).first()
        val normalizedMap = expectedMap.mapValues { (_, value) -> value.toIntOrNull() ?: value }
        val expectedJson: JsonNode = objectMapper.valueToTree(normalizedMap)

        assertEquals(expectedJson, responseJson)
    }

    @Then("the response should contain the order ID")
    fun theResponseShouldContainTheOrderID() {
        val responseBody = resultActions.andReturn().response.contentAsString
        val response: JsonNode = objectMapper.readTree(responseBody)
        Assertions.assertNotNull(response.get("orderId"), "Order ID not found in the response")
    }

    @Then("the database should have the {string} with the following data")
    fun theDatabaseShouldHaveTheWithTheFollowingData(tableName: String, dataTable: DataTable) {
        val expectedOrder = transformOrderItemModel(dataTable)
        val orders = orderRepositoryJpa.findAll()

        val firstOrder = orders.first()

        assertEquals(expectedOrder.customer.name, firstOrder.customer.name)
        assertEquals(expectedOrder.customer.address, firstOrder.customer.address)
    }

    private fun transformOrderItemModel(dataTable: DataTable): OrderModel {
        val row = dataTable.asMaps(String::class.java, String::class.java).firstOrNull()
            ?: error("No order data provided")

        val orderNumber = row["number"] ?: Id.random()
        val customer = CustomerModel(
            name = row["customer.name"] ?: "any customer name",
            address = row["customer.address"] ?: " any address"
        )

        val items = listOf(
            OrderItemModel(
                product = ProductModel(
                    number = row["item[0].product.number"] ?: ("Product number is missing"),
                    name = row["item[0].product.name"] ?: ("Product name is missing"),
                    price = row["item[0].product.price"]?.toBigDecimal() ?: BigDecimal.ZERO,
                    description = row["item[0].product.description"] ?: ("Product description is missing")
                ),
                quantity = row["item[0].quantity"]?.toInt() ?: 1
            )
        )

        val createdAt = row["createdAt"]?.let { LocalDateTime.parse(it) } ?: LocalDateTime.now()
        val status = row["status"]?.let { OrderStatus.valueOf(it) } ?: OrderStatus.OPEN

        return OrderModel(
            number = orderNumber,
            items = items,
            customer = customer,
            createdAt = createdAt,
            status = status
        )
    }

}