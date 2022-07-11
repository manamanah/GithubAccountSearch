package com.example.android.githubaccountsearch.ui.githubaccount

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.githubaccountsearch.databinding.LanguageItemBinding
import com.example.android.githubaccountsearch.models.Language


class LanguageAdapter : ListAdapter<Language, LanguageAdapter.ViewHolder>(LanguageDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LanguageItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: LanguageItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(languageItem: Language) {
            with(binding) {
                name.text = languageItem.name
                percent.text = "${languageItem.percentage}%"
            }
        }
    }

    class LanguageDiffUtils : DiffUtil.ItemCallback<Language>() {
        override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
            return oldItem.name == newItem.name && oldItem.percentage == newItem.percentage
        }
    }
}