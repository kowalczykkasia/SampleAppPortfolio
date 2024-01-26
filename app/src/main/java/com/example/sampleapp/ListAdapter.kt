package com.example.sampleapp

import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp.databinding.ItemListBinding
import com.example.sampleapp.models.Item
import com.example.sampleapp.utils.removeRegex

class ListAdapter(
    private val openingLinkListener: (String) -> Unit
) : ListAdapter<Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(
                oldItem: Item,
                newItem: Item
            ) = oldItem.dateTaken == newItem.dateTaken

            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item
            ) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ListItemViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ListItemViewHolder).bind(getItem(position) as Item, openingLinkListener)
}

class ListItemViewHolder(private var binding: ItemListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item, openingLinkListener: (String) -> Unit) {
        binding.apply {
            tvDescription.text = Html.fromHtml(item.description.removeRegex(), Html.FROM_HTML_MODE_COMPACT)
            root.setOnClickListener {
                openingLinkListener.invoke(item.link)
            }
            Glide.with(root.context)
                .load(item.media.m)
                .placeholder(R.drawable.ic_placeholder)
                .centerCrop()
                .into(ivImage)
        }
    }
}