package com.appsparkler.hfncheckins.model

data class HFNEvent(
    val id: String,
    val title: String,
    val batches: List<String>? = null,
    val defaultBatch: String? = null
)