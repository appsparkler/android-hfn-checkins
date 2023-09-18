package com.appsparkler.hfncheckins.utils

import org.junit.Test
import org.junit.Assert.assertEquals

class isEmailValidTest {

    @Test fun test_validValue() {
        assertEquals(
            isEmailValid("abc@def.com")
            , true
        )
        assertEquals(
            isEmailValid("a___bc@def.com")
            , true
        )
    }

    @Test fun test_invalidValue() {
        assertEquals(
            isEmailValid("abc"),
            false
        )
        assertEquals(
            isEmailValid("abc@"),
            false
        )
    }
}