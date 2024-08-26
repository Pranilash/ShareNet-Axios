package com.overengineered.sharenet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.overengineered.sharenet.databinding.ItemSurplusItemBinding

class SurplusItemsAdapter : ListAdapter<SurplusItem, SurplusItemsAdapter.SurplusItemViewHolder>(SurplusItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurplusItemViewHolder {
        val binding = ItemSurplusItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SurplusItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SurplusItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SurplusItemViewHolder(private val binding: ItemSurplusItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SurplusItem) {
            binding.surplusItem = item
            binding.executePendingBindings()
        }
    }

    class SurplusItemDiffCallback : DiffUtil.ItemCallback<SurplusItem>() {
        override fun areItemsTheSame(oldItem: SurplusItem, newItem: SurplusItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SurplusItem, newItem: SurplusItem): Boolean {
            return oldItem == newItem
        }
    }
}
