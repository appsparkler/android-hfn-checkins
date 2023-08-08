package com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.viewModel.AbhyasiIdCheckin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AbhyasiIdCheckinViewModel:ViewModel() {
    val _uiState = MutableStateFlow(AbhyasiIdCheckin(
        abhyasiId = "",
        dormAndBerthAllocation = "",
        timestamp = 0
    ))

    val uiState = _uiState.asStateFlow()

    fun update(
        abhyasiId: String? = null,
        dormAndBerthAllocation: String? = null
    ) {
        _uiState.update {
            it.copy(
                abhyasiId = abhyasiId ?: _uiState.value.abhyasiId,
                dormAndBerthAllocation = dormAndBerthAllocation ?: _uiState.value.dormAndBerthAllocation,
                timestamp = System.currentTimeMillis()
            )
        }
    }

}