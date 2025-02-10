package com.wpay.test_dagger.data.model

import com.wpay.userstatistics.model.entity.User
import kotlinx.serialization.Serializable

@Serializable
data class User(
    var id: Int,
    val name: String,
    val email: String
){
    fun toUserEntity(): User {
        return User(name = name,  email = email)
    }

    fun toUserDto(): User {
        return User(id, name, email)
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email')"
    }
}