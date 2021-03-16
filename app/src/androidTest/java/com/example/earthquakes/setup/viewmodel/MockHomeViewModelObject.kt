package com.example.earthquakes.setup.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.presentation.home.viewmodel.HomeViewModel
import io.mockk.mockk

object MockHomeViewModelObject {

    private var mockHomeViewModel: HomeViewModel = mockk(relaxed = true)

    var mModels = MutableLiveData<List<Quake>>()
    val models: LiveData<List<Quake>>
        get() = mModels

    fun getMockHomeViewModel(): HomeViewModel {
        return mockHomeViewModel
    }


}