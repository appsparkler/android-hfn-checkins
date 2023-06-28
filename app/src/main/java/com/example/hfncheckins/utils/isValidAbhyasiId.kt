package com.example.hfncheckins.utils

fun isValidAbhyasiId(text: String): Boolean {
    val pattern = "[A-Z,a-z]{6}\\d{3}".toRegex()
    return pattern.matches(text)
}