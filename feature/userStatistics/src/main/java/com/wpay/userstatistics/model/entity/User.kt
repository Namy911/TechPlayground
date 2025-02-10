package com.wpay.userstatistics.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String
){

    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email')"
    }
}