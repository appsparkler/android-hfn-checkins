package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.lifecycle.ViewModel
import com.appsparkler.hfncheckins.model.HFNEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class EventsViewModelState(
  val ongoingEvents: List<HFNEvent>? = null,
  val selectedEvent: HFNEvent? = null
)

class EventsViewModel : ViewModel() {
  private var _uiState = MutableStateFlow(EventsViewModelState())
  val uiState = _uiState.asStateFlow()

  fun setOngoingEvents(hfnEvent: List<HFNEvent>) {
    _uiState.update {
      uiState.value.copy(
        ongoingEvents = hfnEvent
      )
    }
  }

  fun setSelectedEvent(hfnEvent: HFNEvent) {
    _uiState.update {
      uiState.value.copy(
        selectedEvent = hfnEvent
      )
    }
  }
}