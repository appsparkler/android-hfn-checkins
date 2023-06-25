package com.example.hfncheckins.viewModel

import androidx.lifecycle.ViewModel
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
        val isValid = isValidAbhyasiId(updatedValue)
        _uiState.update {
            it.copy(
                value = updatedValue,
                isValid = isValid
            )
        }
    }

    private fun isValidAbhyasiId(text: String): Boolean {
        val pattern = "[A-Z,a-z]{6}\\d{3}".toRegex()
        return pattern.matches(text)
    }

}