package com.example.hfncheckins.utils

fun isEmailValid(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    return emailRegex.matches(email)
}
