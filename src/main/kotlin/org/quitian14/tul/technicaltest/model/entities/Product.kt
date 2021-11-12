package org.quitian14.tul.technicaltest.model.entities

import com.fasterxml.jackson.annotation.JsonFormat
import org.quitian14.tul.technicaltest.types.ProductType
import java.io.Serializable
import java.util.*

data class Product(
    var id: String? = UUID.randomUUID().toString(),

    var sku: String = "",

    var description: String = "",

    var name: String = "",

    var price: Double = 0.0,

    var type: ProductType = ProductType.SIMPLE,

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: Date? = Date(),

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updatedAt: Date? = null
) : Serializable {

    fun getRealPrice() = if (type == ProductType.DISCOUNT) price / 2 else price

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}
