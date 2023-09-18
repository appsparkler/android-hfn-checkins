package com.appsparkler.hfncheckins.ui.components.MainScreen

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

class MainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

//    @Test
//    fun test_initialView() {
//        val event = getSampleEvent()
//        composeTestRule.setContent {
//            MainScreen(
//                event = event,
//                onStartCheckin = {},
//                onClickScan = {}
//            )
//        }
//
//        composeTestRule
//            .onNodeWithText(event.title)
//            .assertIsDisplayed()
//        composeTestRule
//            .onNodeWithText(strings.inputInstructions)
//            .assertIsDisplayed()
//        composeTestRule
//            .onNodeWithText(strings.pleaseEnterInfo)
//            .assertIsDisplayed()
//        composeTestRule
//            .onNodeWithText(strings.startCheckin)
//            .assertIsDisplayed()
//            .assertIsNotEnabled()
//        composeTestRule
//            .onNodeWithText(strings.scan)
//            .assertIsDisplayed()
//            .assertIsEnabled()
//    }
//
//    @Test
//    fun test_onStartCheckin_onClickScan_CTA() {
//        val event = getSampleEvent()
//        var submittedValue = ""
//        var isScanClicked = false
//        composeTestRule.setContent {
//            MainScreen(
//                event = getSampleEvent(),
//                onStartCheckin = {
//                    submittedValue = it
//                },
//                onClickScan = {
//                    isScanClicked = true
//                }
//            )
//        }
//
////        Start Checkin
//        val abhyasiId = "EUQQQE382"
//        composeTestRule
//            .onNodeWithTag(strings.tag_seeker_input)
//            .performTextInput(abhyasiId)
//        composeTestRule
//            .onNodeWithText(strings.startCheckin)
//            .performClick()
//        assertEquals(submittedValue, abhyasiId)
//
////        Click Scan
//        composeTestRule
//            .onNodeWithText(strings.scan)
//            .performClick()
//        assertEquals(true, isScanClicked)
//    }
}