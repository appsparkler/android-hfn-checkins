package com.example.hfncheckins.ui.components.MainScreen

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.hfncheckins.data.sample.getSampleEvent
import com.example.hfncheckins.data.strings
import com.example.hfncheckins.viewModel.SeekerInfoFieldViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class SeekerInfoFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_initialView() {
        val event = getSampleEvent()
        composeTestRule.setContent {
            SeekerInfoField(
                event = event,
                onStartCheckin = {}
            )
        }
        composeTestRule
            .onNodeWithText(event.title)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(strings.inputInstructions)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }

    @Test
    fun test_inputValue() {
        val event = getSampleEvent()
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        composeTestRule.setContent {
            SeekerInfoField(
                event = event,
                onStartCheckin = {},
                seekerInfoFieldViewModel = seekerInfoFieldViewModel
            )
        }
        val textInput = "Hello World"
        composeTestRule.onNodeWithTag("seeker-input-field")
            .performTextInput(textInput)
        assertEquals(
            seekerInfoFieldViewModel.uiState.value.value,
            textInput
        )
    }
}