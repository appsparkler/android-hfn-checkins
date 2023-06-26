package com.example.hfncheckins.viewModel

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.model.HFNEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class GenderType() {
    M, F, U
}

interface QRCodeCheckinInfo {
    val regId: String
    val abhyasiId: String
    val pnr: String
    val fullName: String
    val dormPreference: String;
    val berthPreference: String;
    val dormAndBerthAllocation: String
    val timestamp: Long

}

data class AbhyasiIdCheckin (
    val abhyasiId: String,
    val dormAndBerthAllocation: String? = null,
    val timestamp: Long? = null,
    val eventName: String,
)

interface MobileOrEmailCheckin {
    val fullName: String
    val ageGroup: String
    val gender: GenderType
    val city: String
    val state: String
    val country: String
    val dormAndBerthAllocation: String
    val eventName: String
    val timestamp: Long
}

enum class CheckinTypesEnum() {
    EmailOrMobile,
    AbhyasiId,
    QRCode
}

data class AppUiState(
    val checkinType: CheckinTypesEnum? = null,

//    if abhyasi id checkin
    val abhyasiIdCheckin: AbhyasiIdCheckin? = null,

    // if email or mobile checkin
    val mobileOrEmailCheckin: MobileOrEmailCheckin? = null,

    // if QR code checkin
    val qrCodeCheckins: List<QRCodeCheckinInfo>? = null
)

class AppViewModel:ViewModel() {
    val _uiState = MutableStateFlow<AppUiState>(AppUiState())
    val uiState = _uiState.asStateFlow()

    fun startAbhyasiCheckin(abhyasiId: String, event: HFNEvent) {
        _uiState.update { _uiState.value.copy(
            checkinType = CheckinTypesEnum.AbhyasiId,
            abhyasiIdCheckin = AbhyasiIdCheckin(
                abhyasiId,
                eventName = event.title
            )
        )}
    }

}