package com.example.hfncheckins.utils

import org.junit.Test
import org.junit.Assert.*

class isValidPhoneNumberTest {
    @Test
    fun test_validValue() {
        assertEquals(
            isValidPhoneNumber("+91273328455"),
            true
        )
        assertEquals(
            isValidPhoneNumber("+112733232255"),
            true
        )
    }

    @Test
    fun test_invalidValue() {
        assertEquals(
            isValidPhoneNumber("+9"),
            false
        )
        assertEquals(
            isValidPhoneNumber("+992838383838383333"),
            false
        )
        assertEquals(
            isValidPhoneNumber("Hello World"),
            false
        )
        assertEquals(
            isValidPhoneNumber("992838"),
            false
        )
    }
}