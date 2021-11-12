package org.quitian14.tul.technicaltest.model.request

import java.math.BigDecimal

data class CreateProductRequest(
    val name: String,

    val description: String,

    val sku: String,

    val price: Double,

    val type: String?,
)
