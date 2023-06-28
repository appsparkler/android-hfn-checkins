package com.example.hfncheckins.utils

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