package org.quitian14.tul.technicaltest.model.responses

data class ErrorResponse(
    val status: Int? = 500,
    val message: String? = "Exception"
)
