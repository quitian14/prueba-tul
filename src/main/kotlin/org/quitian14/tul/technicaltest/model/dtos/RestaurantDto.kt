package org.quitian14.tul.technicaltest.model.dtos

import java.io.Serializable

data class RestaurantDto(
    val name: String,

    val address: String,

    val lat: Float,

    val lng: Float
) : Serializable
