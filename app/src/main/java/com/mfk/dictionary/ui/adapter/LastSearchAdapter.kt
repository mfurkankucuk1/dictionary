package com.mfk.dictionary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfk.dictionary.data.model.LocalSearch
import com.mfk.dictionary.databinding.ItemRowLastSearchBinding

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
class LastSearchAdapter : RecyclerView.Adapter<LastSearchAdapter.LastSearchViewHolder>() {

    inner class LastSearchViewHolder(val binding: ItemRowLastSearchBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<LocalSearch>() {
        override fun areItemsTheSame(
            oldItem: LocalSearch,
            newItem: LocalSearch
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LocalSearch,
            newItem: LocalSearch
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastSearchViewHolder {
        return LastSearchViewHolder(
            ItemRowLastSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LastSearchViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            tvSearch.text = currentItem.searchText
            root.setOnClickListener {
                onItemClickListener?.let {
                    it(currentItem)
                }
            }
        }
    }

    override fun getItemCount() = differ.currentList.size

    private var onItemClickListener: ((LocalSearch) -> Unit)? = null

    fun setOnItemClickListener(listener: (LocalSearch) -> Unit) {
        onItemClickListener = listener
    }
}