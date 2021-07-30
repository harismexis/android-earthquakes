package com.example.earthquakes.presentation.screens.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakes.R
import com.example.earthquakes.databinding.VhQuakeItemBinding
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.framework.extensions.setTextColorCompat
import com.example.earthquakes.framework.extensions.setTextOrMissing

class QuakeViewHolder(
    private val binding: VhQuakeItemBinding,
    private val itemClick: QuakeItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        private const val MAGNITUDE_THRESHOLD = 8.0f
    }

    interface QuakeItemClickListener {
        fun onQuakeItemClick(item: Quake, position: Int)
    }

    fun bind(
        item: Quake,
        position: Int
    ) {
        binding.txtSource.setTextOrMissing(item.source)
        binding.txtDatetime.setTextOrMissing(item.datetime)
        binding.txtDepth.setTextOrMissing(item.depth.toString())
        setupMagnitudeText(item.magnitude)
        binding.txtMagnitude.setTextOrMissing(item.magnitude.toString())
        binding.txtLatitude.setTextOrMissing(item.latitude.toString())
        binding.txtLongitude.setTextOrMissing(item.longitude.toString())
        itemView.setOnClickListener {
            itemClick.onQuakeItemClick(item, position)
        }
    }

    private fun setupMagnitudeText(magnitude: Float?) {
        if (magnitude != null && magnitude >= MAGNITUDE_THRESHOLD) {
            binding.txtMagnitude.setTextColorCompat(R.color.red_8)
        } else {
            binding.txtMagnitude.setTextColorCompat(R.color.default_text_color)
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}