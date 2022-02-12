package com.rino.githubusers.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rino.githubusers.databinding.ItemReposBinding
import com.rino.githubusers.core.model.GithubRepos
import androidx.recyclerview.widget.ListAdapter

class GithubReposAdapter(
    private val itemClickListener: (GithubRepos) -> Unit
) : ListAdapter<GithubRepos, GithubReposAdapter.GithubReposViewHolder>(GithubReposItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubReposViewHolder {
        val binding = ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GithubReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GithubReposViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class GithubReposViewHolder(
        private val binding: ItemReposBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(githubRepos: GithubRepos) {
            binding.root.setOnClickListener { itemClickListener(githubRepos) }
            binding.repoName.text = githubRepos.name
        }

    }
}

class GithubReposItemCallback : DiffUtil.ItemCallback<GithubRepos>() {
    override fun areItemsTheSame(oldItem: GithubRepos, newItem: GithubRepos): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubRepos, newItem: GithubRepos): Boolean {
        return oldItem == newItem
    }

}
