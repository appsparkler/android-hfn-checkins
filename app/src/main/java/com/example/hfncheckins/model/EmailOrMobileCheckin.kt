package com.example.hfncheckins.model

data class EmailOrMobileCheckin(
    val startWithMobile: Boolean,
    val batch: String? = null,
    val email: String,
    val mobile: String,
    val fullName: String,
    val ageGroup: String,
    val gender: String,
    val city: String,
    val state: String,
    val country: String,
    val dormOrBerthAllocation: String,
    val timestamp: Long,
    val isValid: Boolean,
    val type: String,
)

data class MobileOrEmailCheckinDBModel(
    val timestamp: Long,
    val type: String,
    val batch: String,
    val email: String,
    val fullName: String,
    val ageGroup: String,
    val mobile: String,
    val dormOrBerthAllocation: String,
    val gender: String,
    val city: String,
    val state: String,
    val country: String
)