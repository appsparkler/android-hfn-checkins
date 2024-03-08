package com.appsparkler.gsm.features.SuccessScreen

import com.appsparkler.gsm.model.ManualEntryUser
import com.appsparkler.gsm.model.QRUser

class SuccessScreenModel {
  var qrUser: QRUser = QRUser()
  var manualEntryUser: ManualEntryUser = ManualEntryUser()

  fun setQRUser(user: QRUser) {
    qrUser = user
  }

  fun setManualEntryUser(user: ManualEntryUser) {
    manualEntryUser = user
  }

  fun reset() {
    qrUser = QRUser()
    manualEntryUser = ManualEntryUser()
  }
}