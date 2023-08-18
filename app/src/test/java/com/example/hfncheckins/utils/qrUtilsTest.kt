package com.example.hfncheckins.utils

import com.example.hfncheckins.model.QRType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class qrUtilsTest {
  @Test
  fun test_getGeneralDetails_PAID_ACCOMODATION() {
    val refinedValue =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"
    val generalDetails = getGeneralDetails(refinedValue)
    assertEquals(generalDetails.eventTitle, "96th Birth Anniversary of Pujya Shri Chariji Maharaj")
    assertEquals(generalDetails.pnr, "ME-ICJN-MHVQ")
    assertEquals(generalDetails.orderId, "24999")
  }

  @Test
  fun test_isQRValid_PAID_ACCOMODATION() {
    val paidQR =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"
    assertEquals(true, isQRValid(paidQR))
  }

  @Test
  fun test_getQRCheckinsAndMore() {
    val paidQR =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;3 more..."
    val result = getQRCheckinsAndMore(paidQR)
    assertEquals("3 more...", result.more)
    assertEquals(1, result.checkins.size)
  }

//  @Test
//  fun test_isQRValid_PAID_ACCOMODATION_WITH_MORE() {
//    val paidQR =
//      "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;3 more..."
//    assertEquals(true, isQRValid(paidQR))
//  }

  @Test
  fun test_isQRValid_OWN_ACCOMODATION() {
    val scannedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj| Bhandara| SU-ICJQ-VZJK;e1e2da12-4f2f-4c42-b8ee-87df204a3e4e|batch1,batch2|INAUEW392|User 1;633d53fe-443d-4fg4-b3b0-7bd13dabf303|batch1|INLIET292|User 2;"
    assertEquals(true, isQRValid(scannedValue))
  }

  @Test
  fun test_getCheckins_paid_accomodation() {
    val refinedValue =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|batch1|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"
    val checkins = getQRCheckins(refinedValue)
    val firstCheckin = checkins[0]

    assertEquals(1, checkins.size)

    assertEquals(firstCheckin.regId, "4e0a5913-b77d-4c2f-a4fd-d4554e930ecf")
    assertEquals(firstCheckin.abhyasiId, "INKKAD166")
    assertEquals(firstCheckin.fullName, "K. KAILASAM")
    assertEquals("batch1", "batch1")
    assertEquals(firstCheckin.dormPreference, "SouthS2-GF-NonAC")
    assertEquals(firstCheckin.berthPreference, "LB")

    assertEquals(false, firstCheckin.checkin)
    assertEquals("", firstCheckin.dormAndBerthAllocation)
    assertEquals(0, firstCheckin.timestamp)

    assertEquals("96th Birth Anniversary of Pujya Shri Chariji Maharaj", firstCheckin.eventName)
    assertEquals("ME-ICJN-MHVQ", firstCheckin.pnr)
    assertEquals("24999", firstCheckin.orderId)
  }

  @Test
  fun test_getCheckins_own_accomodation() {
    val code =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj| Bhandara| SU-ICJQ-VZJK;e1e2da12-4f2f-4c42-b8ee-87df204a3e4e|batch1|INAUEW392|User 1;633d53fe-443d-4fg4-b3b0-7bd13dabf303|batch1|INLIET292|User 2;"
    val checkins = getQRCheckins(code)
    val firstCheckin = checkins[0]
    assertEquals(2, checkins.size)

    assertEquals("e1e2da12-4f2f-4c42-b8ee-87df204a3e4e", firstCheckin.regId)
    assertEquals("INAUEW392", firstCheckin.abhyasiId)
    assertEquals("User 1", firstCheckin.fullName)
    assertEquals("batch1", firstCheckin.batch)
    assertEquals("96th Birth Anniversary of Pujya Shri Chariji Maharaj", firstCheckin.eventName)
    assertEquals("SU-ICJQ-VZJK", firstCheckin.pnr)
  }

  @Test
  fun test_getQRType_OWN_ACCOMODATION() {
    val scannedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj| Bhandara| SU-ICJQ-VZJK;e1e2da12-4f2f-4c42-b8ee-87df204a3e4e|INAUEW392|User 1;633d53fe-443d-4fg4-b3b0-7bd13dabf303|batch1|INLIET292|User 2;"
    assertEquals(QRType.OWN_ACCOMODATION, getQRType(scannedValue))
  }

  @Test
  fun test_getQRType_OWN_ACCOMODATION_WITH_MORE() {
    val scannedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj| Bhandara| SU-ICJQ-VZJK;e1e2da12-4f2f-4c42-b8ee-87df204a3e4e|INAUEW392|User 1;633d53fe-443d-4fg4-b3b0-7bd13dabf303|batch1|INLIET292|User 2;3 more..."
    assertEquals(QRType.OWN_ACCOMODATION, getQRType(scannedValue))
  }

  @Test
  fun test_getQRType_PAID_ACCOMODATION() {
    val scannedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"
    assertEquals(QRType.PAID_ACCOMODATION, getQRType(scannedValue))
  }


  @Test
  fun test_getGeneralDetails_OWN_ACCOMODATION() {
    val scannedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj| Bhandara| SU-ICJQ-VZJK;e1e2da12-4f2f-4c42-b8ee-87df204a3e4e|INAUEW392|User 1;633d53fe-443d-4fg4-b3b0-7bd13dabf303|INLIET292|User 2;"
    val generalDetails = getGeneralDetails(scannedValue)
    assertEquals(generalDetails.eventTitle, "96th Birth Anniversary of Pujya Shri Chariji Maharaj")
    assertEquals(generalDetails.pnr, "SU-ICJQ-VZJK")
    assertEquals(generalDetails.orderId, "Bhandara")
  }
}