package org.quitian14.tul.technicaltest.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class LoginRequest(
    @NotBlank
    @NotNull
    val user: String,

    @NotBlank
    @NotNull
    val password: String,
)
