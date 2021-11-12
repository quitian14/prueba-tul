package org.quitian14.tul.technicaltest.exceptions

data class ErrorContainer(
    val error: Error
) {
    override fun toString(): String {
        return "ErrorContainer(error=$error)"
    }
}
