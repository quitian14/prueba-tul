package org.quitian14.tul.technicaltest.constants

object Routes {
    const val USER: String = "/users"
    const val LOGIN: String = "/login"
    const val PRODUCTS: String = "/products"
    const val PRODUCT_ID: String = "/{product_id}"
    const val SHOPPING_CARTS: String = "/shopping-carts"
    const val FIND_BY_ID: String = "/{shopping_cart_id}"
    const val ADD_PRODUCTS: String = "/{shopping_cart_id}/$PRODUCTS"
    const val REMOVE_PRODUCT: String = "/{shopping_cart_id}/$PRODUCTS/$PRODUCT_ID"
    const val UPDATE_PRODUCT: String = "/{shopping_cart_id}/$PRODUCTS/$PRODUCT_ID"
    const val CHECKOUT_SHOPPING_CART: String = "/{shopping_cart_id}/checkout"
}
