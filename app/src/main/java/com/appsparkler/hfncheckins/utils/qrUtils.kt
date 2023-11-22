package com.appsparkler.hfncheckins.utils

import com.appsparkler.hfncheckins.model.CheckinType
import com.appsparkler.hfncheckins.model.EventOrderGeneralDetails
import com.appsparkler.hfncheckins.model.QRType
import com.appsparkler.hfncheckins.model.QRCodeCheckin

val refineQR: (String) -> String = { it.replace("\n", "") }


data class QRCheckinsAndMore(
  val checkins: List<QRCodeCheckin>,
  val more: String
)

fun getQRCheckinsAndMore(rawValue: String): QRCheckinsAndMore {
  val moreRegex =  Regex("""(\d+\s+more\.\.)""")
  val moreMatch = moreRegex.find(rawValue)
  val more = if (moreMatch!== null) {
    moreMatch.groupValues[1]
  } else {
    ""
  }
  val refinedValue = refineQR(rawValue)
  val refinedValueWithoutMore = refinedValue.replace(moreRegex, "");
  val checkins = getQRCheckins(refinedValueWithoutMore)
  return QRCheckinsAndMore(checkins = checkins, more = more)
}

fun isQRValid(value: String): Boolean {
  val refinedValue = refineQR(value)
  try {
    val generalDetails = getGeneralDetails(refinedValue)
    val isValidEventTitle = generalDetails.eventTitle.isNotEmpty()
    val isValidPnr = generalDetails.pnr.matches("[A-Z]{2}-[A-Z]{4}-[A-Z]{4}".toRegex())
    val isValidOrderId = generalDetails.orderId.isNotEmpty()
    val allCheckins = getQRCheckinsAndMore(refinedValue)
    val isValid = isValidEventTitle && isValidPnr && isValidOrderId && allCheckins.checkins.isNotEmpty()
    return isValid
  } catch (e: Exception) {
    return false
  }
}

fun getQRCheckins(value: String): List<QRCodeCheckin> {
  val refinedValue = value.replace("\n", "")
  val generalDetails = getGeneralDetails(refinedValue)
  val rows = refinedValue.split(";")
  val checkinRows = rows.subList(1, rows.size).toList()
  val checkins = checkinRows
    .filter {
      it.isNotEmpty()
    }
    .map {
      val columns = it.split("|")
      QRCodeCheckin(
//    each checkin detail
        regId = columns[0],
//        batch = columns[1],
        abhyasiId = columns[1].trim(),
        fullName = columns[2].trim(),
        dormPreference = columns.getOrNull(4)?.trim() ?: "",
        berthPreference = columns.getOrNull(5)?.trim() ?: "",
//    default value
        checkin = false,
        timestamp = 0,
        dormAndBerthAllocation = "",
//    general details
        pnr = generalDetails.pnr,
        eventName = generalDetails.eventTitle,
        orderId = generalDetails.orderId,
        type = CheckinType.QR.name
      )
    }
  return checkins
}

fun getGeneralDetails(value: String): EventOrderGeneralDetails {
  val rows = value.split(";")
  val qrType = getQRType(value)
  val firstRow = rows[0]
  val columnsInFirstRow = firstRow.split("|")
  if (qrType == QRType.PAID_ACCOMODATION) {
    return EventOrderGeneralDetails(
      eventTitle = columnsInFirstRow[0].trim(),
      pnr = columnsInFirstRow[1].trim(),
      orderId = columnsInFirstRow[2].trim()
    )
  }
  return EventOrderGeneralDetails(
    eventTitle = columnsInFirstRow[0].trim(),
    orderId = columnsInFirstRow[1].trim(),
    pnr = columnsInFirstRow[2].trim(),
  )
}

fun getQRType(code: String): QRType {
  val refinedValue = code.replace("\n", "")
  val rows = refinedValue.split(";")
  val firstRow = rows[0]
  val columnsInFirstRow = firstRow.split("|").map({
    it.trim()
  })
  val isPNR: (String) -> Boolean = {
    it.matches("[A-Z]{2}-[A-Z]{4}-[A-Z]{4}".toRegex())
  }
  if (isPNR(columnsInFirstRow[1])) {
    return QRType.PAID_ACCOMODATION
  }
  if (isPNR(columnsInFirstRow[2])) {
    return QRType.OWN_ACCOMODATION
  }
  return QRType.NONE
}