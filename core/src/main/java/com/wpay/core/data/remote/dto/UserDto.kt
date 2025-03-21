package com.wpay.core.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
)