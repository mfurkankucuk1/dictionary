package com.mfk.dictionary.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfk.dictionary.data.model.DataMuse
import com.mfk.dictionary.databinding.ItemRowGroupBinding

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
class DataMuseAdapter : RecyclerView.Adapter<DataMuseAdapter.DataMuseViewHolder>() {

    inner class DataMuseViewHolder(val binding: ItemRowGroupBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<DataMuse>() {
        override fun areItemsTheSame(
            oldItem: DataMuse,
            newItem: DataMuse
        ): Boolean {
            return oldItem.score == newItem.score
        }

        override fun areContentsTheSame(
            oldItem: DataMuse,
            newItem: DataMuse
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataMuseViewHolder {
        return DataMuseViewHolder(
            ItemRowGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DataMuseViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            tvGroup.text = currentItem.word
        }

    }

    override fun getItemCount() = differ.currentList.size
}