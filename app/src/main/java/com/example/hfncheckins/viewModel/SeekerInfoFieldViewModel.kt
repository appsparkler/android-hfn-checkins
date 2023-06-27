package com.example.hfncheckins.viewModel

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.utils.isValidPhoneNumber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class SeekerInfoFieldState(
    val value: String = "",
    val isValid:Boolean = false

)

class SeekerInfoFieldViewModel:ViewModel() {
    private val _uiState = MutableStateFlow<SeekerInfoFieldState>(SeekerInfoFieldState())
    val uiState = _uiState.asStateFlow()

    fun updateValue(updatedValue: String) {
        val isValid = isValidAbhyasiId(updatedValue) || isValidPhoneNumber((updatedValue))
        _uiState.update {
            it.copy(
                value = updatedValue,
                isValid = isValid
            )
        }
    }
}