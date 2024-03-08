package com.appsparkler.gsm.features.SuccessScreen

import com.appsparkler.gsm.model.ManualEntryUser
import com.appsparkler.gsm.model.QRUser

class SuccessScreenModel {
  var qrUser: QRUser? = null
  var manualEntryUser: ManualEntryUser? = null

  fun setQRUser(user: QRUser?) {
    qrUser = user
  }

  fun setAManualEntryUser(user: ManualEntryUser?) {
    manualEntryUser = user
  }

  fun reset() {
    qrUser = null
    manualEntryUser = null
  }
}