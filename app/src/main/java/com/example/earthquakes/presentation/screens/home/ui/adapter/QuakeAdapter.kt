package com.example.earthquakes.presentation.screens.home.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.earthquakes.databinding.VhQuakeItemBinding
import com.example.earthquakes.domain.Quake
import com.example.earthquakes.presentation.screens.home.ui.viewholder.QuakeViewHolder

class QuakeAdapter(
    private val models: List<Quake>,
    private val clickListener: QuakeViewHolder.QuakeItemClickListener
) : RecyclerView.Adapter<QuakeViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuakeViewHolder {
        return QuakeViewHolder(
            VhQuakeItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            clickListener
        )
    }

    override fun onBindViewHolder(
        viewHolder: QuakeViewHolder,
        position: Int
    ) {
        viewHolder.bind(models[position], position)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onViewDetachedFromWindow(holder: QuakeViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unbind()
    }

}