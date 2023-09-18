package com.appsparkler.hfncheckins.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun getDefaultBatch(): String {
  val now = LocalDate.now()
  val september26 = LocalDate.of(now.year, 9, 26)

  return if (now <= september26) {
    "batch-1"
  } else {
    "batch-2"
  }
}
