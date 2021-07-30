package com.example.earthquakes.presentation.map.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earthquakes.framework.extensions.getErrorMessage
import com.example.earthquakes.framework.quakeresult.QuakesResult
import com.example.earthquakes.usecases.UseCaseGetLocalQuakes
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getLocalQuakes: UseCaseGetLocalQuakes
) : ViewModel() {

    private val TAG = MapViewModel::class.qualifiedName

    private val mQuakesResult = MutableLiveData<QuakesResult>()
    val quakesResult: LiveData<QuakesResult>
        get() = mQuakesResult

    fun fetchQuakes() {
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

}