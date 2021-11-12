package org.quitian14.tul.technicaltest.services

import org.quitian14.tul.technicaltest.constants.RedisKeys
import org.quitian14.tul.technicaltest.model.entities.Product
import org.quitian14.tul.technicaltest.model.request.CreateProductRequest
import org.quitian14.tul.technicaltest.repositories.ProductRepository
import org.quitian14.tul.technicaltest.types.ProductType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    fun findAll(): List<Product> {
        return productRepository.findAll()
    }

    @Cacheable(
        value = [RedisKeys.TEST_MS_VALUE],
        key = RedisKeys.PRODUCT_KEY,
        cacheManager = "expire1hour",
        unless = "#result == null"
    )    fun findById(id: String): Product? {
        return productRepository.findById(id)
    }

    fun create(req: CreateProductRequest): Int {
        return productRepository.create(mapperProduct(req))
    }

    @CacheEvict(value = [RedisKeys.TEST_MS_VALUE],
        key = RedisKeys.PRODUCT_KEY)
    fun update(req: CreateProductRequest, id: String): Int {
        return productRepository.update(mapperProduct(req, id))
    }

    fun delete(productId: String): Int {
        return productRepository.delete(productId)
    }

    private fun mapperProduct(req: CreateProductRequest, productId: String? = null): Product {
        return Product(
            id = productId,
            name = req.name,
            description = req.description,
            price = req.price,
            sku = req.sku,
            type = ProductType.valueOf(req.type!!)
        )
    }
}
