package com.appsparkler.gsm.features.HomeScreen

import com.appsparkler.gsm.model.QRUser

class QRUtils {
  fun getQRUser(scanResult: String): QRUser {
    val rows = scanResult.split(";")
//    first row
    val firstRow = rows[0]
    val firstRowColumns = firstRow.split("|")
    val eventName = firstRowColumns[0].trim()
    val sessionName = firstRowColumns[1].trim()
    val pnr = firstRow.split("|")[2].trim()
//    second row
    val secondRowColumns = rows[1].split("|")
    val registrationId = secondRowColumns[0].trim()
    val name = secondRowColumns[3].trim()

    return QRUser(
      eventName = eventName,
      sessionName = sessionName,
      pnr = pnr,
      registrationId = registrationId,
      name = name,
      checkinTime = System.currentTimeMillis()
    )
  }
  fun isQRValid(scanResult: String): Boolean {
    try {
      val rows = scanResult.split(";")
      val hasRowSizeOfTwo = rows.size == 3

      //      First Row Validations
      val firstRow = rows[0]
      val firstRowColumns = firstRow.split("|")
      val firstRowHas3Columns = firstRowColumns.size == 3
      val eventName = firstRowColumns[0].trim()
      val sessionName = firstRowColumns[1].trim()
      val pnr = firstRow.split("|")[2].trim()
      val pnrMatchesPattern = Regex("[A-Z]{2}-[A-Z]{4}-[A-Z]{4}").matches(pnr)

      //  Second Row Validations
      val secondRowColumns = rows[1].split("|")
      val secondRowHas4Columns = secondRowColumns.size == 4
      val registrationId = secondRowColumns[0].trim()
      val registrationIdIsValid =
        Regex("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}").matches(registrationId)
      val secondAndThirdColumnsAreBlank =
        secondRowColumns[1].isEmpty() && secondRowColumns[2].isEmpty()
      val name = secondRowColumns[3].trim()
      val thirdRowIsEmptyString = rows[2].isEmpty()

      return hasRowSizeOfTwo &&

//              First Row Validations
              firstRowHas3Columns &&
              eventName.isNotBlank() &&
              sessionName.isNotBlank() &&
              pnrMatchesPattern &&

//              Second Row Validations
              secondRowHas4Columns &&
              registrationIdIsValid &&
              secondAndThirdColumnsAreBlank &&
              name.isNotBlank() &&

//              Empty thrid row validation
              thirdRowIsEmptyString
    } catch (_: Exception) {
      return false
    }
  }
}