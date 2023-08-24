package com.example.hfncheckins.ui.components.QRCheckinScreen

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.model.QRCodeCheckin
import com.example.hfncheckins.model.QRCodeCheckinDBModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QRCheckinScreenViewModel : ViewModel() {
  val _uiState = MutableStateFlow(listOf<QRCodeCheckin>())
  val _more = MutableStateFlow<String>("")
  val more = _more.asStateFlow()
  val uiState = _uiState.asStateFlow()

  fun isValid(): Boolean {
    return uiState.value.any { it.checkin }
  }

  fun setupList(qrCheckinItems: List<QRCodeCheckin>) {
    _uiState.update {
      qrCheckinItems
    }
  }

  fun getCheckedInItems(): List<QRCodeCheckinDBModel> {
    return uiState
      .value
      .filter { it.checkin }
      .map {
        QRCodeCheckinDBModel(
          fullName = it.fullName,
          batch = it.batch,
          type = it.type,
          timestamp = System.currentTimeMillis(),
          dormAndBerthAllocation = it.dormAndBerthAllocation,
          orderId = it.orderId,
          berthPreference = it.berthPreference,
          dormPreference = it.dormPreference,
          eventName = it.eventName,
          pnr = it.pnr,
          abhyasiId = it.abhyasiId,
          regId = it.regId
        )
      }
  }

  fun update(
    updatedQRCheckinItem: QRCodeCheckin,
  ) {
    val qrCheckins = _uiState.value.map { qrCheckinItem ->
      if (updatedQRCheckinItem.regId == qrCheckinItem.regId) updatedQRCheckinItem
      else qrCheckinItem
    }
    _uiState.update {
      qrCheckins
    }
  }

  fun updateMore(
    value: String
  ) {
    _more.update {
      value
    }
  }
}