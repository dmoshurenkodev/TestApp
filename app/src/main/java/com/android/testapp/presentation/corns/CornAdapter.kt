package com.android.testapp.presentation.corns

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.testapp.R
import com.android.testapp.domain.model.CornModel
import kotlinx.android.synthetic.main.item_corn.view.*

class CornAdapter : ListAdapter<CornModel, CornAdapter.CornViewHolder>(diffCallback) {

    var itemClickListener: ((CornModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CornViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CornViewHolder(inflater.inflate(R.layout.item_corn, parent, false))
    }

    override fun onBindViewHolder(holder: CornViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CornViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val tvName: TextView = itemView.tvName
        private val tvDescription: TextView = itemView.tvDescription

        fun bind(model: CornModel) {
            tvName.text = model.farmName
            tvDescription.text = model.fieldNoDescr
            itemView.setOnClickListener { itemClickListener?.invoke(model) }
        }
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<CornModel>() {
            override fun areItemsTheSame(
                oldItem: CornModel,
                newItem: CornModel
            ): Boolean {
                return oldItem.fieldID == newItem.fieldID
            }

            override fun areContentsTheSame(
                oldItem: CornModel,
                newItem: CornModel
            ): Boolean {
                return oldItem.farmName == newItem.farmName
            }
        }
    }

}