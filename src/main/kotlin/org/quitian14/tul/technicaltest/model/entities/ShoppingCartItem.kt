package org.quitian14.tul.technicaltest.model.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class ShoppingCartItem(
    val id: String = UUID.randomUUID().toString(),

    var amount: Int = 1,

    val product: Product,

    val shoppingCartId: String,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: Date? = Date(),

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: Date? = null
) {
    fun getTotal() = product.getRealPrice() * amount

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShoppingCartItem

        if (product != other.product) return false

        return true
    }

    override fun hashCode(): Int {
        return product.hashCode()
    }
}
