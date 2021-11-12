package org.quitian14.tul.technicaltest.model.dtos

import java.io.Serializable

data class ParamDto(
    val city: String? = null,

    val lat: Float? = null,

    val lng: Float? = null
) : Serializable
