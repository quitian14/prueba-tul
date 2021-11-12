package org.quitian14.tul.technicaltest.types

enum class ShoppingCartState {
    PENDING, COMPLETE;

    companion object {
        fun isValidType(type: String): Boolean = values().any { it.name == type.toUpperCase() }
    }
}