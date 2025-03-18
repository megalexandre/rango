package rango.com.api.infrastructure.configuration.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.javamoney.moneta.Money
import java.text.NumberFormat
import java.util.*
import javax.money.Monetary

class MoneySerializer : JsonSerializer<Money>() {
    override fun serialize(value: Money, gen: JsonGenerator, serializers: SerializerProvider) {
        val currencyCode = value.currency.currencyCode
        val locale = when (currencyCode) {
            "USD" -> Locale.forLanguageTag("en-US")
            else -> Locale.forLanguageTag("pt-BR")
        }

        val format = NumberFormat.getCurrencyInstance(locale)
        val formattedValue = format.format(value.number.toDouble())

        gen.writeStartObject()
        gen.writeStringField("readable_value", formattedValue)
        gen.writeStringField("value", value.number.toString())

        gen.writeEndObject()
    }
}

class MoneyDeserializer : StdDeserializer<Money>(Money::class.java) {
    override fun deserialize(p: com.fasterxml.jackson.core.JsonParser, ctxt: com.fasterxml.jackson.databind.DeserializationContext): Money {
        val node = p.codec.readTree<com.fasterxml.jackson.databind.JsonNode>(p)
        val amount = node.get("amount").asText()
        val currency = node.get("currency").asText()
        return Money.of(amount.toBigDecimal(), Monetary.getCurrency(currency))
    }
}