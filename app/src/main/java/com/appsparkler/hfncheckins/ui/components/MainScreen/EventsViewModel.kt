package com.appsparkler.hfncheckins.ui.components.MainScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.appsparkler.hfncheckins.R
import com.appsparkler.hfncheckins.db.getDb
import com.appsparkler.hfncheckins.model.HFNEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EventsManager(context:Context) {

  private val eventsKey = context.getString(R.string.ongoing_events)
  private val selectedEventKey = "selected-event"
  private val sharedPreferences = context.getSharedPreferences(context.getString(R.string.my_shared_prefs), Context.MODE_PRIVATE)

  fun setSelectedEvent(event: HFNEvent) {
    val jsonString = Json.encodeToString(event)
    with(sharedPreferences.edit()) {
      putString(selectedEventKey, jsonString)
      apply()
    }
  }

  fun getSelectedEvent(): HFNEvent {
    val selectedEvent = sharedPreferences.getString(selectedEventKey, null)
    if(!selectedEvent.isNullOrBlank()) {
      return Json.decodeFromString<HFNEvent>(selectedEvent)
    }
    throw Exception("Selected event not found")
  }

  fun getEvents():Array<HFNEvent> {
    val events  = retrieveEventsFromSharedPreferences(sharedPreferences, eventsKey)
    if(events != null) {
      return events.toTypedArray()
    } else {
      return emptyArray()
    }
  }

  fun setEvents() {
    val db = getDb()
    db.collection(eventsKey).get()
      .addOnSuccessListener {
        if(!it.isEmpty()) {
          val events = it.toObjects(HFNEvent::class.java)
          Log.d("EventsManager", "setEvents: $events")
          storeEventsInSharedPreferences(events, sharedPreferences, eventsKey)
        }
      }
  }

  private fun retrieveEventsFromSharedPreferences(sharedPreferences: SharedPreferences, eventsKey: String): List<HFNEvent>? {
    val events = sharedPreferences.getString(eventsKey, null)
    if(!events.isNullOrBlank()) {
      return Json.decodeFromString<List<HFNEvent>>(events)
    }
    return null
  }

  private fun storeEventsInSharedPreferences(events: List<HFNEvent>, sharedPreferences: SharedPreferences, eventsKey: String) {
    val jsonString = Json.encodeToString(events)
    with(sharedPreferences.edit()) {
      putString(eventsKey, jsonString)
      apply()
    }
  }
}


data class EventsViewModelState(
  val ongoingEvents: Array<HFNEvent>,
  val selectedEvent: HFNEvent? = null
) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as EventsViewModelState

    if (!ongoingEvents.contentEquals(other.ongoingEvents)) return false
    if (selectedEvent != other.selectedEvent) return false

    return true
  }

  override fun hashCode(): Int {
    var result = ongoingEvents.contentHashCode()
    result = 31 * result + selectedEvent.hashCode()
    return result
  }
}

class EventsViewModel:ViewModel() {
  private val _uiState = MutableStateFlow(
    EventsViewModelState(
      ongoingEvents = emptyArray<HFNEvent>(),
    )
  )
  val uiState = _uiState.asStateFlow()

  fun setEvents(events: Array<HFNEvent>) {
    _uiState.update {
      it.copy(
        ongoingEvents = events
      )
    }
  }

  fun setSelectedEvent(event:HFNEvent) {
    _uiState.update {
      it.copy(
        selectedEvent = event
      )
    }
  }
}