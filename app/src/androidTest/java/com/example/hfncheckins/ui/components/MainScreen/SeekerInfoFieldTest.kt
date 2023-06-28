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
                hfnEvent = event,
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
    fun test_invalidInputValue() {
        val event = getSampleEvent()
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        composeTestRule.setContent {
            SeekerInfoField(
                hfnEvent = event,
                onStartCheckin = {},
                seekerInfoFieldViewModel = seekerInfoFieldViewModel
            )
        }
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsNotEnabled()
        val textInput = "Hello World"
        composeTestRule.onNodeWithTag("seeker-input-field")
            .performTextInput(textInput)
        assertEquals(
            seekerInfoFieldViewModel.uiState.value.value,
            textInput
        )
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsNotEnabled()
    }

    @Test
    fun test_validInput_abhyasiId() {
        val event = getSampleEvent()
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        composeTestRule.setContent {
            SeekerInfoField(
                hfnEvent = event,
                onStartCheckin = {},
                seekerInfoFieldViewModel = seekerInfoFieldViewModel
            )
        }
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsNotEnabled()
        val textInput = "OPXXBK478"
        composeTestRule.onNodeWithTag("seeker-input-field")
            .performTextInput(textInput)
        assertEquals(
            seekerInfoFieldViewModel.uiState.value.value,
            textInput
        )
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun test_validInput_mobileNumber() {
        val event = getSampleEvent()
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        composeTestRule.setContent {
            SeekerInfoField(
                hfnEvent = event,
                onStartCheckin = {},
                seekerInfoFieldViewModel = seekerInfoFieldViewModel
            )
        }
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsNotEnabled()
        val textInput = "+917330987462"
        composeTestRule.onNodeWithTag("seeker-input-field")
            .performTextInput(textInput)
        assertEquals(
            seekerInfoFieldViewModel.uiState.value.value,
            textInput
        )
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun test_startCheckin() {
        val event = getSampleEvent()
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        var inputValue = ""
        composeTestRule.setContent {
            SeekerInfoField(
                hfnEvent = event,
                onStartCheckin = {
                    inputValue = it
                },
                seekerInfoFieldViewModel = seekerInfoFieldViewModel
            )
        }
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsNotEnabled()
        val mobileNumber = "+917330987462"
        composeTestRule
            .onNodeWithTag("seeker-input-field")
            .performTextInput(mobileNumber)
        assertEquals(
            seekerInfoFieldViewModel.uiState.value.value,
            mobileNumber
        )
        composeTestRule
            .onNodeWithText(strings.startCheckin)
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()
        assertEquals(inputValue, mobileNumber)
    }

    @Test
    fun test_hasImeAction() {
        val event = getSampleEvent()
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        var inputValue = ""
        composeTestRule.setContent {
            SeekerInfoField(
                hfnEvent = event,
                onStartCheckin = {
                    inputValue = it
                },
                seekerInfoFieldViewModel = seekerInfoFieldViewModel
            )
        }
        val mobileNumber = "+917330987462"
        composeTestRule
            .onNodeWithTag(strings.tag_seeker_input)
            .performTextInput(mobileNumber)
        composeTestRule
            .onNodeWithTag(strings.tag_seeker_input)
            .performImeAction()
        assertEquals(mobileNumber, inputValue)
    }
}