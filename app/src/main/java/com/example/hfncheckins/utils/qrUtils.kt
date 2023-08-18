package com.example.hfncheckins.utils

import com.example.hfncheckins.model.CheckinType
import com.example.hfncheckins.model.EventOrderGeneralDetails
import com.example.hfncheckins.model.QRType
import com.example.hfncheckins.model.QRCodeCheckin

fun isQRValid(value: String):Boolean {
  val refinedValue = value.replace("\n", "")
  try {
    val generalDetails = getGeneralDetails(refinedValue)
    val isValidEventTitle = generalDetails.eventTitle.isNotEmpty()
    val isValidPnr = generalDetails.pnr.matches("[A-Z]{2}-[A-Z]{4}-[A-Z]{4}".toRegex())
    val isValidOrderId = generalDetails.orderId.isNotEmpty()
    val allCheckins = getQRCheckins(refinedValue)
    val isValid = isValidEventTitle && isValidPnr && isValidOrderId && allCheckins.isNotEmpty()
    return isValid
  } catch ( e: Exception) {
    return false
  }
}

fun getQRCheckins(value: String):List<QRCodeCheckin> {
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
        batch = columns[1],
        abhyasiId = columns[2],
        fullName = columns[3],
        dormPreference = columns.getOrNull(4) ?: "",
        berthPreference = columns.getOrNull(5) ?: "",
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
  if(qrType == QRType.PAID_ACCOMODATION){
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
  if(isPNR(columnsInFirstRow[1])){
    return QRType.PAID_ACCOMODATION
  }
  if(isPNR(columnsInFirstRow[2])){
    return QRType.OWN_ACCOMODATION
  }
  return QRType.NONE
}