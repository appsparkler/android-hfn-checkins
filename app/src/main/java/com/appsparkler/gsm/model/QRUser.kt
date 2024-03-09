package com.appsparkler.gsm.model

data class QRUser(
  val name: String = "",
  val eventName: String = "",
  val sessionName: String = "",
  val registrationId: String = "",
  val pnr: String = "",
  val checkinTime: Long = System.currentTimeMillis(),
  val uid: String? = null,
  val platform: String = "ANDROID"
)