package com.appsparkler.hfncheckins.model

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
    val checkin: Boolean,
    val type: String,
)

data class QRCodeCheckinDBModel(
    val eventName: String,
    val orderId: String,
    val pnr: String,

//    val batch: String,
    val abhyasiId: String,
    val regId: String,
    val fullName: String,
    val berthPreference: String,
    val dormPreference: String,

    val dormAndBerthAllocation: String,
    val timestamp: Long,
    val type: String,
)