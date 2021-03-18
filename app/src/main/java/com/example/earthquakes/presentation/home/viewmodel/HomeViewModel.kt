package com.example.earthquakes.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakes.framework.extensions.getErrorMessage
import com.example.earthquakes.framework.quakeresult.QuakesResult
import com.example.earthquakes.framework.resource.ResourceProvider
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import com.example.earthquakes.presentation.home.interactors.HomeInteractors
import com.example.earthquakes.presentation.preferences.PrefsManager
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactors: HomeInteractors,
    private val connectivity: ConnectivityMonitor,
    private val prefsManager: PrefsManager,
    private val resProvider: ResourceProvider
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mQuakesResult = MutableLiveData<QuakesResult>()
    val quakesResult: LiveData<QuakesResult>
        get() = mQuakesResult

    fun hasUserName(): Boolean {
        return prefsManager.getUsername().isNotBlank()
    }

    fun fetchQuakes() {
        if (connectivity.isOnline()) fetchRemoteQuakes()
        else fetchLocalQuakes()
    }

    private fun fetchRemoteQuakes() {
        viewModelScope.launch {
            try {
                val items = interactors.interGetRemoteQuakes.invoke(
                    prefsManager.getNorth(),
                    prefsManager.getSouth(),
                    prefsManager.getEast(),
                    prefsManager.getWest(),
                    prefsManager.getMaxQuakeResults(),
                    prefsManager.getUsername()
                )
                mQuakesResult.value = QuakesResult.QuakesSuccess(items)
                interactors.interStoreQuakes.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuakesResult.value = QuakesResult.QuakesError(getErrorMessage(e))
            }
        }
    }

    private fun fetchLocalQuakes() {
        viewModelScope.launch {
            try {
                val items = interactors.interGetLocalQuakes.invoke()
                mQuakesResult.value = QuakesResult.QuakesSuccess(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuakesResult.value = QuakesResult.QuakesError(e.getErrorMessage())
            }
        }
    }

    private fun getErrorMessage(e: Exception): String {
        if (e is HttpException && e.code() == 401) return resProvider.getUnauthorisedUserMessage()
        return e.getErrorMessage()
    }

}