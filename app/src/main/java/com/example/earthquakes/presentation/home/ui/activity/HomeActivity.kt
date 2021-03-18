package com.example.earthquakes.presentation.home.ui.activity

import android.content.ActivityNotFoundException
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.earthquakes.R
import com.example.earthquakes.databinding.ActivityHomeBinding
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.base.BaseActivity
import com.example.earthquakes.framework.extensions.getErrorMessage
import com.example.earthquakes.framework.extensions.setDivider
import com.example.earthquakes.framework.util.getMapIntent
import com.example.earthquakes.framework.util.guardLet
import com.example.earthquakes.presentation.home.ui.adapter.QuakeAdapter
import com.example.earthquakes.presentation.home.ui.viewholder.QuakeViewHolder
import com.example.earthquakes.presentation.home.viewmodel.HomeViewModel
import com.example.earthquakes.presentation.preferences.PrefsActivity

class HomeActivity : BaseActivity(), QuakeViewHolder.QuakeItemClickListener {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: QuakeAdapter
    private var uiModels: MutableList<Quake> = mutableListOf()

    companion object {
        private val TAG: String? = HomeActivity::class.qualifiedName
    }

    override fun initialise() {
        super.initialise()
        PreferenceManager.setDefaultValues(this, R.xml.settings, false)
        setupSwipeToRefresh()
        viewModel.bind()
    }

    override fun initialiseViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun initialiseViewBinding() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun initialiseView() {
        super.initialiseView()
        initialiseRecycler()
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun onQuakeItemClick(item: Quake, position: Int) {
        val (lat, lon) = guardLet(item.latitude, item.longitude) {
            Toast.makeText(this, "Invalid coordinates", Toast.LENGTH_SHORT).show()
            return
        }
        try {
            startActivity(getMapIntent(lat, lon))
        } catch (e: ActivityNotFoundException) {
            Log.d(TAG, e.getErrorMessage())
        }
    }

    override fun getToolbar(): Toolbar {
        return binding.homeToolbar
    }

    override fun observeLiveData() {
        viewModel.models.observe(this, {
            populate(it)
        })
    }

    private fun populate(models: List<Quake>) {
        binding.homeSwipeRefresh.isRefreshing = false
        binding.loadingProgressBar.visibility = View.GONE
        binding.homeList.visibility = View.VISIBLE
        uiModels.clear()
        uiModels.addAll(models)
        adapter.notifyDataSetChanged()
    }

    private fun initialiseRecycler() {
        adapter = QuakeAdapter(uiModels, this)
        adapter.setHasStableIds(true)
        binding.homeList.layoutManager = LinearLayoutManager(this)
        binding.homeList.adapter = adapter
        binding.homeList.setDivider(R.drawable.divider)
    }

    private fun setupSwipeToRefresh() {
        binding.homeSwipeRefresh.setOnRefreshListener {
            binding.homeSwipeRefresh.isRefreshing = true
            viewModel.refresh { canRefresh ->
                if (!canRefresh) {
                    binding.homeSwipeRefresh.isRefreshing = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, PrefsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}