package com.example.earthquakes.home.setup

import androidx.lifecycle.Observer
import com.example.earthquakes.BuildConfig
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.presentation.home.interactors.HomeInteractors
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import com.example.earthquakes.interactors.InterGetLocalQuakes
import com.example.earthquakes.interactors.InterGetRemoteQuakes
import com.example.earthquakes.interactors.InterStoreQuakes
import com.example.earthquakes.presentation.home.viewmodel.HomeViewModel
import com.example.earthquakes.presentation.preferences.PrefsManager
import com.example.earthquakes.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito

abstract class HomeViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockInterGetLocalItems: InterGetLocalQuakes

    @Mock
    protected lateinit var mockInterGetRemoteItems: InterGetRemoteQuakes

    @Mock
    protected lateinit var mockInterStoreItems: InterStoreQuakes

    @Mock
    protected lateinit var mockInteractors: HomeInteractors

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor

    @Mock
    protected lateinit var mockPrefsManager: PrefsManager

    @Mock
    lateinit var mockObserver: Observer<List<Quake>>

    private val mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
    protected lateinit var subject: HomeViewModel

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
        mockPrefsManager()
    }

    override fun initialiseClassUnderTest() {
        subject = HomeViewModel(mockInteractors, mockConnectivity, mockPrefsManager)
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockInteractors.interGetRemoteQuakes).thenReturn(mockInterGetRemoteItems)
        Mockito.`when`(mockInteractors.interGetLocalQuakes).thenReturn(mockInterGetLocalItems)
        Mockito.`when`(mockInteractors.interStoreQuakes).thenReturn(mockInterStoreItems)
    }

    // Internet

    protected fun mockInternetOn() {
        mockInternetActive(true)
    }

    protected fun mockInternetOff() {
        mockInternetActive(false)
    }

    private fun mockInternetActive(active: Boolean) {
        Mockito.`when`(mockConnectivity.isOnline()).thenReturn(active)
    }

    protected fun verifyInternetChecked() {
        verify(mockConnectivity, Mockito.times(1)).isOnline()
    }

    // Remote Call

    protected fun mockRemoteCallReturnsAllItemsValid() {
        mockRemoteCall(mockItems)
    }

    private fun mockRemoteCall(items: List<Quake>) {
        runBlocking {
            Mockito.`when`(mockInterGetRemoteItems.invoke(
                mockPrefsManager.getNorth(),
                mockPrefsManager.getSouth(),
                mockPrefsManager.getEast(),
                mockPrefsManager.getWest(),
                mockPrefsManager.getMaxQuakeResults(),
                BuildConfig.GEONAMES_USERNAME
            )).thenReturn(items)
        }
    }

    protected fun mockRemoteCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockInterGetRemoteItems.invoke(
                mockPrefsManager.getNorth(),
                mockPrefsManager.getSouth(),
                mockPrefsManager.getEast(),
                mockPrefsManager.getWest(),
                mockPrefsManager.getMaxQuakeResults(),
                BuildConfig.GEONAMES_USERNAME))
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyRemoteCallDone() {
        runBlocking {
            verify(mockInterGetRemoteItems,
                Mockito.times(1)).invoke(
                mockPrefsManager.getNorth(),
                mockPrefsManager.getSouth(),
                mockPrefsManager.getEast(),
                mockPrefsManager.getWest(),
                mockPrefsManager.getMaxQuakeResults(),
                BuildConfig.GEONAMES_USERNAME)
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            verify(mockInterGetRemoteItems, Mockito.never()).invoke(any(), any(), any(), any(), any(), any())
        }
    }

    // Local Call

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockItems)
    }

    private fun mockLocalCall(items: List<Quake>) {
        runBlocking {
            Mockito.`when`(mockInterGetLocalItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockInterGetLocalItems.invoke())
                .thenThrow(IllegalStateException("Error"))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockInterGetLocalItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyLocalCallNotDone() {
        runBlocking {
            verify(mockInterGetLocalItems, Mockito.never()).invoke()
        }
    }

    // LiveData

    protected fun initialiseLiveData() {
        subject.models.observeForever(mockObserver)
    }

    protected fun verifyLiveDataChangedAsExpected() {
        verifyLiveDataChanged(mockItems)
    }

    private fun verifyLiveDataChanged(items: List<Quake>) {
        verify(mockObserver).onChanged(items)
    }

    protected fun verifyLiveDataNotChanged() {
        verifyZeroInteractions(mockObserver)
    }

    // Store Data

    protected fun verifyDataStored() {
        verifyDataStored(mockItems)
    }

    private fun verifyDataStored(items: List<Quake>) {
        runBlocking {
            verify(mockInterStoreItems, Mockito.times(1)).invoke(items)
        }
    }

    protected fun verifyDataNotStored() {
        runBlocking {
            verify(mockInterStoreItems, Mockito.never()).invoke(any())
        }
    }

    // prefs

    private fun mockPrefsManager() {
        Mockito.`when`(mockPrefsManager.getNorth()).thenReturn(mockNorthBound)
        Mockito.`when`(mockPrefsManager.getSouth()).thenReturn(mockSouthBound)
        Mockito.`when`(mockPrefsManager.getEast()).thenReturn(mockEastBound)
        Mockito.`when`(mockPrefsManager.getWest()).thenReturn(mockWestBound)
        Mockito.`when`(mockPrefsManager.getMaxQuakeResults()).thenReturn(mockMaxResults)
    }

}