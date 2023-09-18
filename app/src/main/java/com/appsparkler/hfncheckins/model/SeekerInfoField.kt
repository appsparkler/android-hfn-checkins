package com.appsparkler.hfncheckins.model

data class SeekerInfoFieldState(
    val value: String = "",
    val isValid:Boolean = false,
    val type: InputValueType? = null,
    val batch: String? = null
)

enum class InputValueType {
    ABHYASI_ID,
    PHONE_NUMBER,
    EMAIL
}

