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
import rango.com.api.resources.jpa.OrderRepositoryJpa
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

    @Then("the response should contain the order ID")
    fun theResponseShouldContainTheOrderID() {
        val responseBody = resultActions.andReturn().response.contentAsString
        val response: JsonNode = objectMapper.readTree(responseBody)
        Assertions.assertNotNull(response.get("orderId"), "Order ID not found in the response")
    }

    @Then("the database should have the {string} with the following data")
    fun theDatabaseShouldHaveTheWithTheFollowingData(tableName: String, dataTable: DataTable) {
        val expectedData = dataTable.asMaps()
        val orders = orderRepositoryJpa.findAll()

        val firstOrder = orders.first()
        val expectedOrder = expectedData.first()

        assertEquals(expectedOrder["customer.name"], firstOrder.customer.name)
        assertEquals(expectedOrder["customer.address"], firstOrder.customer.address)
        //assertEquals(expectedOrder["products[0].number"], firstOrder.products[0].number)
    }

}