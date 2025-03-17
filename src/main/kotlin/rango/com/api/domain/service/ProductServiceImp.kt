package rango.com.api.domain.service

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.stereotype.Service
import rango.com.api.domain.entity.Product
import rango.com.api.domain.repository.ProductRepository

@Service
class ProductServiceImp(
    private val productRepository: ProductRepository,
    meterRegistry: MeterRegistry
): ProductService {

    private var productCreated: Counter = Counter.builder("created_product").register(meterRegistry)

    override fun getAllProducts(): List<Product> = productRepository.getAllProducts()

    override fun save(product: Product) = productRepository.save(product).also {
        productCreated.increment()
    }
}