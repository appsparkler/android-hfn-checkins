package com.example.hfncheckins.model

data class QRCodeCheckin(
    val eventName: String,
    val regId: String,
    val abhyasiId: String,
    val batch: String,
    val orderId: String,
    val pnr: String,
    val fullName: String,
    val dormPreference: String,
    val berthPreference: String,
    val dormAndBerthAllocation: String,
    val timestamp: Long,
    val checkin: Boolean
)