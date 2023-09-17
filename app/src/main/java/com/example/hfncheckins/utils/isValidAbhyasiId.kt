package com.example.hfncheckins.utils

fun isValidAbhyasiId(text: String): Boolean {
    val pattern = "[A-Z,a-z]{6}\\d{3}|[A-Z,a-z]\\d{8}".toRegex()
    val isValid = pattern.matches(text)
    return isValid
}