package com.appsparkler.hfncheckins.data.sample

import com.appsparkler.hfncheckins.model.HFNEvent
import com.appsparkler.hfncheckins.model.AbhyasiIdCheckin

data class AppState(
    val event: HFNEvent,
    val abhyasiIdCheckin: AbhyasiIdCheckin? = null
)
