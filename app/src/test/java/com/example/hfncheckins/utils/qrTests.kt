package com.example.hfncheckins.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

class qrTests {
  val paidQR = "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"

  @Test
  fun test_getGeneralDetails() {
    val refinedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"
    val generalDetails = getGeneralDetails(refinedValue)
    assertEquals(generalDetails.eventTitle, "96th Birth Anniversary of Pujya Shri Chariji Maharaj")
    assertEquals(generalDetails.pnr, "ME-ICJN-MHVQ")
    assertEquals(generalDetails.orderId, "24999")
  }

  @Test
  fun test_isValidQRCode() {
    assertEquals(true, isQRValid(paidQR))
  }

  @Test
  fun test_getCheckns() {
    val refinedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"
    val checkins = getQRCheckins(refinedValue)
    val firstCheckin = checkins[0]

    assertEquals(1, checkins.size)

    assertEquals(firstCheckin.regId, "4e0a5913-b77d-4c2f-a4fd-d4554e930ecf")
    assertEquals(firstCheckin.abhyasiId, "INKKAD166")
    assertEquals(firstCheckin.fullName, "K. KAILASAM")
    assertEquals(firstCheckin.dormPreference, "SouthS2-GF-NonAC")
    assertEquals(firstCheckin.berthPreference, "LB")

    assertEquals(false, firstCheckin.checkin)
    assertEquals("", firstCheckin.dormAndBerthAllocation)
    assertEquals(0, firstCheckin.timestamp)

    assertEquals("96th Birth Anniversary of Pujya Shri Chariji Maharaj", firstCheckin.eventName)
    assertEquals("ME-ICJN-MHVQ", firstCheckin.pnr)
    assertEquals("24999", firstCheckin.orderId)
  }

}