package org.quitian14.tul.technicaltest.controllers

import org.quitian14.tul.technicaltest.constants.Routes
import org.quitian14.tul.technicaltest.constants.Routes.ADD_PRODUCTS
import org.quitian14.tul.technicaltest.constants.Routes.CHECKOUT_SHOPPING_CART
import org.quitian14.tul.technicaltest.constants.Routes.FIND_BY_ID
import org.quitian14.tul.technicaltest.constants.Routes.REMOVE_PRODUCT
import org.quitian14.tul.technicaltest.constants.Routes.UPDATE_PRODUCT
import org.quitian14.tul.technicaltest.model.request.AddProductRequest
import org.quitian14.tul.technicaltest.model.request.UpdateProductRequest
import org.quitian14.tul.technicaltest.security.Secured
import org.quitian14.tul.technicaltest.services.ShoppingCartService
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
@RequestMapping(Routes.SHOPPING_CARTS)
class ShoppingCartController {

    @Autowired
    lateinit var shoppingCartService: ShoppingCartService

    @Secured(permissions = ["create"])
    @PostMapping
    fun create() = shoppingCartService.create()

    @Secured(permissions = ["create"])
    @PostMapping(ADD_PRODUCTS)
    fun addProduct(
        @RequestBody @Validated req: AddProductRequest,
        @PathVariable(name = "shopping_cart_id") shoppingCartId: String
    ) = shoppingCartService.addProduct(shoppingCartId, req.amount, req.productId)

    @Secured(permissions = ["update"])
    @PutMapping(UPDATE_PRODUCT)
    fun updateProduct(
        @RequestBody @Validated req: UpdateProductRequest,
        @PathVariable(name = "product_id") productId: String,
        @PathVariable(name = "shopping_cart_id") shoppingCartId: String
    ) = shoppingCartService.updateProduct(shoppingCartId, productId,req.amount)


    @Secured(permissions = ["delete"])
    @DeleteMapping(REMOVE_PRODUCT)
    fun delete(
        @PathVariable(name = "product_id") productId: String,
        @PathVariable(name = "shopping_cart_id") shoppingCartId: String
    ) = shoppingCartService.removeProduct(shoppingCartId, productId)


    @Secured(permissions = ["get"])
    @GetMapping(FIND_BY_ID)
    fun findById(@PathVariable(name = "shopping_cart_id") shoppingCartId: String) =
        shoppingCartService.findById(shoppingCartId)


    @Secured(permissions = ["update"])
    @PutMapping(CHECKOUT_SHOPPING_CART)
    fun checkout(@PathVariable(name = "shopping_cart_id") shoppingCartId: String) =
        shoppingCartService.completeShoppingCart(shoppingCartId)

}
