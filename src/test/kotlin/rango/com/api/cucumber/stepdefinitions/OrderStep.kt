package rango.com.api.cucumber.stepdefinitions

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import rango.com.api.resources.jpa.ProductRepositoryJpa

class OrderStep(
    val productRepositoryJpa: ProductRepositoryJpa,
    applicationContext: WebApplicationContext,
    val objectMapper: ObjectMapper,

){
    lateinit var resultActions: ResultActions

    var mockMvc: MockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build()


}