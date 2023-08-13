package com.example.hfncheckins.viewModel

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.utils.isEmailValid
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.utils.isValidPhoneNumber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SeekerInfoFieldState(
    val value: String = "",
    val isValid:Boolean = false,
    val type: InputValueType? = null,
    val batch: String? = null
)

enum class InputValueType {
    ABHYASI_ID,
    PHONE_NUMBER,
    EMAIL
}
class SeekerInfoFieldViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(SeekerInfoFieldState())
    val uiState = _uiState.asStateFlow()

    fun updateBatch(batch: String?) {
        _uiState.update {
            it.copy(
                batch = batch
            )
        }
    }

    fun updateValue(updatedValue: String) {
        val isValid =
            isValidAbhyasiId(updatedValue) ||
                    isValidPhoneNumber(updatedValue) ||
                    isEmailValid(updatedValue)
        _uiState.update {
            it.copy(
                value = updatedValue,
                isValid = isValid
            )
        }
        setType()
    }

    fun setType() {
        var type = if(isValidAbhyasiId(_uiState.value.value)) {
            InputValueType.ABHYASI_ID
        } else if (isValidPhoneNumber(_uiState.value.value)) {
            InputValueType.PHONE_NUMBER
        } else if(isEmailValid(_uiState.value.value)) {
            InputValueType.EMAIL
        } else {
            null
        }
        _uiState.update {
            it.copy(
                type = type
            )
        }
    }
}

