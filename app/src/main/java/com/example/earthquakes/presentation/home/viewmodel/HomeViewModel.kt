package com.example.earthquakes.presentation.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakes.BuildConfig
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.presentation.interactors.HomeInteractors
import com.example.earthquakes.framework.extensions.getErrorMessage
import com.example.earthquakes.framework.util.functional.Action1
import com.example.earthquakes.framework.util.network.ConnectivityMonitor
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val interactors: HomeInteractors,
    private val connectivity: ConnectivityMonitor,
) : ViewModel() {

    private val TAG = HomeViewModel::class.qualifiedName

    private val mModels = MutableLiveData<List<Quake>>()
    val models: LiveData<List<Quake>>
        get() = mModels

    companion object {
        const val NORTH_DEFAULT = 44.1f
        const val SOUTH_DEFAULT = -9.9f
        const val EAST_DEFAULT = -22.4f
        const val WEST_DEFAULT = 55.2f
    }

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
                    north = NORTH_DEFAULT,
                    south = SOUTH_DEFAULT,
                    east = EAST_DEFAULT,
                    west = WEST_DEFAULT,
                    username = BuildConfig.GEONAMES_USERNAME
                )
                mModels.value = items
                interactors.interStoreQuakes.invoke(items)
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

    private fun fetchLocalQuakes() {
        viewModelScope.launch {
            try {
                mModels.value = interactors.interGetLocalQuakes.invoke()
            } catch (e: Exception) {
                Log.d(TAG, e.getErrorMessage())
            }
        }
    }

}