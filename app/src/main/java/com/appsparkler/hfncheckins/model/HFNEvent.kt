package com.appsparkler.hfncheckins.model

import kotlinx.serialization.Serializable

@Serializable
data class HFNEvent(
    val id: String,
    val title: String,
    val batches: List<String>? = null,
    val defaultBatch: String? = null
){
  constructor() : this("", "") {
  }
}