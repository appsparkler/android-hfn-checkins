package com.appsparkler.gsm

import com.appsparkler.gsm.features.HomeScreen.QRUtils
import org.junit.Assert.assertEquals
import org.junit.Test

class QRUtilsTest {
  val qrUtils = QRUtils()
  @Test
  fun test_isQRValid_trueScenario() {
    val scanResult =
      "Global Spirituality Mahotsav| Global Spiritual Mahatsov| RA-IEWY-QODX;6d60d1b8-f3c1-4ced-8900-ef538b724bf6|||Ravi B PDP;"
    val result = qrUtils.isQRValid(scanResult)
    assertEquals(true, result)
  }

  @Test
  fun test_isQRValid_falseScenario() {
    val withOnly2ColumnsInFirstRows =
      "Global Spiritual Mahatsov| RA-IEWY-QODX;6d60d1b8-f3c1-4ced-8900-ef538b724bf6|||Ravi B PDP;"
    val withIncorrectPNR =
      "Global Spirituality Mahotsav| Global Spiritual Mahatsov| RA-IEWY-Q12X;6d60d1b8-f3c1-4ced-8900-ef538b724bf6|||Ravi B PDP;"
    val withEmptyEventName =
      "| Global Spiritual Mahatsov| RA-IEWY-QODX;6d60d1b8-f3c1-4ced-8900-ef538b724bf6|||Ravi B PDP;"
    val withIncorrectRegId = "Global Spirituality Mahotsav| Global Spiritual Mahatsov| RA-IEWY-QODX;6d60d1b8-f3c1-4ced-800-ef538b724bf6|||Ravi B PDP;"
    val withEmptyName = "Global Spirituality Mahotsav| Global Spiritual Mahatsov| RA-IEWY-QODX;6d60d1b8-f3c1-4ced-8900-ef538b724bf6|||;"
    assertEquals(false, qrUtils.isQRValid(withOnly2ColumnsInFirstRows))
    assertEquals(false, qrUtils.isQRValid(withIncorrectPNR))
    assertEquals(false, qrUtils.isQRValid(withEmptyEventName))
    assertEquals(false, qrUtils.isQRValid(withIncorrectRegId))
    assertEquals(false, qrUtils.isQRValid(withEmptyName))
  }

  @Test
  fun test_getQRUser() {
    val scanResult = "Global Spirituality Mahotsav| Global Spiritual Mahatsov| RA-IEWY-QODX;6d60d1b8-f3c1-4ced-8900-ef538b724bf6|||Ravi B PDP;"
    val qrUser = qrUtils.getQRUser(scanResult)
    assertEquals("Global Spirituality Mahotsav", qrUser.eventName)
    assertEquals("Global Spiritual Mahatsov", qrUser.sessionName)
    assertEquals("RA-IEWY-QODX", qrUser.pnr)
    assertEquals("6d60d1b8-f3c1-4ced-8900-ef538b724bf6", qrUser.registrationId)
    assertEquals("Ravi B PDP", qrUser.name)
  }
}