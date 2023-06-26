package com.example.hfncheckins.viewModel.utils

import com.example.hfncheckins.utils.isValidAbhyasiId
import org.junit.Test
import org.junit.Assert.assertEquals

class isValidAbhyasiIdTest {

    @Test
    fun test_validCase() {
        assertEquals(true, isValidAbhyasiId("INAAAE382"))
    }

    @Test
    fun test_invalidCase() {
        assertEquals(false, isValidAbhyasiId("Hello World"))
    }
}