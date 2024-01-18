package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.lifecycle.ViewModel
import com.appsparkler.hfncheckins.utils.isEmailValid
import com.appsparkler.hfncheckins.utils.isValidAbhyasiId
import com.appsparkler.hfncheckins.utils.isValidPhoneNumber
import com.appsparkler.hfncheckins.model.InputValueType
import com.appsparkler.hfncheckins.model.SeekerInfoFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel : ViewModel() {
  private val _uiState = MutableStateFlow(SeekerInfoFieldState())
  val uiState = _uiState.asStateFlow()

  fun reset() {
    _uiState.update {
      SeekerInfoFieldState(
        batch = uiState.value.batch
      )
    }
  }

  fun update(
    batch: String? = null,
    value: String? = null
  ) {
    val type = getType(value)
    val isValid = if (value != null) {
      isValidAbhyasiId(value) ||
              isValidPhoneNumber(value) ||
              isEmailValid(value)
    } else {
      _uiState.value.isValid
    }
    _uiState.update {
      it.copy(
        batch = batch ?: it.batch,
        type = type ?: it.type,
        value = value ?: it.value,
        isValid = isValid
      )
    }
  }

  fun getType(value: String?): InputValueType? {
    val type = if(value == null){
      null
    } else if(isValidAbhyasiId(value)) {
      InputValueType.ABHYASI_ID
    } else if (isValidPhoneNumber(value)) {
      InputValueType.PHONE_NUMBER
    } else if (isEmailValid(value)) {
      InputValueType.EMAIL
    } else {
      null
    }
    return type
  }
}