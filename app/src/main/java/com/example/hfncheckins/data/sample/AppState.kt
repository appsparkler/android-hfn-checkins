package com.example.hfncheckins.data.sample

import com.example.hfncheckins.model.HFNEvent
import com.example.hfncheckins.viewModel.AbhyasiIdCheckin

data class AppState(
    val event: HFNEvent,
    val abhyasiIdCheckin: AbhyasiIdCheckin? = null
)
