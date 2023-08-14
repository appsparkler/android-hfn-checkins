package com.example.hfncheckins.ui.components.MainScreen

import androidx.lifecycle.ViewModel
import com.example.hfncheckins.utils.isEmailValid
import com.example.hfncheckins.utils.isValidAbhyasiId
import com.example.hfncheckins.utils.isValidPhoneNumber
import com.example.hfncheckins.viewModel.InputValueType
import com.example.hfncheckins.viewModel.SeekerInfoFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(SeekerInfoFieldState())
    val uiState = _uiState.asStateFlow()

    fun update(
        batch: String? = null,
        value: String? = null
    ) {
        val type = getType()
        _uiState.update {
            it.copy(
                batch = batch ?: it.batch,
                type = type ?: it.type,
                value = value ?: it.value
            )
        }
    }

//    fun updateBatch(batch: String?) {
//        _uiState.update {
//            it.copy(
//                batch = batch
//            )
//        }
//    }

//    fun updateValue(updatedValue: String) {
//        val isValid =
//            isValidAbhyasiId(updatedValue) ||
//                    isValidPhoneNumber(updatedValue) ||
//                    isEmailValid(updatedValue)
//        _uiState.update {
//            it.copy(
//                value = updatedValue,
//                isValid = isValid
//            )
//        }
//        setType()
//    }

    fun getType(): InputValueType? {
        var type = if(isValidAbhyasiId(_uiState.value.value)) {
            InputValueType.ABHYASI_ID
        } else if (isValidPhoneNumber(_uiState.value.value)) {
            InputValueType.PHONE_NUMBER
        } else if(isEmailValid(_uiState.value.value)) {
            InputValueType.EMAIL
        } else {
            null
        }
        return type
    }

//    fun setType() {
//        var type = if(isValidAbhyasiId(_uiState.value.value)) {
//            InputValueType.ABHYASI_ID
//        } else if (isValidPhoneNumber(_uiState.value.value)) {
//            InputValueType.PHONE_NUMBER
//        } else if(isEmailValid(_uiState.value.value)) {
//            InputValueType.EMAIL
//        } else {
//            null
//        }
//        _uiState.update {
//            it.copy(
//                type = type
//            )
//        }
//    }
}