package com.example.hfncheckins.viewModel

import com.example.hfncheckins.data.sample.getSampleEvent
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class AppViewModelTest {

    lateinit var appViewModel: AppViewModel

    @Before
    fun setup() {
        appViewModel = AppViewModel()
    }

    @Test
    fun test_initialState() {
        assertNull(appViewModel.uiState.value.abhyasiIdCheckin)
        assertNull(appViewModel.uiState.value.checkinType)
        assertEquals("", appViewModel.uiState.value.qrcodeValue)
        assertNull(appViewModel.uiState.value.mobileOrEmailCheckin)
    }

    @Test
    fun test_startAbhyasiIdCheckin() {
        val abhyasiId = "WUQQQE482"
        val event = getSampleEvent()
        appViewModel.startAbhyasiCheckin(
            abhyasiId,
            event = event
        )
        assertEquals(appViewModel.uiState.value.checkinType, CheckinTypesEnum.AbhyasiId)
        assertEquals(appViewModel.uiState.value.abhyasiIdCheckin?.abhyasiId, abhyasiId)
        assertEquals(appViewModel.uiState.value.abhyasiIdCheckin?.eventName, event.title)
    }

    @Test fun test_startEmailOrMobileCheckin_mobileCheckin() {
        val mobileNumber = "+918838383838"
        val event = getSampleEvent()
        appViewModel.startEmailOrMobileCheckin(
            mobileNumber,event=event
        )
        assertEquals(appViewModel.uiState.value.checkinType, CheckinTypesEnum.EmailOrMobile)
        assertEquals(
            appViewModel.uiState.value.mobileOrEmailCheckin?.mobile,
            mobileNumber
        )
        assertEquals(
            appViewModel.uiState.value.mobileOrEmailCheckin?.eventName,
            event.title
        )
    }
}