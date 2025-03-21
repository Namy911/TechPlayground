package com.wpay.medibook.data.dto

data class ConsultationRequest (
    val id: Long = 0,
    val date: String,
    val specialist: String,
    val userId: Long,
)