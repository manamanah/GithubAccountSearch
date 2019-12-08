package com.example.android.githubaccountsearch.adapters

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android.githubaccountsearch.databinding.RepositoryItemBinding
import com.example.android.githubaccountsearch.models.GitRepository
import java.util.*


class GitRepositoryAdapter : RecyclerView.Adapter<GitRepositoryAdapter.ViewHolder>() {

    private var repositories : List<GitRepository> = Collections.emptyList()


    override fun getItemCount(): Int = repositories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = RepositoryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position in 0 until itemCount){
            val item = repositories[position]
            holder.bind(item)
        }
    }

    fun updateReposList(list: List<GitRepository>){
        repositories = list
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : GitRepository){
            binding.viewModel = item

            binding.title.movementMethod = LinkMovementMethod.getInstance()

            val link = HtmlCompat.fromHtml("<a href='${item.url}'>${item.name}</a>", HtmlCompat.FROM_HTML_MODE_LEGACY)
            binding.title.text = link

            binding.timeRange.text = "${item.creationYear.get(Calendar.YEAR)} - ${item.lastPushYear.get(Calendar.YEAR)}"
        }
    }
}