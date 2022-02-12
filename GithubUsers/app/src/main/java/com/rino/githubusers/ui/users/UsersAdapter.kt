package com.rino.githubusers.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rino.githubusers.databinding.ItemUserBinding
import com.rino.githubusers.core.model.GithubUser
import com.rino.githubusers.ui.base.ImageLoader

class UsersAdapter(
    private val imageLoader: ImageLoader<ImageView>,
    private val itemClickListener: (GithubUser) -> Unit
) : ListAdapter<GithubUser, UsersAdapter.UserViewHolder>(GithubUserItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(currentList[position])

    inner class UserViewHolder(
        private val binding: ItemUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(githubUser: GithubUser) {
            binding.root.setOnClickListener { itemClickListener(githubUser) }
            binding.tvLogin.text = githubUser.login

            githubUser.avatarUrl?.let { imageLoader.loadInto(it, binding.avatarImage) }
        }
    }

}

class GithubUserItemCallback : DiffUtil.ItemCallback<GithubUser>() {
    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem == newItem
    }
}
