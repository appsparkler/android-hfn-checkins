package com.example.hfncheckins.ui.components.QRCheckinScreen

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.viewModel.QRCodeCheckin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QRCheckinScreenViewModel:ViewModel() {
    val _uiState = MutableStateFlow(listOf<QRCodeCheckin>())
    val uiState = _uiState.asStateFlow()

    fun isValid():Boolean {
        return uiState.value.any { it.checkin }
    }

    fun setupList(qrCheckinItems: List<QRCodeCheckin>){
        _uiState.update {
            qrCheckinItems
        }
    }

    fun getCheckedInItems():List<QRCodeCheckin>{
        return uiState.value.filter { it.checkin }
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
}