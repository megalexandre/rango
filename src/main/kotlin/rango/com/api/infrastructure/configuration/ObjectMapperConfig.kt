package rango.com.api.infrastructure.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.javamoney.moneta.Money
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import rango.com.api.infrastructure.configuration.serialization.MoneyDeserializer
import rango.com.api.infrastructure.configuration.serialization.MoneySerializer

@Configuration
class ObjectMapperConfig {

    @Bean
    fun objectMapper(): ObjectMapper =
        ObjectMapper().apply {
            registerModule(JavaTimeModule())
            registerModule(KotlinModule.Builder().build())
            disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE

            registerModule(
                SimpleModule().apply {
                    addSerializer(Money::class.java, MoneySerializer())
                    addDeserializer(Money::class.java, MoneyDeserializer())
                }
            )
        }

}