package com.example.earthquakes.home

import com.example.earthquakes.home.setup.HomeViewModelTestSetup
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest : HomeViewModelTestSetup() {

    init {
        initialise()
    }

    @Before
    fun doBeforeEachTestCase() {
        initialiseLiveData()
    }

    @Test
    fun internetOn_when_viewModelBinds_then_remoteCallDone_dataStored_liveDataUpdated() {
        // given
        mockInternetOn()
        mockRemoteCallReturnsAllItemsValid()

        // when
        subject.fetchQuakes()

        // then
        verify_remoteFeedCallDone_dataStored_liveDataUpdated()
    }

    @Test
    fun internetOff_when_viewModelBinds_then_localItemsFetched_liveDataUpdated() {
        // given
        mockInternetOff()
        mockLocalCallReturnsAllItemsValid()

        // when
        subject.fetchQuakes()

        // then
        verifyInternetChecked()
        verifyRemoteCallNotDone()
        verifyLocalCallDone()
        verifyDataNotStored()
        verifyLiveDataChangedWithSuccess()
    }

    @Test
    fun remoteCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockInternetOn()
        mockRemoteCallThrowsError()

        // when
        subject.fetchQuakes()

        // then
        verifyInternetChecked()
        verifyRemoteCallDone()
        verifyLocalCallNotDone()
        verifyLiveDataChangedWithError()
    }

    @Test
    fun localCallThrowsError_when_viewModelBinds_nothingHappens() {
        // given
        mockInternetOff()
        mockLocalCallThrowsError()

        // when
        subject.fetchQuakes()

        // then
        verifyInternetChecked()
        verifyRemoteCallNotDone()
        verifyLocalCallDone()
        verifyLiveDataChangedWithError()
    }

    private fun verify_remoteFeedCallDone_dataStored_liveDataUpdated() {
        verifyInternetChecked()
        verifyRemoteCallDone()
        verifyLocalCallNotDone()
        verifyLiveDataChangedWithSuccess()
        verifyDataStored()
    }

}