package org.quitian14.tul.technicaltest.model.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Positive

data class AddProductRequest(
    @field:Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
    @field:NotBlank
    val productId: String,

    @field:Positive
    val amount: Int,
)
