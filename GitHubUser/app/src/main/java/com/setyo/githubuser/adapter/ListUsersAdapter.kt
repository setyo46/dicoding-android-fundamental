package com.setyo.githubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.setyo.githubuser.R
import com.setyo.githubuser.data.GithubUser
import com.setyo.githubuser.databinding.ItemRowUserBinding
import com.setyo.githubuser.ui.DetailActivity

class ListUsersAdapter(private val listUser: List<GithubUser>): RecyclerView.Adapter<ListUsersAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GithubUser) {
            binding.apply {
                tvUsername.text = user.login
                Glide.with(itemView.context)
                    .load(user.avatarUrl)
                    .error(R.drawable.baseline_person_24)
                    .into(ivItemAvatar)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listUser.size

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        viewHolder.bind(listUser[position])
    }


}