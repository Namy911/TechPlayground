package com.wpay.medibook.data.dto

data class PrescriptionRequest (
    val id: Long = 0,
    val date: String,
    val specialist: String,
    val medicalCenter: String,
    val userId: Long,
)