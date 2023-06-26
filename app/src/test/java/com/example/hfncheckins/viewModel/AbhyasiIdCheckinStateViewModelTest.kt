package com.example.hfncheckins.viewModel

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class AbhyasiIdCheckinStateViewModelTest {

    lateinit var abhyasiIdCheckinStateViewModel: AbhyasiIdCheckinStateViewModel

    @Before
    fun setup() {
        abhyasiIdCheckinStateViewModel = AbhyasiIdCheckinStateViewModel()
    }

    @Test
    fun test_initialState() {
        assertEquals("", abhyasiIdCheckinStateViewModel.appUiState.value.abhyasiId)
    }

    @Test
    fun test_updateAbhyasiId() {
        val abhyasiId = "INAAA328"
        abhyasiIdCheckinStateViewModel.updateAbhyasiId(abhyasiId)
        assertEquals(abhyasiId, abhyasiIdCheckinStateViewModel.appUiState.value.abhyasiId)
    }
}