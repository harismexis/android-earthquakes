package com.example.earthquakes.presentation.home.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakes.R
import com.example.earthquakes.databinding.VhQuakeItemBinding
import com.example.earthquakes.domain.Quake

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
        binding.txtSource.text = item.source
        binding.txtDatetime.text = item.datetime
        binding.txtDepth.text = item.depth.toString()
        setupMagnitudeText(item.magnitude)
        binding.txtMagnitude.text = item.magnitude.toString()
        binding.txtLatitude.text = item.latitude.toString()
        binding.txtLongitude.text = item.longitude.toString()
        itemView.setOnClickListener {
            itemClick.onQuakeItemClick(item, position)
        }
    }

    private fun setupMagnitudeText(magnitude: Float?) {
        if (magnitude != null && magnitude >= MAGNITUDE_THRESHOLD) {
            binding.txtMagnitude.setTextColor(binding.txtMagnitude.context.getColor(R.color.red_8))
        } else {
            binding.txtMagnitude.setTextColor(binding.txtMagnitude.context.getColor(R.color.default_text_color))
        }
    }

    fun unbind() {
        // Release resources, unsubscribe etc
    }

}