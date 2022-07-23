package com.example.android.githubaccountsearch.ui.githubaccount

import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.githubaccountsearch.databinding.RepositoryItemBinding
import com.example.android.githubaccountsearch.models.GitRepository
import java.util.Calendar

class GitRepositoryAdapter :
    ListAdapter<GitRepository, GitRepositoryAdapter.ViewHolder>(GitRepositoryDiffUtils()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewBinding = RepositoryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GitRepository) {
            val link = HtmlCompat.fromHtml(
                "<a href='${item.url}'>${item.name}</a>",
                HtmlCompat.FROM_HTML_MODE_LEGACY
            )
            val range =
                "${item.creationYear.get(Calendar.YEAR)} - ${item.lastPushYear.get(Calendar.YEAR)}"

            with(binding) {
                title.movementMethod = LinkMovementMethod.getInstance()
                title.text = link
                timeRange.text = range
                stars.text = item.starsCount.toString()
                forks.text = item.forksCount.toString()

                if (item.language.isNullOrEmpty()) {
                    language.visibility = View.INVISIBLE
                } else {
                    language.text = item.language.orEmpty()
                    language.visibility = View.VISIBLE
                }

                if (item.description.isNullOrEmpty()) {
                    description.visibility = View.INVISIBLE
                } else {
                    description.visibility = View.VISIBLE
                    description.text = item.description.orEmpty()
                }

                if (item.license == null) {
                    license.visibility = View.INVISIBLE
                } else {
                    license.visibility = View.VISIBLE
                    license.text = item.license.name
                }
            }
        }
    }

    class GitRepositoryDiffUtils : DiffUtil.ItemCallback<GitRepository>() {
        override fun areItemsTheSame(oldItem: GitRepository, newItem: GitRepository): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GitRepository, newItem: GitRepository): Boolean {
            return oldItem.name == newItem.name &&
                oldItem.description == newItem.description &&
                oldItem.language == newItem.language &&
                oldItem.starsCount == newItem.starsCount &&
                oldItem.forksCount == newItem.forksCount &&
                oldItem.url == newItem.url &&
                oldItem.license == newItem.license &&
                oldItem.creationYear == newItem.creationYear &&
                oldItem.lastPushYear == newItem.lastPushYear &&
                oldItem.size == newItem.size
        }
    }
}
