package com.example.earthquakes.home.setup

import androidx.lifecycle.Observer
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.quakeresult.QuakesResult
import com.example.earthquakes.framework.resource.ResourceProvider
import com.example.earthquakes.presentation.home.interactors.HomeUseCases
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import com.example.earthquakes.usecases.UseCaseGetLocalQuakes
import com.example.earthquakes.usecases.UseCaseGetRemoteQuakes
import com.example.earthquakes.usecases.UseCaseStoreQuakes
import com.example.earthquakes.presentation.home.viewmodel.HomeViewModel
import com.example.earthquakes.presentation.preferences.PrefsManager
import com.example.earthquakes.setup.UnitTestSetup
import kotlinx.coroutines.runBlocking
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyZeroInteractions

abstract class HomeViewModelTestSetup : UnitTestSetup() {

    @Mock
    protected lateinit var mockUseCaseGetLocalItems: UseCaseGetLocalQuakes

    @Mock
    protected lateinit var mockUseCaseGetRemoteItems: UseCaseGetRemoteQuakes

    @Mock
    protected lateinit var mockUseCaseStoreItems: UseCaseStoreQuakes

    @Mock
    protected lateinit var mockUseCases: HomeUseCases

    @Mock
    protected lateinit var mockConnectivity: ConnectivityMonitor

    @Mock
    protected lateinit var mockPrefsManager: PrefsManager

    @Mock
    protected lateinit var mockResourceProvider: ResourceProvider

    @Mock
    lateinit var mockObserver: Observer<QuakesResult>

    private val mockItems = mockParser.getMockQuakesFromFeedWithAllItemsValid()
    private val mockQuakesResultSuccess = QuakesResult.Success(mockItems)
    private val mockQuakesResultError = QuakesResult.Error(ERROR_MESSAGE)
    protected lateinit var subject: HomeViewModel

    companion object {
        const val ERROR_MESSAGE = "error"
    }

    override fun initialise() {
        super.initialise()
        initialiseMockInteractors()
        mockPrefsManager()
    }

    override fun initialiseClassUnderTest() {
        subject = HomeViewModel(
            mockUseCases,
            mockConnectivity,
            mockPrefsManager,
            mockResourceProvider
        )
    }

    private fun initialiseMockInteractors() {
        Mockito.`when`(mockUseCases.getRemoteQuakes).thenReturn(mockUseCaseGetRemoteItems)
        Mockito.`when`(mockUseCases.getLocalQuakes).thenReturn(mockUseCaseGetLocalItems)
        Mockito.`when`(mockUseCases.storeQuakes).thenReturn(mockUseCaseStoreItems)
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
            Mockito.`when`(
                mockUseCaseGetRemoteItems.invoke(
                    mockPrefsManager.getNorth(),
                    mockPrefsManager.getSouth(),
                    mockPrefsManager.getEast(),
                    mockPrefsManager.getWest(),
                    mockPrefsManager.getMaxQuakeResults(),
                    mockPrefsManager.getUsername()
                )
            ).thenReturn(items)
        }
    }

    protected fun mockRemoteCallThrowsError() {
        runBlocking {
            Mockito.`when`(
                mockUseCaseGetRemoteItems.invoke(
                    mockPrefsManager.getNorth(),
                    mockPrefsManager.getSouth(),
                    mockPrefsManager.getEast(),
                    mockPrefsManager.getWest(),
                    mockPrefsManager.getMaxQuakeResults(),
                    mockPrefsManager.getUsername()
                )
            )
                .thenThrow(IllegalStateException(ERROR_MESSAGE))
        }
    }

    protected fun verifyRemoteCallDone() {
        runBlocking {
            verify(
                mockUseCaseGetRemoteItems,
                Mockito.times(1)
            ).invoke(
                mockPrefsManager.getNorth(),
                mockPrefsManager.getSouth(),
                mockPrefsManager.getEast(),
                mockPrefsManager.getWest(),
                mockPrefsManager.getMaxQuakeResults(),
                mockPrefsManager.getUsername()
            )
        }
    }

    protected fun verifyRemoteCallNotDone() {
        runBlocking {
            verify(mockUseCaseGetRemoteItems, Mockito.never()).invoke(
                any(),
                any(),
                any(),
                any(),
                any(),
                any()
            )
        }
    }

    // Local Call

    protected fun mockLocalCallReturnsAllItemsValid() {
        mockLocalCall(mockItems)
    }

    private fun mockLocalCall(items: List<Quake>) {
        runBlocking {
            Mockito.`when`(mockUseCaseGetLocalItems.invoke()).thenReturn(items)
        }
    }

    protected fun mockLocalCallThrowsError() {
        runBlocking {
            Mockito.`when`(mockUseCaseGetLocalItems.invoke())
                .thenThrow(IllegalStateException(ERROR_MESSAGE))
        }
    }

    protected fun verifyLocalCallDone() {
        runBlocking {
            verify(mockUseCaseGetLocalItems, Mockito.times(1)).invoke()
        }
    }

    protected fun verifyLocalCallNotDone() {
        runBlocking {
            verify(mockUseCaseGetLocalItems, Mockito.never()).invoke()
        }
    }

    // LiveData

    protected fun initialiseLiveData() {
        subject.quakesResult.observeForever(mockObserver)
    }

    protected fun verifyLiveDataChangedWithSuccess() {
        verifyLiveDataChanged(mockQuakesResultSuccess)
    }

    protected fun verifyLiveDataChangedWithError() {
        verifyLiveDataChanged(mockQuakesResultError)
    }

    private fun verifyLiveDataChanged(result: QuakesResult) {
        verify(mockObserver).onChanged(result)
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
            verify(mockUseCaseStoreItems, Mockito.times(1)).invoke(items)
        }
    }

    protected fun verifyDataNotStored() {
        runBlocking {
            verify(mockUseCaseStoreItems, Mockito.never()).invoke(any())
        }
    }

    private fun mockPrefsManager() {
        Mockito.`when`(mockPrefsManager.getNorth()).thenReturn(mockNorthBound)
        Mockito.`when`(mockPrefsManager.getSouth()).thenReturn(mockSouthBound)
        Mockito.`when`(mockPrefsManager.getEast()).thenReturn(mockEastBound)
        Mockito.`when`(mockPrefsManager.getWest()).thenReturn(mockWestBound)
        Mockito.`when`(mockPrefsManager.getMaxQuakeResults()).thenReturn(mockMaxResults)
        Mockito.`when`(mockPrefsManager.getMaxQuakeResults()).thenReturn(mockMaxResults)
        Mockito.`when`(mockPrefsManager.getUsername()).thenReturn(mockUsername)
    }

}