package com.appsparkler.hfncheckins.utils

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

    @Test
    fun test_newSeeker_lower_case() {
        assertEquals(true, isValidAbhyasiId("b33938392"))
    }

    @Test
    fun test_newSeeker_upper_case() {
        assertEquals(true, isValidAbhyasiId("H33938392"))

    }
}