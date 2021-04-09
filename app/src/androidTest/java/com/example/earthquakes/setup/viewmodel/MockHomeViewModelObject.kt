package com.example.earthquakes.setup.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.earthquakes.framework.quakeresult.QuakesResult
import com.example.earthquakes.presentation.home.viewmodel.HomeViewModel
import io.mockk.mockk

object MockHomeViewModelObject {

    private var mockHomeViewModel: HomeViewModel = mockk(relaxed = true)

    var quakesResult = MutableLiveData<QuakesResult>()

    fun getMockHomeViewModel(): HomeViewModel {
        return mockHomeViewModel
    }


}