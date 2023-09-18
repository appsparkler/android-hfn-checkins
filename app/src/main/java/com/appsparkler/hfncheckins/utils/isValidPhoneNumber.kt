package com.appsparkler.hfncheckins.utils



fun isValidPhoneNumber(phoneNumber: String): Boolean {
    val regex = Regex("^\\+[1-9]\\d{5,14}\$")
    return regex.matches(phoneNumber)
}