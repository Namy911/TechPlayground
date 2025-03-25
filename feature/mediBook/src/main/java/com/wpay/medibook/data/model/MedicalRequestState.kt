package com.wpay.medibook.data.model

data class MedicalRequestState(
    val consultExpandForm: Boolean = false,
    val prescriptExpandForm: Boolean = false,
    val date: String = "",
    val specialist: String = "",
    val medicalCenter: String = "",
)