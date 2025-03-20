package rango.com.api.infrastructure.observability

enum class MetricType(val metricName: String) {
    ORDERS_CREATED("ORDERS_CREATED"),
    ORDERS_REQUESTED_VALUE("ORDERS_REQUESTED_VALUE"),
}