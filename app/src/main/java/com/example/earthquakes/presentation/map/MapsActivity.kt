package com.example.earthquakes.presentation.map

import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import com.example.earthquakes.R
import com.example.earthquakes.databinding.ActivityMapsBinding
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.base.BaseActivity
import com.example.earthquakes.framework.quakeresult.QuakesResult
import com.example.earthquakes.presentation.map.viewmodel.MapViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : BaseActivity(), OnMapReadyCallback {

    private lateinit var viewModel: MapViewModel
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var quakes = ArrayList<Quake>()

    override fun initialiseView() {
        setupToolbar()
        showMap()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun showMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        viewModel.fetchQuakes()
    }

    override fun observeLiveData() {
        viewModel.quakesResult.observe(this, {
            when (it) {
                is QuakesResult.QuakesSuccess -> populate(it.items)
                is QuakesResult.QuakesError -> populateError(it.error)
            }
        })
    }

    private fun populate(models: List<Quake>) {
        this.quakes.addAll(models)
        showQuakesOnMap()
    }

    private fun showQuakesOnMap() {
        for (quake in quakes) {
            val location = LatLng(
                quake.latitude!!.toDouble(),
                quake.longitude!!.toDouble()
            )
            mMap.addMarker(
                MarkerOptions()
                    .position(location)
                    .title(quake.datetime)
            )
        }
    }

    private fun populateError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun initialiseViewBinding() {
        binding = ActivityMapsBinding.inflate(layoutInflater)
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MapViewModel::class.java]
    }

    override fun getToolbar(): Toolbar {
        return binding.toolbar
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
