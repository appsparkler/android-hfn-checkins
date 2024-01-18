package com.appsparkler.hfncheckins.utils

import com.appsparkler.hfncheckins.model.QRType
import junit.framework.TestCase.assertEquals
import org.junit.Test

class QRUtilsTest {

  val singlePersonQR = "Group Meditation with Shri Modi Ji and Daaji| Satsangh| NI-IDSY-EIRL;1846a39d-44e6-432e-b443-146fec0327a2| batch 1 | INBLAD143| BULUSU LAKSHMI;"

  val twoPersonQR = "Group Meditation with Shri Modi Ji and Daaji| Satsangh| CH-IDSZ-QOMJ;dab5e1ab-ea9c-4be2-a82e-7cd938a2f7af| batch 1,batch 2 | INMKAD173| MUTHUKUMAR K.;4d848a94-b273-4637-a0bc-b853d269d8ee | batch1, batch2| INCMAE111| Chitra M. Bhatia;"

  @Test
  fun test_getGeneralDetails() {
    val generalDetails = getGeneralDetails(singlePersonQR)
    assertEquals(generalDetails.eventTitle, "Group Meditation with Shri Modi Ji and Daaji")
    assertEquals(generalDetails.pnr, "NI-IDSY-EIRL")
    assertEquals(generalDetails.orderId, "Satsangh")

    val generalDetails2 = getGeneralDetails(twoPersonQR)
    assertEquals(generalDetails2.eventTitle, "Group Meditation with Shri Modi Ji and Daaji")
    assertEquals(generalDetails2.pnr, "CH-IDSZ-QOMJ")
    assertEquals(generalDetails2.orderId, "Satsangh")
  }

  @Test
  fun test_getQRCheckinsAndMore() {
    val singlePersonResult = getQRCheckinsAndMore(singlePersonQR)
    assertEquals(1, singlePersonResult.checkins.size)
    assertEquals("BULUSU LAKSHMI", singlePersonResult.checkins[0].fullName)
    assertEquals("INBLAD143", singlePersonResult.checkins[0].abhyasiId)
    assertEquals("1846a39d-44e6-432e-b443-146fec0327a2", singlePersonResult.checkins[0].regId)

    val twoPersonResult = getQRCheckinsAndMore(twoPersonQR)
    assertEquals(2, twoPersonResult.checkins.size)

    assertEquals("MUTHUKUMAR K.", twoPersonResult.checkins[0].fullName)
    assertEquals("dab5e1ab-ea9c-4be2-a82e-7cd938a2f7af", twoPersonResult.checkins[0].regId)
    assertEquals("INMKAD173", twoPersonResult.checkins[0].abhyasiId)

    assertEquals("INCMAE111", twoPersonResult.checkins[1].abhyasiId)
    assertEquals("Chitra M. Bhatia", twoPersonResult.checkins[1].fullName)
    assertEquals("4d848a94-b273-4637-a0bc-b853d269d8ee", twoPersonResult.checkins[1].regId)

  }

  @Test
  fun test_isQRValid_PAID_ACCOMODATION() {
    assertEquals(true, isQRValid(singlePersonQR))
    assertEquals(true, isQRValid(twoPersonQR))
  }

  @Test
  fun test_isQRValid_OWN_ACCOMODATION_WITH_MORE() {
    val qrRawValue = "68th Birthday of Pujya Daaji Maharaj| Bhandara| MA-ICQK-ZTQH;aa037594-6276-45c2-b57e-714b154fa9a3|batch-2|INJSAB136|Karan Crowston;d4ab8ca1-29ec-4ef6-910b-731a6ed1751a|batch-1|INKJAB106|Petronila Rouzer;57d4f9d1-1712-4a86-a859-f6c40ae8169f|batch-2, batch-1|INVGAD114|Osvaldo Hons;671a7fd6-6cd4-4762-8d46-6bb71d21d0bf|batch-2|INCKAE027|Denyse Sanz;0baa6bcd-2358-4435-bf40-5145c59a7d17|batch-1|INKAAE326|Nigel Mikles;3 more.."
    assertEquals(true, isQRValid(qrRawValue))
  }

  @Test
  fun test_getQRCheckinsAndMore_OWN_ACCOMODATION() {
    val result = getQRCheckinsAndMore(singlePersonQR)
    assertEquals(1, result.checkins.size)
  }

  @Test
  fun test_getQRCheckinsAndMore_NO_MORE_INFo() {
    val paidQR =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj|Bhandara|ME-ICJN-MHVQ;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|batch1,batch2|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|LB;"
    val result = getQRCheckinsAndMore(paidQR)
    assertEquals("", result.more)
    assertEquals(1, result.checkins.size)
//    assertEquals("batch1,batch2", result.checkins[0].batch)
  }

  @Test
  fun test_isQRValid_OWN_ACCOMODATION() {
    val scannedValue = "96th Birth Anniversary of Pujya Shri Chariji Maharaj| Bhandara| SU-ICJQ-VZJK;e1e2da12-4f2f-4c42-b8ee-87df204a3e4e|batch1,batch2|INAUEW392|User 1;633d53fe-443d-4fg4-b3b0-7bd13dabf303|batch1|INLIET292|User 2;"
    assertEquals(true, isQRValid(scannedValue))
  }

  @Test
  fun test_getQRCheckins_paid_accomodation() {
    val refinedValue =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj|ME-ICJN-MHVQ|24999;4e0a5913-b77d-4c2f-a4fd-d4554e930ecf|INKKAD166|K. KAILASAM|SouthS2-GF-NonAC|batch1|LB;"
    val checkins = getQRCheckins(refinedValue)
    val firstCheckin = checkins[0]

    assertEquals(1, checkins.size)

    assertEquals(firstCheckin.regId, "4e0a5913-b77d-4c2f-a4fd-d4554e930ecf")
    assertEquals( "INKKAD166",firstCheckin.abhyasiId)
    assertEquals( "K. KAILASAM", firstCheckin.fullName,)
//    assertEquals("batch1",firstCheckin.batch,)
    assertEquals( "SouthS2-GF-NonAC",firstCheckin.dormPreference)
    assertEquals( "LB",firstCheckin.berthPreference)

    assertEquals(false, firstCheckin.checkin)
    assertEquals("", firstCheckin.dormAndBerthAllocation)
    assertEquals(0, firstCheckin.timestamp)

    assertEquals("96th Birth Anniversary of Pujya Shri Chariji Maharaj", firstCheckin.eventName)
    assertEquals("ME-ICJN-MHVQ", firstCheckin.pnr)
    assertEquals("24999", firstCheckin.orderId)
  }

  @Test
  fun test_getQRCheckins_own_accomodation() {
    val code =
      "96th Birth Anniversary of Pujya Shri Chariji Maharaj| Bhandara| SU-ICJQ-VZJK;e1e2da12-4f2f-4c42-b8ee-87df204a3e4e|batch1|INAUEW392|User 1;633d53fe-443d-4fg4-b3b0-7bd13dabf303|batch1|INLIET292|User 2;"
    val checkins = getQRCheckins(code)
    val firstCheckin = checkins[0]
    assertEquals(2, checkins.size)

    assertEquals("e1e2da12-4f2f-4c42-b8ee-87df204a3e4e", firstCheckin.regId)
    assertEquals("INAUEW392", firstCheckin.abhyasiId)
    assertEquals("User 1", firstCheckin.fullName)
//    assertEquals("batch1", firstCheckin.batch)
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