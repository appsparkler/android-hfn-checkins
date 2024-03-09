package com.appsparkler.gsm.model.apiService

import com.appsparkler.gsm.model.ManualEntryUser
import com.appsparkler.gsm.model.QRUser

class GSMApiService {
  fun checkinQRUser(qrUser: QRUser) {
    val db = getDb()
    db.collection("users").document(qrUser.registrationId).set(qrUser)
  }

  fun checkinManualEntryUser(manualEntryUser: ManualEntryUser) {
    val db = getDb()
    val name = manualEntryUser.name
    val mobileNo = manualEntryUser.mobileNo
    val email = manualEntryUser.email
    db.collection("users")
      .document("$name-$mobileNo-$email")
      .set(manualEntryUser)
  }
}