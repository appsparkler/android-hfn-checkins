package com.example.hfncheckins.ui.components.InputScreen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class ScanButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_onClickHandlerIsCalled() {
        var clicked = 0
        composeTestRule.setContent {
            ScanButton(
                onClick = {
                    clicked++
                }
            )
        }
        composeTestRule.onNodeWithText(
            "SCAN",
            true,
            ignoreCase = true
        )
            .performClick()
        assertEquals(1, clicked)
    }
}