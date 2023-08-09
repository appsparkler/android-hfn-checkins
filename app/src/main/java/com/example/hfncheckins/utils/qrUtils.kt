package com.example.hfncheckins.utils

import com.example.hfncheckins.model.EventOrderGeneralDetails
import com.example.hfncheckins.viewModel.QRCodeCheckin

fun isValid(value: String):Boolean {
  val refinedValue = value.replace("\n", "")
  val generalDetails = getGeneralDetails(refinedValue)
  val isValidEventTitle = generalDetails.eventTitle.isNotEmpty()
  val isValidPnr = generalDetails.pnr.matches("[A-Z]{2}-[A-Z]{4}-[A-Z]{4}".toRegex())
  val isValidOrderId = generalDetails.orderId.isNotEmpty()
  val allCheckins = getQRCheckins(refinedValue)
  val isValid = isValidEventTitle && isValidPnr && isValidOrderId && allCheckins.isNotEmpty()
  return isValid
}

fun getQRCheckins(refinedValue: String):List<QRCodeCheckin> {
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
        abhyasiId = columns[1],
        fullName = columns[2],
        dormPreference = columns[3],
        berthPreference = columns[4],
//    default value
        checkin = false,
        timestamp = 0,
        dormAndBerthAllocation = "",
//    general details
        pnr = generalDetails.pnr,
        eventName = generalDetails.eventTitle,
        orderId = generalDetails.orderId
      )
  }
  return checkins
}

fun getGeneralDetails(value: String): EventOrderGeneralDetails {
  val rows = value.split(";")
  val firstRow = rows[0]
  val columnsInFirstRow = firstRow.split("|")
  return EventOrderGeneralDetails(
    eventTitle = columnsInFirstRow[0],
    pnr = columnsInFirstRow[1],
    orderId = columnsInFirstRow[2]
  )
}