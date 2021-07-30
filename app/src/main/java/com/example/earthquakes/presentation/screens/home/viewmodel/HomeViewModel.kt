package com.example.earthquakes.presentation.screens.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakes.framework.extensions.getErrorMessage
import com.example.earthquakes.result.QuakesResult
import com.example.earthquakes.framework.util.resource.ResourceProvider
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import com.example.earthquakes.presentation.screens.preferences.PrefsManager
import com.example.earthquakes.tests.usecases.UseCaseGetLocalQuakes
import com.example.earthquakes.tests.usecases.UseCaseGetRemoteQuakes
import com.example.earthquakes.tests.usecases.UseCaseStoreQuakes
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLocalQuakes: UseCaseGetLocalQuakes,
    private val getRemoteQuakes: UseCaseGetRemoteQuakes,
    private val storeQuakes: UseCaseStoreQuakes,
    private val connectivity: ConnectivityMonitor,
    private val prefsManager: PrefsManager,
    private val resProvider: ResourceProvider
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mQuakesResult = MutableLiveData<QuakesResult>()
    val quakesResult: LiveData<QuakesResult>
        get() = mQuakesResult

    fun fetchQuakes() {
        if (connectivity.isOnline()) fetchRemoteQuakes()
        else fetchLocalQuakes()
    }

    private fun fetchRemoteQuakes() {
        viewModelScope.launch {
            try {
                val items = getRemoteQuakes(
                    prefsManager.getNorth(),
                    prefsManager.getSouth(),
                    prefsManager.getEast(),
                    prefsManager.getWest(),
                    prefsManager.getMaxQuakeResults(),
                    prefsManager.getUsername()
                )
                mQuakesResult.value = QuakesResult.Success(items)
                storeQuakes(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuakesResult.value = QuakesResult.Error(getErrorMessage(e))
            }
        }
    }

    private fun fetchLocalQuakes() {
        viewModelScope.launch {
            try {
                val items = getLocalQuakes()
                mQuakesResult.value = QuakesResult.Success(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuakesResult.value = QuakesResult.Error(e.getErrorMessage())
            }
        }
    }

    private fun getErrorMessage(e: Exception): String {
        if (e is HttpException && e.code() == 401) return resProvider.getUnauthorisedUserMessage()
        return e.getErrorMessage()
    }

}