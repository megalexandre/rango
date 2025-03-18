package rango.com.api.commons.functions

import org.javamoney.moneta.Money
import java.math.BigDecimal
import javax.money.Monetary

fun BigDecimal.toMoney() =  Money.of(this, Monetary.getCurrency("BRL"))

