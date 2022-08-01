package com.example.pagingwithhilt.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingwithhilt.databinding.ListItemBinding
import com.example.pagingwithhilt.model.Data

class PagingAdapter(private val onItemClicked: (Data) -> Unit) :
    PagingDataAdapter<Data, PagingAdapter.PagingViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return PagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val current = getItem(position)
//        holder.itemView.setOnClickListener {
//            onItemClicked(current)
//        }
        if (current != null) {
            holder.bind(current)
        }
    }

    class PagingViewHolder(private var binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Data) {
            binding.apply {
               textViewName.text =
                    "${item.firstName} ${item.lastName}"
                textViewEmail.text = item.email

            }
        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}