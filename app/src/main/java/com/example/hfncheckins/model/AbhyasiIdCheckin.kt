package com.example.hfncheckins.model

data class AbhyasiIdCheckin (
    val abhyasiId: String,
    val dormAndBerthAllocation: String,
    val batch: String? = null,
    val timestamp: Long? = null,
)