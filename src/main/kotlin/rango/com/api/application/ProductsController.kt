package rango.com.api.application

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import rango.com.api.application.products.`in`.ProductCreateRequest
import rango.com.api.application.products.out.ProductResponse
import rango.com.api.application.products.out.toResponse
import rango.com.api.domain.service.ProductService
import java.net.URI

@RestController
@RequestMapping("/products")
class ProductsController(
    private val productService: ProductService,
    private val meterRegistry: MeterRegistry
) {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    private var counter: Counter = Counter.builder("created_product").
        register(meterRegistry);

    @GetMapping
    fun getProducts() = productService.getAllProducts().also { log.info("getting all products") }

    @PostMapping
    fun saveProducts(@RequestBody request: ProductCreateRequest): ResponseEntity<ProductResponse> {
        log.info("creating product request $request")

        val response = productService.save(request.toEntity()).toResponse()
        log.info("created product $response")
        counter.increment()

        return ResponseEntity.created(URI.create("/products")).body(response)
    }


}