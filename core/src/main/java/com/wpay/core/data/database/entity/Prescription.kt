package com.wpay.core.data.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "prescriptions",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Prescription(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val specialist: String,
    val medicalCenter: String,
    val userId: Long,
)