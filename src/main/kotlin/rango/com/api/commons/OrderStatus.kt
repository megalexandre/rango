package rango.com.api.commons

enum class OrderStatus(val value: Int) {

    OPEN(1),
    IN_PROGRESS(2),
    SHIPPED(3),
    DELIVERED(4),
    CLOSED(6),

    CANCELLED(99),
}