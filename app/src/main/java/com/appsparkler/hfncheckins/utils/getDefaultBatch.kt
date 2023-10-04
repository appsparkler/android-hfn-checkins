package com.appsparkler.hfncheckins.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
fun getDefaultBatch(): String {
  val now = LocalDate.now()
  val october06 = LocalDate.of(now.year, 10, 6) // fri
  val october07 = LocalDate.of(now.year, 10, 7) // sat
  val october08 = LocalDate.of(now.year, 10, 8) // sun

  return if (now <= october06) {
    "checkin"
  } else if(now <= october07){
    "day 1"
  } else if(now <= october08) {
    "day 2"
  } else { // october 8 onwards
    "day 3"
  }

}
