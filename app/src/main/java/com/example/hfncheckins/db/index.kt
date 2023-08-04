package com.example.hfncheckins.db

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.firestore.ktx.memoryCacheSettings
import com.google.firebase.firestore.ktx.persistentCacheSettings
import com.google.firebase.ktx.Firebase

internal val settings = firestoreSettings {
    // Use memory cache
    setLocalCacheSettings(memoryCacheSettings {})
    // Use persistent disk cache (default)
    setLocalCacheSettings(persistentCacheSettings {})
}

val getDb:() -> FirebaseFirestore = {
    val db = Firebase.firestore
    db.firestoreSettings = settings
    db
}


