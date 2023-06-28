package com.example.hfncheckins.viewModel

import org.junit.Test
import org.junit.Assert.*

class SeekerInfoFieldViewModelTest {

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

    @Test fun test_isValidForContactNumber() {
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        assertEquals("", seekerInfoFieldViewModel.uiState.value.value)
        assertEquals(false, seekerInfoFieldViewModel.uiState.value.isValid)
        val updatedValue = "+918383838473"
        seekerInfoFieldViewModel.updateValue(updatedValue)
        assertEquals(updatedValue, seekerInfoFieldViewModel.uiState.value.value)
        assertEquals(true, seekerInfoFieldViewModel.uiState.value.isValid)
    }

    @Test fun test_isValidForEmailAddress() {
        val seekerInfoFieldViewModel = SeekerInfoFieldViewModel()
        assertEquals("", seekerInfoFieldViewModel.uiState.value.value)
        assertEquals(false, seekerInfoFieldViewModel.uiState.value.isValid)
        val updatedValue = "abc@def.com"
        seekerInfoFieldViewModel.updateValue(updatedValue)
        assertEquals(updatedValue, seekerInfoFieldViewModel.uiState.value.value)
        assertEquals(true, seekerInfoFieldViewModel.uiState.value.isValid)
    }
}