package com.example.hfncheckins.viewModel

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*

class SeekerInfoFieldViewModelTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_update_function() {
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        assertEquals("", seekerInfoFieldViewModel.uiState.value.value)
        val updatedValue = "Hello World"
        seekerInfoFieldViewModel.updateValue(updatedValue)
        assertEquals(updatedValue, seekerInfoFieldViewModel.uiState.value.value)
        assertEquals(false, seekerInfoFieldViewModel.uiState.value.isValid)
    }

    @Test
    fun test_isValidForAbhyasiId() {
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        assertEquals("", seekerInfoFieldViewModel.uiState.value.value)
        val updatedValue = "UEWWWE837"
        seekerInfoFieldViewModel.updateValue(updatedValue)
        assertEquals(updatedValue, seekerInfoFieldViewModel.uiState.value.value)
        assertEquals(true, seekerInfoFieldViewModel.uiState.value.isValid)
    }
}