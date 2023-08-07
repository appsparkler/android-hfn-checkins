package com.example.hfncheckins.ui.components.QRCheckinScreen

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.viewModel.QRCodeCheckinInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QRCheckinScreenViewModel:ViewModel() {
    val _uiState = MutableStateFlow(listOf<QRCodeCheckinInfo>())
    val uiState = _uiState.asStateFlow()

    fun isValid():Boolean {
        return uiState.value.any { it.checkin }
    }

    fun setupList(qrCheckinItems: List<QRCodeCheckinInfo>){
        _uiState.update {
            qrCheckinItems
        }
    }

    fun update(
        updatedQRCheckinItem: QRCodeCheckinInfo,
    ) {
        val qrCheckins = _uiState.value.map { qrCheckinItem ->
                        if (updatedQRCheckinItem.regId === qrCheckinItem.regId) updatedQRCheckinItem
                        else qrCheckinItem
                    }
        _uiState.update {
            qrCheckins
        }
    }
}