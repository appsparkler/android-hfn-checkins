package com.appsparkler.gsm.features.HomeScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeScreenState(
  val name: String = "",
  val mobileNo: String = "",
  val email: String = "",
  val organization: String = "",
  val checkinButtonEnabled: Boolean = false
)

class HomeScreenViewModel : ViewModel() {
  private val _state = MutableStateFlow(HomeScreenState())
  val state = _state.asStateFlow()

  fun updateName(name: String) {
    _state.value = state.value.copy(
      name = name
    )
    validateForm()
  }

  private fun validateForm() {
    if(
      mobileOrEmailExistsAndAreValid() &&
      nameIsNotEmpty()
      ) enableCheckinButton()
    else disableCheckinButton()
  }

  private fun nameIsNotEmpty(): Boolean {
    return state.value.name.isNotEmpty()
  }

  private fun mobileOrEmailExistsAndAreValid(): Boolean {
    val mobileOrEmailExists = state.value.mobileNo.isNotEmpty() || state.value.email.isNotEmpty()
    return mobileOrEmailExists
  }

  fun updateMobileNo(mobileNo: String) {
    _state.value = state.value.copy(
      mobileNo = mobileNo
    )
    validateForm()
  }

  fun updateEmail(email: String) {
    _state.value = state.value.copy(
      email = email
    )
    validateForm()
  }

  fun updateOrganization(organization: String) {
    _state.value = state.value.copy(
      organization = organization
    )
    validateForm()
  }

  fun enableCheckinButton() {
    _state.value = state.value.copy(
      checkinButtonEnabled = true
    )
  }

  fun disableCheckinButton() {
    _state.value = state.value.copy(
      checkinButtonEnabled = false
    )
  }

  fun handleCheckin() {
    TODO("Not yet implemented")
  }


}