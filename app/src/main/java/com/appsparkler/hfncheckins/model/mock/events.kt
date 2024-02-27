package com.appsparkler.hfncheckins.model.mock

import com.appsparkler.hfncheckins.model.HFNEvent

object eventsMockData {
  val data : Array<HFNEvent> = arrayOf(
    HFNEvent(
      id = "202407_chariji_maharaj",
      title = "Chariji Maharaj's 95th Birth Anniversary Celebration",
      batches = listOf(
        "batch-1",
        "batch-2",
        "batch-2, batch-1"
      )
    ),
    HFNEvent(
      id="202403_world_spirituality",
      title = "World Spirituality Mahotsav",
    )
  )
}