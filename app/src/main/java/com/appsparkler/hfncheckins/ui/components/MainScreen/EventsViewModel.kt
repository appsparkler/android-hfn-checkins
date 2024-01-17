package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.lifecycle.ViewModel
import com.appsparkler.hfncheckins.db.getDb
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

  fun setupEventListener() {
    val db = getDb()
    db.collection("ongoing-events").addSnapshotListener { snapshot, e ->
      if (e != null) {
        return@addSnapshotListener
      }
      val ongoingEvents = snapshot?.toObjects(HFNEvent::class.java)
      if (ongoingEvents != null) {
        setOngoingEvents(ongoingEvents)

        if (_uiState.value.selectedEvent != null) {
          val foundSelectedEvent = ongoingEvents
            .find { it.id === _uiState.value.selectedEvent!!.id }
          if (foundSelectedEvent === null) {
            setSelectedEvent(null)
          }
        }

        if (ongoingEvents.size == 1) {
          setSelectedEvent(ongoingEvents[0])
        }
      }
    }
  }

  fun setOngoingEvents(hfnEvent: List<HFNEvent>) {
    _uiState.update {
      uiState.value.copy(
        ongoingEvents = hfnEvent
      )
    }
  }

  fun setSelectedEvent(hfnEvent: HFNEvent?) {
    _uiState.update {
      _uiState.value.copy(
        selectedEvent = hfnEvent
      )
    }
  }
}