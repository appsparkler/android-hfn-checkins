package com.example.hfncheckins.model

import java.util.Date

data class Event(
    val id: String,
    val title: String,
    val description: String,
    val startDate: Date,
    val endDate: Date
)