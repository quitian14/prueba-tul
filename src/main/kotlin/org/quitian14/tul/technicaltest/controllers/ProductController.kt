package org.quitian14.tul.technicaltest.controllers

import org.quitian14.tul.technicaltest.constants.Routes
import org.quitian14.tul.technicaltest.constants.Routes.PRODUCT_ID
import org.quitian14.tul.technicaltest.model.entities.Product
import org.quitian14.tul.technicaltest.model.request.CreateProductRequest
import org.quitian14.tul.technicaltest.security.Secured
import org.quitian14.tul.technicaltest.services.ProductService
import org.quitian14.tul.technicaltest.validators.ProductValidator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Routes.PRODUCTS)
class ProductController {

    @Autowired
    lateinit var productService: ProductService

    @Autowired
    private lateinit var productValidator: ProductValidator

    @Secured(permissions = ["create"])
    @PostMapping
    fun create(@RequestBody @Validated req: CreateProductRequest): Int {
        productValidator.validate(req)
        return productService.create(req)
    }

    @Secured(permissions = ["update"])
    @PutMapping(PRODUCT_ID)
    fun update(
        @RequestBody @Validated req: CreateProductRequest,
        @PathVariable(name = "product_id") productId: String,
    ): Int {
        productValidator.validate(req)
        return productService.update(req, productId)
    }

    @Secured(permissions = ["get"])
    @GetMapping(PRODUCT_ID)
    fun findById(
        @PathVariable(name = "product_id") productId: String
    ) = productService.findById(productId)


    @Secured(permissions = ["delete"])
    @DeleteMapping(PRODUCT_ID)
    fun delete(@PathVariable(name = "product_id") productId: String): Int {
        return productService.delete(productId)
    }

    @Secured(permissions = ["get"])
    @GetMapping
    fun getAll(): List<Product> {
        return productService.findAll()
    }
}
