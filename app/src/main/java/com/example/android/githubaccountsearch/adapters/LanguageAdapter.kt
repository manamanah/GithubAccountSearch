package com.example.android.githubaccountsearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.githubaccountsearch.databinding.LanguageItemBinding
import com.example.android.githubaccountsearch.models.Language
import java.util.*


class LanguageAdapter : RecyclerView.Adapter<LanguageAdapter.ViewHolder>(){

    private var languages : List<Language> = Collections.emptyList()


    override fun getItemCount(): Int = languages.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LanguageItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position in 0 until itemCount){
            holder.bind(languages[position])
        }
    }

    fun updateList(list: List<Language>){
        languages = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding : LanguageItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(languageItem: Language){
            binding.viewModel = languageItem
        }
    }
}