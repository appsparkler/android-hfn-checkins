package com.appsparkler.hfncheckins.ui.components.MainScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.appsparkler.hfncheckins.R
import com.appsparkler.hfncheckins.db.getDb
import com.appsparkler.hfncheckins.model.HFNEvent
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class EventsManager(context:Context) {

  private val eventsKey = context.getString(R.string.ongoing_events)
  private val sharedPreferences = context.getSharedPreferences(context.getString(R.string.my_shared_prefs), Context.MODE_PRIVATE)

  fun getEvents():List<HFNEvent>? {
    return retrieveEventsFromSharedPreferences(sharedPreferences, eventsKey)
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
    Log.d("EventsManager", "storeEventsInSharedPreferences: $jsonString")
    with(sharedPreferences.edit()) {
      putString(eventsKey, jsonString)
      apply()
    }
  }
}