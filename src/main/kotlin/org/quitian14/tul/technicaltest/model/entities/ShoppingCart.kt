package org.quitian14.tul.technicaltest.model.entities

import com.fasterxml.jackson.annotation.JsonFormat
import org.quitian14.tul.technicaltest.types.ShoppingCartState
import java.io.Serializable
import java.util.*

data class ShoppingCart(
    val id: String? = UUID.randomUUID().toString(),

    var state: ShoppingCartState? = ShoppingCartState.PENDING,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: Date? = Date(),

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: Date? = null,

    var items: MutableList<ShoppingCartItem> = mutableListOf()
) : Serializable {
    fun getTotal() = items.sumByDouble { it.getTotal() }

    fun removeItem(productId: String): ShoppingCartItem? {
        val itemToDelete = getItem(productId)
        items.remove(itemToDelete)

        return itemToDelete
    }

    fun addItem(newItem: ShoppingCartItem): ShoppingCartItem {
        val item = getItem(newItem.product.id!!)
        return if (item!=null) {
            item.amount += newItem.amount
            item
        } else{
            items.add(newItem)
            newItem
        }
    }

    fun updateItem(productId: String, amount: Int): ShoppingCartItem? {
        val item = getItem(productId)
        if (item!=null) {
            item.amount = amount
        }

        return item
    }

    fun getItem(productId: String) = items.firstOrNull { it.product.id == productId }

}
