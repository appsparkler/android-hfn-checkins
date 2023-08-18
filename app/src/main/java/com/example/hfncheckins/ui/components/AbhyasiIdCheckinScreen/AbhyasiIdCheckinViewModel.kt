package com.example.hfncheckins.ui.components.AbhyasiIdCheckinScreen

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.model.AbhyasiIdCheckin
import com.example.hfncheckins.model.CheckinType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AbhyasiIdCheckinViewModel : ViewModel() {
  val _uiState = MutableStateFlow(
    AbhyasiIdCheckin(
      abhyasiId = "",
      dormAndBerthAllocation = "",
      timestamp = 0,
      type = CheckinType.ABHYASI_ID.name
    )
  )

  val uiState = _uiState.asStateFlow()

  fun update(
    abhyasiId: String? = null,
    dormAndBerthAllocation: String? = null,
    batch: String? = null
  ) {
    _uiState.update {
      it.copy(
        abhyasiId = abhyasiId ?: _uiState.value.abhyasiId,
        dormAndBerthAllocation = dormAndBerthAllocation ?: _uiState.value.dormAndBerthAllocation,
        timestamp = System.currentTimeMillis(),
        batch = batch
      )
    }
  }

}