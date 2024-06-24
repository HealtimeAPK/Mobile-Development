package com.id.masel.healtime.data.model

import com.squareup.moshi.Json


data class RegisterResponse(
    @field:Json(name = "error")
    val email: Boolean,

    @field:Json(name = "message")
    val message: String
)