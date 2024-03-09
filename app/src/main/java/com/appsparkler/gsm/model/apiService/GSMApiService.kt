package com.appsparkler.gsm.model.apiService

import com.appsparkler.gsm.model.ManualEntryUser
import com.appsparkler.gsm.model.QRUser

class GSMApiService {
  fun checkinQRUser(qrUser: QRUser) {
    val db = getDb()
    db.collection("events/202403_GSM/checkins")
      .document(qrUser.registrationId)
      .set(qrUser.copy(checkinTime = System.currentTimeMillis()))
  }

  fun checkinManualEntryUser(manualEntryUser: ManualEntryUser) {
    val db = getDb()
    val name = manualEntryUser.name
    val mobileNo = manualEntryUser.mobileNo
    val email = manualEntryUser.email
    db.collection("events/202403_GSM/checkins")
      .document("$name-$mobileNo-$email")
      .set(manualEntryUser.copy(checkinTime = System.currentTimeMillis()))
  }
}