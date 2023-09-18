package com.appsparkler.hfncheckins.ui.components.CheckinWithEmailOrMobileScreen

import androidx.lifecycle.ViewModel
import com.appsparkler.hfncheckins.model.CheckinType
import com.appsparkler.hfncheckins.model.EmailOrMobileCheckin
import com.appsparkler.hfncheckins.utils.isEmailValid
import com.appsparkler.hfncheckins.utils.isValidPhoneNumber
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CheckinWithMobileOrEmailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        EmailOrMobileCheckin(
            timestamp = System.currentTimeMillis(),
            email = "",
            gender = "",
            fullName = "",
            ageGroup = "",
            mobile = "",
            dormOrBerthAllocation = "",
            city = "",
            state = "",
            country = "",
            isValid = false,
            startWithMobile = true,
            batch = null,
            type = CheckinType.MOBILE_OR_EMAIL.name,
            event = ""
        )
    )
    val uiState = _uiState.asStateFlow()

    fun validate() {
        val isFullNameValid = uiState.value.fullName.isNotBlank()
        val isAgeValid = uiState.value.ageGroup.isNotBlank()
        val isGenderValid = uiState.value.gender.isNotBlank()
        val isCityValid = uiState.value.city.isNotBlank()
        val isStateValid = uiState.value.state.isNotBlank()
        val isCountryValid = uiState.value.country.isNotBlank()
        val isMobileValid =
            uiState.value.mobile.isEmpty() || isValidPhoneNumber(uiState.value.mobile)
        val isEmailValid = uiState.value.email.isEmpty() || isEmailValid(uiState.value.email)
        val isValid =
            isFullNameValid && isAgeValid && isGenderValid && isCityValid && isStateValid && isCountryValid && isMobileValid && isEmailValid
        _uiState.update {
            it.copy(
                isValid = isValid
            )
        }
    }

    fun update(
        batch: String? = null,
        email: String? = null,
        gender: String? = null,
        fullName: String? = null,
        ageGroup: String? = null,
        mobile: String? = null,
        dormOrBerthAllocation: String? = null,
        city: String? = null,
        state: String? = null,
        country: String? = null,
        isValid: Boolean? = null,
        startWithMobile: Boolean? = null,
        event: String? = null
    ) {
        val currentState = _uiState.value
        _uiState.update {
            it.copy(
                email = email ?: currentState.email,
                gender = gender ?: currentState.gender,
                fullName = fullName ?: currentState.fullName,
                ageGroup = ageGroup ?: currentState.ageGroup,
                mobile = mobile ?: currentState.mobile,
                dormOrBerthAllocation = dormOrBerthAllocation
                    ?: currentState.dormOrBerthAllocation,
                city = city ?: currentState.city,
                state = state ?: currentState.state,
                country = country ?: currentState.country,
                isValid = isValid ?: currentState.isValid,
                startWithMobile = startWithMobile ?: currentState.startWithMobile,
                batch = batch ?: currentState.batch,
                event = event ?: currentState.event,
            )
        }
        validate()
    }
}