package com.example.hfncheckins.model

data class EmailOrMobileCheckin(
    val startWithMobile: Boolean,
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
    val isValid: Boolean
)