package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.lifecycle.ViewModel
import com.appsparkler.hfncheckins.model.HFNEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class EventsViewModelState(
  val ongoingEvents: List<HFNEvent>? = null
)

class EventsViewModel : ViewModel() {
  var _uiState = MutableStateFlow(EventsViewModelState())
  val uiState = _uiState.value

  fun setOngoingEvents(hfnEvent: List<HFNEvent>) {
    _uiState.update {
      uiState.copy(
        ongoingEvents = hfnEvent
      )
    }
  }
}