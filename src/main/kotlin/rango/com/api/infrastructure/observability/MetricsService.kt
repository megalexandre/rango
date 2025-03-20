package rango.com.api.infrastructure.observability

import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Service

@Service
class MetricsService(private val meterRegistry: MeterRegistry) {

    fun increment(metricType: MetricType) {
        val counter = meterRegistry.counter(metricType.metricName)
        counter.increment()
    }

    fun sum(metricType: MetricType, value: Double) {
        val summary = meterRegistry.summary(metricType.metricName)
        summary.record(value)
    }

}