package com.appsparkler.gsm.features.HomeScreen

import androidx.lifecycle.ViewModel
import com.appsparkler.gsm.model.QRUser
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
    if (
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
    return mobileOrEmailExists && mobileIsValid() && emailIsValid()
  }

  private fun mobileIsValid(): Boolean {
    if (state.value.mobileNo.isEmpty()) return true
    val mobileNo = state.value.mobileNo
    val regex = Regex("^\\+[1-9]\\d{5,14}\$")
    return regex.matches(mobileNo)
  }

  private fun emailIsValid(): Boolean {
    if (state.value.email.isEmpty()) return true
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    return emailRegex.matches(state.value.email)
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

  fun resetState() {
    _state.value = HomeScreenState()
  }
  fun addQRRecord(scanResult: String): QRUser? {
    val qrUtils = QRUtils()
    if(qrUtils.isQRValid(scanResult)) {
      return qrUtils.getQRUser(scanResult)
    }
    return null
  }
}