package com.example.earthquakes.presentation.map.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakes.framework.extensions.getErrorMessage
import com.example.earthquakes.framework.quakeresult.QuakesResult
import com.example.earthquakes.presentation.map.interactors.MapInteractors
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val interactors: MapInteractors,
) : ViewModel() {

    private val TAG = MapViewModel::class.qualifiedName

    private val mQuakesResult = MutableLiveData<QuakesResult>()
    val quakesResult: LiveData<QuakesResult>
        get() = mQuakesResult

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