package com.mfk.dictionary.ui.adapter

import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.text.color
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfk.dictionary.R
import com.mfk.dictionary.data.model.Meanings
import com.mfk.dictionary.databinding.ItemRowEntriesBinding
import com.mfk.dictionary.utils.remove
import com.mfk.dictionary.utils.show
import java.util.*

/**
 * Created by M.Furkan KÜÇÜK on 13.01.2023
 **/
class DictionaryDetailAdapter :
    RecyclerView.Adapter<DictionaryDetailAdapter.DictionariyDetailViewHolder>() {

    inner class DictionariyDetailViewHolder(val binding: ItemRowEntriesBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Meanings>() {
        override fun areItemsTheSame(
            oldItem: Meanings,
            newItem: Meanings
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Meanings,
            newItem: Meanings
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionariyDetailViewHolder {
        return DictionariyDetailViewHolder(
            ItemRowEntriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DictionariyDetailViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.binding.apply {
            setupHeaderUI(holder, currentItem, (position + 1))
            setupDefinitionUI(holder, currentItem)
            setupExampleUI(holder, currentItem)
        }
    }

    private fun setupHeaderUI(
        holder: DictionariyDetailViewHolder,
        currentItem: Meanings,
        position: Int
    ) {
        holder.binding.apply {

            val partOfSpeech = SpannableStringBuilder()
                .color(ContextCompat.getColor(holder.itemView.context, R.color.black)) {
                    append("$position - ")

                }.color(
                    ContextCompat.getColor(holder.itemView.context, R.color.search_button_color)
                ) {
                    append(currentItem.partOfSpeech?.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })
                }

            tvHeader.text = partOfSpeech
        }
    }

    private fun setupExampleUI(
        holder: DictionariyDetailViewHolder,
        currentItem: Meanings
    ) {
        holder.binding.apply {
            holder.binding.apply {
                currentItem.definitions[0].example?.let { example ->
                    tvExampleHeader.show()
                    tvExample.show()
                    tvExample.text = example
                } ?: run {
                    tvExampleHeader.remove()
                    tvExample.remove()
                }
            }
        }
    }

    private fun setupDefinitionUI(
        holder: DictionariyDetailViewHolder,
        currentItem: Meanings
    ) {
        holder.binding.apply {
            currentItem.definitions[0].definition?.let { definition ->
                tvDescription.show()
                tvDescription.text = definition
            } ?: run {
                tvDescription.remove()
            }
        }
    }

    override fun getItemCount() = differ.currentList.size
}