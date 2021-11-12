package org.quitian14.tul.technicaltest.types

enum class ProductType {
    SIMPLE, DISCOUNT;

    companion object {
        fun isValidType(type: String): Boolean = values().any { it.name.equals(type, ignoreCase = true) }
    }
}