package com.example.hfncheckins.viewModel

import com.example.hfncheckins.ui.components.MainScreen.MainScreenViewModel
import org.junit.Test
import org.junit.Assert.*

class MainScreenViewModelTest {

    @Test
    fun test_update_function() {
        val mainScreenViewModel = MainScreenViewModel()
        assertEquals("", mainScreenViewModel.uiState.value.value)
        val updatedValue = "Hello World"
        mainScreenViewModel.update(updatedValue)
        assertEquals(updatedValue, mainScreenViewModel.uiState.value.value)
        assertEquals(false, mainScreenViewModel.uiState.value.isValid)
    }

    @Test
    fun test_isValidForAbhyasiId() {
        val mainScreenViewModel = MainScreenViewModel()
        assertEquals("", mainScreenViewModel.uiState.value.value)
        val updatedValue = "UEWWWE837"
        mainScreenViewModel.update(updatedValue)
        assertEquals(updatedValue, mainScreenViewModel.uiState.value.value)
        assertEquals(true, mainScreenViewModel.uiState.value.isValid)
    }

    @Test fun test_isValidForContactNumber() {
        val mainScreenViewModel = MainScreenViewModel()
        assertEquals("", mainScreenViewModel.uiState.value.value)
        assertEquals(false, mainScreenViewModel.uiState.value.isValid)
        val updatedValue = "+918383838473"
        mainScreenViewModel.update(updatedValue)
        assertEquals(updatedValue, mainScreenViewModel.uiState.value.value)
        assertEquals(true, mainScreenViewModel.uiState.value.isValid)
    }

    @Test fun test_isValidForEmailAddress() {
        val mainScreenViewModel = MainScreenViewModel()
        assertEquals("", mainScreenViewModel.uiState.value.value)
        assertEquals(false, mainScreenViewModel.uiState.value.isValid)
        val updatedValue = "abc@def.com"
        mainScreenViewModel.update(updatedValue)
        assertEquals(updatedValue, mainScreenViewModel.uiState.value.value)
        assertEquals(true, mainScreenViewModel.uiState.value.isValid)
    }
}