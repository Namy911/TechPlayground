package com.wpay.medibook.data.model

data class MedicalRequestState(
    val consultExpandForm: Boolean = false,
    val prescriptExpandForm: Boolean = false,
    val consultDate: String = "",
    val prescriptDate: String = "",
    val prescriptSpecialist: String = "",
    val consultSpecialist: String = "",
    val medicalCenter: String = "",
)