package org.quitian14.tul.technicaltest.model.request

import javax.validation.constraints.Positive

data class UpdateProductRequest(
    @field:Positive
    val amount: Int,
)
