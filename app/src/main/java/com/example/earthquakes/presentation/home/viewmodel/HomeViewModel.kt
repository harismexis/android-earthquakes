package com.example.earthquakes.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakes.BuildConfig
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.extensions.getErrorMessage
import com.example.earthquakes.framework.quakeresult.QuakesResult
import com.example.earthquakes.framework.util.functional.Action1
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import com.example.earthquakes.presentation.home.interactors.HomeInteractors
import com.example.earthquakes.presentation.preferences.PrefsManager
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactors: HomeInteractors,
    private val connectivity: ConnectivityMonitor,
    private val prefsManager: PrefsManager
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mQuakesResult = MutableLiveData<QuakesResult>()
    val quakesResult: LiveData<QuakesResult>
        get() = mQuakesResult

    fun bind() {
        if (connectivity.isOnline()) {
            fetchRemoteQuakes()
        } else {
            fetchLocalQuakes()
        }
    }

    fun refresh(callback: Action1<Boolean>) {
        val canRefresh = connectivity.isOnline()
        callback.call(canRefresh)
        if (canRefresh) {
            fetchRemoteQuakes()
        }
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
                    username = BuildConfig.GEONAMES_USERNAME
                )
                mQuakesResult.value = QuakesResult.QuakesSuccess(items)
                interactors.interStoreQuakes.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
                mQuakesResult.value = QuakesResult.QuakesError(e.getErrorMessage())
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

}