package com.appsparkler.gsm.model

data class ManualEntryUser(
  val name: String = "",
  val mobileNo: String = "",
  val email: String = "",
  val organization: String = "",
  val checkinTime: Long = System.currentTimeMillis()
)